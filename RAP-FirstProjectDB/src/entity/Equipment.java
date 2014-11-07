package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the equipment database table.
 * 
 */
@Entity
@Table(name="equipment")
@NamedQuery(name="Equipment.findAll", query="SELECT e FROM Equipment e")
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEquipment;

	private String nameEquipment;

	//bi-directional many-to-one association to Type
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="typeEquipment")
	private Type type;

	//bi-directional many-to-one association to Version
	@OneToMany(mappedBy="equipment", cascade={CascadeType.PERSIST})
	private List<Version> versions;

	public Equipment() {
	}

	public int getIdEquipment() {
		return this.idEquipment;
	}

	public void setIdEquipment(int idEquipment) {
		this.idEquipment = idEquipment;
	}

	public String getNameEquipment() {
		return this.nameEquipment;
	}

	public void setNameEquipment(String nameEquipment) {
		this.nameEquipment = nameEquipment;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Version> getVersions() {
		return this.versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public Version addVersion(Version version) {
		getVersions().add(version);
		version.setEquipment(this);

		return version;
	}

	public Version removeVersion(Version version) {
		getVersions().remove(version);
		version.setEquipment(null);

		return version;
	}

}
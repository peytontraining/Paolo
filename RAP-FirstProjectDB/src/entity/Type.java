package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type database table.
 * 
 */
@Entity
@Table(name="type")
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtype;

	private String typename;

	//bi-directional many-to-one association to Equipment
	@OneToMany(mappedBy="type", cascade={CascadeType.PERSIST})
	private List<Equipment> equipments;

	public Type() {
	}

	public int getIdtype() {
		return this.idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List<Equipment> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Equipment addEquipment(Equipment equipment) {
		getEquipments().add(equipment);
		equipment.setType(this);

		return equipment;
	}

	public Equipment removeEquipment(Equipment equipment) {
		getEquipments().remove(equipment);
		equipment.setType(null);

		return equipment;
	}

}
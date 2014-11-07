package entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the configure database table.
 * 
 */
@Entity
@Table(name="configure")
@NamedQuery(name="Configure.findAll", query="SELECT c FROM Configure c")
public class Configure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idconfigure;

	private String description;

	private String mandactory;

	private String name;

	private String value;

	//bi-directional many-to-one association to Device
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="device")
	private Device deviceBean;

	public Configure() {
	}

	public int getIdconfigure() {
		return this.idconfigure;
	}

	public void setIdconfigure(int idconfigure) {
		this.idconfigure = idconfigure;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMandactory() {
		return this.mandactory;
	}

	public void setMandactory(String mandactory) {
		this.mandactory = mandactory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Device getDeviceBean() {
		return this.deviceBean;
	}

	public void setDeviceBean(Device deviceBean) {
		this.deviceBean = deviceBean;
	}
}
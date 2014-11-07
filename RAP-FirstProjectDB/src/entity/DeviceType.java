package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the device_type database table.
 * 
 */
@Entity
@Table(name="device_type")
@NamedQuery(name="DeviceType.findAll", query="SELECT d FROM DeviceType d")
public class DeviceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iddevice_type")
	private int iddeviceType;

	private String nameDeviceType;

	//bi-directional many-to-one association to Device1
	@OneToMany(mappedBy="deviceType")
	private List<Device1> device1s;

	//bi-directional many-to-one association to AppModule
	@ManyToOne
	@JoinColumn(name="appModule")
	private AppModule appModuleBean;

	public DeviceType() {
	}

	public int getIddeviceType() {
		return this.iddeviceType;
	}

	public void setIddeviceType(int iddeviceType) {
		this.iddeviceType = iddeviceType;
	}

	public String getNameDeviceType() {
		return this.nameDeviceType;
	}

	public void setNameDeviceType(String nameDeviceType) {
		this.nameDeviceType = nameDeviceType;
	}

	public List<Device1> getDevice1s() {
		return this.device1s;
	}

	public void setDevice1s(List<Device1> device1s) {
		this.device1s = device1s;
	}

	public Device1 addDevice1(Device1 device1) {
		getDevice1s().add(device1);
		device1.setDeviceType(this);

		return device1;
	}

	public Device1 removeDevice1(Device1 device1) {
		getDevice1s().remove(device1);
		device1.setDeviceType(null);

		return device1;
	}

	public AppModule getAppModuleBean() {
		return this.appModuleBean;
	}

	public void setAppModuleBean(AppModule appModuleBean) {
		this.appModuleBean = appModuleBean;
	}

}
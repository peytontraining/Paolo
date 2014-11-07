package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the app_module database table.
 * 
 */
@Entity
@Table(name="app_module")
@NamedQuery(name="AppModule.findAll", query="SELECT a FROM AppModule a")
public class AppModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmodule;

	private String nameModule;

	//bi-directional many-to-one association to DeviceType
	@OneToMany(mappedBy="appModuleBean")
	private List<DeviceType> deviceTypes;

	public AppModule() {
	}

	public int getIdmodule() {
		return this.idmodule;
	}

	public void setIdmodule(int idmodule) {
		this.idmodule = idmodule;
	}

	public String getNameModule() {
		return this.nameModule;
	}

	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}

	public List<DeviceType> getDeviceTypes() {
		return this.deviceTypes;
	}

	public void setDeviceTypes(List<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public DeviceType addDeviceType(DeviceType deviceType) {
		getDeviceTypes().add(deviceType);
		deviceType.setAppModuleBean(this);

		return deviceType;
	}

	public DeviceType removeDeviceType(DeviceType deviceType) {
		getDeviceTypes().remove(deviceType);
		deviceType.setAppModuleBean(null);

		return deviceType;
	}

}
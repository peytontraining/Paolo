package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the devices database table.
 * 
 */
@Entity
@Table(name="devices")
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDevice;

	private String appModule;

	private String deviceType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	private String manufacturer;

	private String modelNumber;

	private String name;

	private String note;

	private String physicalLocation;

	private String room;

	private String status;

	//bi-directional many-to-one association to Configure
	@OneToMany(mappedBy="deviceBean",cascade={CascadeType.PERSIST})
	private List<Configure> configures;

	//bi-directional many-to-one association to Device1
	@ManyToOne
	@JoinColumn(name="masterTemplate")
	private Device1 device1;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="driver")
	private Driver driverBean;

	//bi-directional many-to-one association to Version
	@ManyToOne
	@JoinColumn(name="version")
	private Version versionBean;

	public Device() {
	}

	public int getIdDevice() {
		return this.idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getAppModule() {
		return this.appModule;
	}

	public void setAppModule(String appModule) {
		this.appModule = appModule;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelNumber() {
		return this.modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhysicalLocation() {
		return this.physicalLocation;
	}

	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Configure> getConfigures() {
		return this.configures;
	}

	public void setConfigures(List<Configure> configures) {
		this.configures = configures;
	}

	public Configure addConfigure(Configure configure) {
		getConfigures().add(configure);
		configure.setDeviceBean(this);

		return configure;
	}

	public Configure removeConfigure(Configure configure) {
		getConfigures().remove(configure);
		configure.setDeviceBean(null);

		return configure;
	}

	public Device1 getDevice1() {
		return this.device1;
	}

	public void setDevice1(Device1 device1) {
		this.device1 = device1;
	}

	public Driver getDriverBean() {
		return this.driverBean;
	}

	public void setDriverBean(Driver driverBean) {
		this.driverBean = driverBean;
	}

	public Version getVersionBean() {
		return this.versionBean;
	}

	public void setVersionBean(Version versionBean) {
		this.versionBean = versionBean;
	}

}
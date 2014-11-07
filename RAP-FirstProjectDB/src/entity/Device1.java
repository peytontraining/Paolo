package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the device1 database table.
 * 
 */
@Entity
@Table(name="device1")
@NamedQuery(name="Device1.findAll", query="SELECT d FROM Device1 d")
public class Device1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDevice1;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	private String manufacturer1;

	private String modelNumber1;

	private String name1;

	private String needUpdate;

	private String notes;

	private String physicalLocation1;

	//bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(name="deviceType1")
	private DeviceType deviceType;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="driver")
	private Driver driverBean;

	//bi-directional many-to-one association to Version
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="version1")
	private Version version;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="device1")
	private List<Device> devices;

	public Device1() {
	}

	public int getIdDevice1() {
		return this.idDevice1;
	}

	public void setIdDevice1(int idDevice1) {
		this.idDevice1 = idDevice1;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getManufacturer1() {
		return this.manufacturer1;
	}

	public void setManufacturer1(String manufacturer1) {
		this.manufacturer1 = manufacturer1;
	}

	public String getModelNumber1() {
		return this.modelNumber1;
	}

	public void setModelNumber1(String modelNumber1) {
		this.modelNumber1 = modelNumber1;
	}

	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getNeedUpdate() {
		return this.needUpdate;
	}

	public void setNeedUpdate(String needUpdate) {
		this.needUpdate = needUpdate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPhysicalLocation1() {
		return this.physicalLocation1;
	}

	public void setPhysicalLocation1(String physicalLocation1) {
		this.physicalLocation1 = physicalLocation1;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Driver getDriverBean() {
		return this.driverBean;
	}

	public void setDriverBean(Driver driverBean) {
		this.driverBean = driverBean;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setDevice1(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDevice1(null);

		return device;
	}

}
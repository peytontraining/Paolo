package entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the versions database table.
 * 
 */
@Entity
@Table(name="versions")
@NamedQuery(name="Version.findAll", query="SELECT v FROM Version v")
public class Version implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int version;

	private String deploySource;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deployTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date saveTime;

	private String targetVersion;

	private String versionName;

	//bi-directional many-to-one association to Device1
	@OneToMany(mappedBy="version",cascade={CascadeType.PERSIST})
	private List<Device1> device1s;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="versionBean", cascade={CascadeType.PERSIST})
	private List<Device> devices;

	//bi-directional many-to-one association to Equipment
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="idEquipment")
	private Equipment equipment;

	public Version() {
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDeploySource() {
		return this.deploySource;
	}

	public void setDeploySource(String deploySource) {
		this.deploySource = deploySource;
	}

	public Date getDeployTime() {
		return this.deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public Date getSaveTime() {
		return this.saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public String getTargetVersion() {
		return this.targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public List<Device1> getDevice1s() {
		return this.device1s;
	}

	public void setDevice1s(List<Device1> device1s) {
		this.device1s = device1s;
	}

	public Device1 addDevice1(Device1 device1) {
		getDevice1s().add(device1);
		device1.setVersion(this);

		return device1;
	}

	public Device1 removeDevice1(Device1 device1) {
		getDevice1s().remove(device1);
		device1.setVersion(null);

		return device1;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setVersionBean(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setVersionBean(null);

		return device;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
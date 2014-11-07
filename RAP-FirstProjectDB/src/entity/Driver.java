package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the driver database table.
 * 
 */
@Entity
@Table(name="driver")
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddriver;

	private String nameDriver;

	//bi-directional many-to-one association to Device1
	@OneToMany(mappedBy="driverBean")
	private List<Device1> device1s;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="driverBean")
	private List<Device> devices;

	public Driver() {
	}

	public int getIddriver() {
		return this.iddriver;
	}

	public void setIddriver(int iddriver) {
		this.iddriver = iddriver;
	}

	public String getNameDriver() {
		return this.nameDriver;
	}

	public void setNameDriver(String nameDriver) {
		this.nameDriver = nameDriver;
	}

	public List<Device1> getDevice1s() {
		return this.device1s;
	}

	public void setDevice1s(List<Device1> device1s) {
		this.device1s = device1s;
	}

	public Device1 addDevice1(Device1 device1) {
		getDevice1s().add(device1);
		device1.setDriverBean(this);

		return device1;
	}

	public Device1 removeDevice1(Device1 device1) {
		getDevice1s().remove(device1);
		device1.setDriverBean(null);

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
		device.setDriverBean(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDriverBean(null);

		return device;
	}

}
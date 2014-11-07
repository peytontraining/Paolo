package view.project;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import entity.Device;

public class DeviceViewerComparator extends ViewerComparator{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;
    
    public DeviceViewerComparator(){
        this.propertyIndex = 0;
        direction = DESCENDING;
    }
    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
      }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
          // Same column as last sort; toggle the direction
          direction = 1 - direction;
        } else {
          // New column; do an ascending sort
          this.propertyIndex = column;
          direction = DESCENDING;
        }
      }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        System.out.println("abc"+ e1.toString());
        Device device1 = (Device) e1;
        Device device2 = (Device) e2;
      int rc = 0;
      switch (propertyIndex) {
      case 0:
          rc = device1.getName().compareTo(device2.getName());
        break;
      case 1:
          rc = device1.getAppModule().compareTo(device2.getAppModule());
        break;
      case 2:
          rc = device1.getDeviceType().compareTo(device2.getDeviceType());
        break;
      case 3:
          rc = device1.getPhysicalLocation().compareTo(device2.getPhysicalLocation());
        break;
      case 4:
          rc = device1.getManufacturer().compareTo(device2.getManufacturer());
        break;
      default:
        rc = 0;
      }
      // If descending order, flip the direction
      if (direction == DESCENDING) {
        rc = -rc;
      }
      return rc;
    }
}

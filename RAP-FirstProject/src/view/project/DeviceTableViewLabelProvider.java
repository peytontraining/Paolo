package view.project;


import java.awt.TextField;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.*;

public class DeviceTableViewLabelProvider extends LabelProvider implements
        ITableLabelProvider {
    private static final long serialVersionUID = 3997124367771097992L;

    /** The txt filter. */
    TextField txtFilter;
    public final Image CAMERA_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/camera.png").createImage();
    public final Image FIREPLACE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject","/icons/fireplace-controller.png").createImage();
    /**
     * The Class ViewContentProvider.
     */

    public DeviceTableViewLabelProvider() {
    }
    
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        Device row = (Device) element;
        if (0 == columnIndex) {
            if ("CCTV".equals(row.getAppModule())) {
                return CAMERA_IMAGE;
            } 
            if ("Fireplace".equals(row.getAppModule())) {
                return FIREPLACE_IMAGE;
            }
        }
        return null;
    }
    
    @Override
    public String getColumnText(Object element, int columnIndex) {
        Device row = (Device) element;
        if (columnIndex == 0) {
            return row.getDeviceType();
        } else if (columnIndex == 1) {
            return row.getName();
        } else if (columnIndex == 2) {
            return row.getRoom();
        } else if (columnIndex == 3) {
            return row.getStatus();
        } else if (columnIndex == 4) {
            return row.getDeviceType();
        }
        return null;
    }
}

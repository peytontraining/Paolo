package view.template;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.*;
public class DeviceTemplateLabelProvider implements ITableLabelProvider{
  
    SimpleDateFormat dateformatJava = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
    public final Image CAMERA_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/camera.png").createImage();
    public final Image FIREPLACE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject","/icons/fireplace-controller.png").createImage();
    @Override
    public void addListener(ILabelProviderListener listener) {
        
    }

    @Override
    public void dispose() {
        
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        
    }

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        if (columnIndex == 0) {
            if (element instanceof AppModule) {
                if (((AppModule) element).getNameModule().intern() == "CCTV") {
                return CAMERA_IMAGE;
                }
            }
            if (element instanceof DeviceType) {
                if (((DeviceType) element).getAppModuleBean().getNameModule().intern() == "CCTV") {
                    return CAMERA_IMAGE;
                }
            }
            if (element instanceof Device1) {
                if (((Device1) element).getDeviceType().getAppModuleBean().getNameModule().intern() =="CCTV") {
                    return CAMERA_IMAGE;
                }
            }
            return FIREPLACE_IMAGE;

        }
        
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        switch (columnIndex){
        case 0: 
            if (element instanceof AppModule) {
              return ((AppModule) element).getNameModule();
              }
              if (element instanceof DeviceType) {
              return ((DeviceType) element).getNameDeviceType();
              }
              if (element instanceof Device1) {
              return ((Device1) element).getName1();
              }
              
        case 1:
            if (element instanceof Device1)
                return  dateformatJava.format(((Device1)element).getLastModified());
        case 2:
            if (element instanceof Device1)
                return ((Device1)element).getManufacturer1();
        case 3: 
            if (element instanceof Device1)
                return ((Device1)element).getModelNumber1();
        case 4:
            if (element instanceof Device1)
                return ((Device1)element).getVersion().getVersionName();
            }
        return null;
    }
    
}

package dialog;


import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.AppModule;
import entity.Device1;
import entity.DeviceType;
import entity.Equipment;
import entity.Type;
import entity.Version;

public class TemplateTreeViewLabelProvider extends LabelProvider{

    /**
     * 
     */
    private static final long serialVersionUID = -2731005230435299639L;
    public final Image CAMERA_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/camera.png").createImage();
    public final Image FIREPLACE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject","/icons/fireplace-controller.png").createImage();
    
    public Image getImage(Object element){
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
    
    @Override
    public String getText(Object element){
        if (element instanceof AppModule) {
            return ((AppModule) element).getNameModule();
        }
        if (element instanceof DeviceType) {
            return ((DeviceType) element).getNameDeviceType();
        }
        if (element instanceof Device1) {
            return ((Device1) element).getName1();
        }
        return null;

    }
}

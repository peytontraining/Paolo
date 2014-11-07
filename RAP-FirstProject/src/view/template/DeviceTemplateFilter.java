package view.template;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import entity.*;


public class DeviceTemplateFilter extends ViewerFilter{
    private static final long serialVersionUID = 1L;
    private String searchString;

    public void setSearchText(String s) {
    // ensure that the value can be used for matching
    this.searchString = s.toLowerCase();
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
    if (searchString == null || searchString.length() == 0) {
            return true;
    }
    String name;
    if (element instanceof AppModule){
        name = ((AppModule) element).getNameModule().toLowerCase();
        if(name != null && name.contains(searchString)){
            return true;
        }else{
            List<DeviceType> listType = ((AppModule) element).getDeviceTypes();
            if(listType != null){
                for(DeviceType type : listType){
                    if (select(viewer, element, type)){
                        return true;
                    }
                }
            }
        }
    } else if (element instanceof DeviceType){
        name = ((DeviceType) element).getNameDeviceType().toLowerCase();
        if(name != null && name.contains(searchString)){
            return true;
        } else {
            List<Device1> listDevice = ((DeviceType) element).getDevice1s();
                if(listDevice != null){
                    for(Device1 device : listDevice){
                        if(select(viewer,element,device)){
                            return true;
                        }
                    }
                }
            
        }
    } else {
        name = ((Device1) element).getName1().toLowerCase();
//        String lastModified = 
        String manufacturer = ((Device1) element).getManufacturer1().toLowerCase();
        String modelNumber = ((Device1) element).getModelNumber1().toLowerCase();
        String version = ((Device1) element).getVersion().getVersionName().toLowerCase();
        
        if(name != null && name.contains(searchString)){
            return true;
        }
        if(manufacturer != null && manufacturer.contains(searchString)){
            return true;
        }
        if(modelNumber != null && modelNumber.contains(searchString)){
            return true;
        }if(version != null && version.contains(searchString)){
            return true;
        }
    }
    return false;
    }
}

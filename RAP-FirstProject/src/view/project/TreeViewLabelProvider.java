package view.project;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.Equipment;
import entity.Type;
import entity.Version;

public class TreeViewLabelProvider extends LabelProvider{
    public final Image TYPE_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin("RAP-FirstProject", "/icons/company_group.png").createImage();
    public final Image EQUIPMENT_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin("RAP-FirstProject",
                    "/icons/project.png").createImage();
    public final Image VERSION_IMAGE = AbstractUIPlugin
            .imageDescriptorFromPlugin("RAP-FirstProject",
                    "/icons/version.png").createImage();
    
    public Image getImage(Object element){
        if(element instanceof Type){
            return TYPE_IMAGE;
        }        
        if(element instanceof Equipment){
            return EQUIPMENT_IMAGE;
        }
            return VERSION_IMAGE;
    }
    
    @Override
    public String getText(Object element){
        if(element instanceof Type){
            return ((Type) element).getTypename();
        }
        if(element instanceof Equipment){
            return ((Equipment) element).getNameEquipment();
        }
            return ((Version) element).getVersionName();
    }
}


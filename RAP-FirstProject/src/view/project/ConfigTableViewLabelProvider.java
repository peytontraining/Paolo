package view.project;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import entity.Configure;

public class ConfigTableViewLabelProvider extends LabelProvider implements ITableLabelProvider{

    /**
     * 
     */
    
    private static final long serialVersionUID = -1725515991895444364L;

    public ConfigTableViewLabelProvider() {
        
    }
    
    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        Configure row = (Configure) element;
        if(columnIndex == 0){
            return row.getName();
        }
        
        if(columnIndex == 1){
            return row.getValue();
        }
        
//        if(columnIndex == 2){
//            
//        }
        
        if(columnIndex == 3){
            return row.getDescription();
        }
        return null;
    }


}

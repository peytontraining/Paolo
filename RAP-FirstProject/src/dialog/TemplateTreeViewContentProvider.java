package dialog;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import entity.AppModule;
import entity.DeviceType;
import entity.Equipment;
import entity.Type;

public class TemplateTreeViewContentProvider implements ITreeContentProvider{
    private final Object[] EMPTY = new Object[0];
    
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if(inputElement instanceof List){
            return ((List) inputElement).toArray();
        }
        return EMPTY;
    }
    
    @Override
    public Object[] getChildren(Object parentElement) {
        if(parentElement instanceof AppModule && hasChildren(parentElement)){
            return ((AppModule) parentElement).getDeviceTypes().toArray();
        }
        
        if(parentElement instanceof DeviceType && hasChildren(parentElement)){
            return ((DeviceType) parentElement).getDevice1s().toArray();
        }
        
        return EMPTY;
    }

    @Override
    public Object getParent(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof AppModule && ((AppModule) element).getDeviceTypes().size()>0){
            return true;
        }
        
        if(element instanceof DeviceType && ((DeviceType) element).getDevice1s().size()>0){
            return true;
        }
        
        return false;    }


}

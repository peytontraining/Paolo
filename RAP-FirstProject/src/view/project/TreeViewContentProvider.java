package view.project;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import entity.Equipment;
import entity.Type;

public class TreeViewContentProvider implements ITreeContentProvider {

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
        if(parentElement instanceof Type && hasChildren(parentElement)){
            return ((Type) parentElement).getEquipments().toArray();
        }
        
        if(parentElement instanceof Equipment && hasChildren(parentElement)){
            return ((Equipment) parentElement).getVersions().toArray();
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
        if (element instanceof Type && ((Type) element).getEquipments().size() > 0){
            return true;
        }
        
        if(element instanceof Equipment && ((Equipment) element).getVersions().size()>0){
            return true;
        }
        
        return false;
    }

    
}

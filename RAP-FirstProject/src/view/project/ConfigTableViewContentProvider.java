package view.project;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ConfigTableViewContentProvider implements IStructuredContentProvider {

    /**
     * 
     */
    private static final long serialVersionUID = -5572909351968855559L;

    public ConfigTableViewContentProvider() {

    }
    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
        Object[] result = null;
        if (inputElement instanceof List<?>) {
            result = new Object[((List<Object[]>) inputElement).size()];
            ((List<Object[]>) inputElement).toArray(result);
        }

        return result;
    }

}

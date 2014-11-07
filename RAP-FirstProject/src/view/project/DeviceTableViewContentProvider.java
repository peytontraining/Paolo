package view.project;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DeviceTableViewContentProvider implements IStructuredContentProvider {
    private static final long serialVersionUID = -896659660499890585L;

    public DeviceTableViewContentProvider() {

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

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
}

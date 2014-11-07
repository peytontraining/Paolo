package view.template;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.AppModule;
import entity.Device1;
import entity.DeviceType;

public class DeviceTemplateContentProvider implements ITreeContentProvider {
   

    @Override
    public void dispose() {

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

    }

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof List)
            return ((List<?>) parentElement).toArray();
        if (parentElement instanceof AppModule)
            return ((AppModule) parentElement).getDeviceTypes().toArray();
        if (parentElement instanceof DeviceType)
            return ((DeviceType) parentElement).getDevice1s().toArray();
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof DeviceType)
            return ((DeviceType) element).getAppModuleBean();
        if (element instanceof Device1)
            return ((Device1) element).getDeviceType();
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof List)
            return ((List<?>) element).size() > 0;
        if (element instanceof AppModule)
            return ((AppModule) element).getDeviceTypes().size() > 0;
        if (element instanceof DeviceType)
            return ((DeviceType) element).getDevice1s().size() > 0;
        return false;
    }

}

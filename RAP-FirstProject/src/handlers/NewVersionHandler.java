package handlers;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import entity.Equipment;
import entity.Version;
import view.project.TreeViewPart;

public class NewVersionHandler extends AbstractHandler{
    Version version;
    Equipment equipment;
    
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");
        
        ISelection selection = window.getActivePage().getSelection("RAP-FirstProject.project");
        IStructuredSelection sselection = (IStructuredSelection) selection;
        Object element = sselection.getFirstElement();
        
        if(element instanceof Equipment){
            equipment = (Equipment) element;
            version = createNewVersion(equipment);
            equipment.getVersions().add(version);
            treeViewPart.treeViewer.refresh();
        }
        
        if(element instanceof Version){
            Version v;
            v = (Version) element;
            equipment = v.getEquipment();
            version = createNewVersion(equipment);
            equipment.getVersions().add(version);
            treeViewPart.treeViewer.refresh();
        }
        return null;
    }
    
    private Version createNewVersion(Equipment equipment) {
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 10, 10, 15, 20, 30);
        Date date = cal.getTime();
        
        Version newVersion = new Version();
        newVersion.setEquipment(equipment);
        newVersion.setDeploySource("abc");
        newVersion.setVersionName("1.0.*");
        newVersion.setDeployTime(date);
        newVersion.setSaveTime(date);
        newVersion.setTargetVersion("2.x");
        return newVersion;
    }
    
    
}

package handlers;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import entity.Equipment;
import entity.Type;
import entity.Version;
import view.project.TreeViewPart;

public class NewProjectHandler extends AbstractHandler {
    Equipment equipment;
    Type type;
    Version version;
    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IViewPart viewPart;
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");
        
        try {
            String secondaryId = String.valueOf((new Date()).getTime());
//            viewPart = window.getActivePage().showView("RAP-FirstProject.projectPropertiesViewPart");
            viewPart = window.getActivePage().showView("RAP-FirstProject.projectPropertiesViewPart", secondaryId, IWorkbenchPage.VIEW_ACTIVATE);
          
            ISelection selection = window.getActivePage().getSelection("RAP-FirstProject.project");
            IStructuredSelection sselection = (IStructuredSelection) selection;
            Object element = sselection.getFirstElement();
            
            if(element instanceof Type){
                type = (Type) element;
                equipment = new Equipment();
                version = createNewVersion();
                equipment.setNameEquipment("Unknow");
                List<Version> versions = new ArrayList<>();
                versions.add(version);
                equipment.setVersions(versions);
                type.getEquipments().add(equipment);
                treeViewPart.treeViewer.refresh();
            }
            
            if(element instanceof Equipment){
                equipment = (Equipment) element;
                type = equipment.getType();
                equipment = new Equipment();
                version = createNewVersion();
                equipment.setNameEquipment("Unknow");
                List<Version> versions = new ArrayList<>();
                versions.add(version);
                equipment.setVersions(versions);
                type.getEquipments().add(equipment);
                treeViewPart.treeViewer.refresh();
            }
            
            if(element instanceof Version){
                version = (Version) element;
                equipment = version.getEquipment();
                type = equipment.getType();
                equipment = new Equipment();
                
                version = createNewVersion();
                equipment.setNameEquipment("Unknow");
                List<Version> versions = new ArrayList<>();
                versions.add(version);
                equipment.setVersions(versions);
                type.getEquipments().add(equipment);
                treeViewPart.treeViewer.refresh();
        }
        } catch (PartInitException e) {
            //
            e.printStackTrace();
        }
        return null;
    }
    
    private Version createNewVersion() {
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 10, 10, 15, 20, 30);
        Date date = cal.getTime();
        
        Version newVersion = new Version();
        newVersion.setDeploySource("abc");
        newVersion.setVersionName("1.0.*");
        newVersion.setDeployTime(date);
        newVersion.setSaveTime(date);
        newVersion.setTargetVersion("2.x");
        return newVersion;
    }

  } 
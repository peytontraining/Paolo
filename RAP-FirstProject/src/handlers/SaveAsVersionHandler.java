package handlers;



import java.util.ArrayList;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import entity.Device;
import entity.Equipment;
import entity.Type;
import entity.Version;
import view.project.TreeViewPart;

public class SaveAsVersionHandler extends AbstractHandler {

    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        Version version;
        Version cloneVersion;
        Equipment equipment;
//        Type type;
        EquipmentModel equipmentModel;
        
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");
        ISelection selection =window.getActivePage().getSelection("RAP-FirstProject.project");
        IStructuredSelection sselection = (IStructuredSelection) selection;
        Object element = sselection.getFirstElement();
        
        if(element instanceof Version){
             version = (Version) element;
             equipment = version.getEquipment();
             InputDialog dialog = new InputDialog(window.getShell(), "Save as", "Enter new vesion clone", version.getVersionName(), new IInputValidator() {
                @Override
                public String isValid(String newText) {
                    return null;
                }
            });
             
             cloneVersion = new Version();
             
            if (dialog.OK == dialog.open()) {
//                cloneVersion = version;
                cloneVersion.setEquipment(version.getEquipment());
                cloneVersion.setVersionName(dialog.getValue());
                cloneVersion.setDeploySource(version.getDeploySource());
                cloneVersion.setDeployTime(version.getDeployTime());
                cloneVersion.setSaveTime(version.getSaveTime());
                cloneVersion.setTargetVersion(version.getTargetVersion());
                
                List<Device> listDevices = new ArrayList<Device>();
                for (Device device : version.getDevices()){
                    device.setVersionBean(cloneVersion);
                    device.setIdDevice(0);
                    listDevices.add(device);
                }
                
                cloneVersion.setDevices(listDevices);
                equipment.getVersions().add(cloneVersion);
                
//              Insert new version to DB
                equipmentModel = new EquipmentModel();
                equipmentModel.editEntity(cloneVersion);
                treeViewPart.treeViewer.refresh(equipment);
            }
        }
      return null;
        }
  } 
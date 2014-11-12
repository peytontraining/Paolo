package handlers;

import model.EquipmentModel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import entity.Equipment;
import entity.Type;
import entity.Version;
import view.project.TreeViewPart;

public class DeleteVersionHandler extends AbstractHandler {
    Version version ;
    Equipment equipment;
    Type type;
    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");

        ISelection selection = window.getActivePage().getSelection("RAP-FirstProject.project");
        IStructuredSelection sselection = (IStructuredSelection) selection;
        Object element = sselection.getFirstElement();
        
        EquipmentModel equipmentModel = new EquipmentModel();
        if (element instanceof Version) {
            version = (Version) element;
            equipmentModel.deleteVersion(version);
            equipment = version.getEquipment();
            
            MessageDialog dg = new MessageDialog(
                    window.getShell(),
                    "My title",
                    null,
                    "Do you want to delete this version?",
                    MessageDialog.QUESTION_WITH_CANCEL, 
                    new String[]{
                        "YES", 
                        "NO", 
                        "CANCEL"},
                    0
                    );
//            dg.open();
            switch(dg.open()) {
            case 0: 
                //yes
                equipment.removeVersion(version);
                treeViewPart.treeViewer.refresh();
                System.out.println("yes");
                break;
            case 1:
                //no
                System.out.println("no");
                break;
            case 2:
                //cancel
                dg.close();
                System.out.println("cancel");
                break;
            }
            
        
        }
        
        if(element instanceof Equipment){
            equipment = (Equipment) element;
            equipmentModel.deleteEquipment(equipment);
            type = equipment.getType();
            type.removeEquipment(equipment);
            treeViewPart.treeViewer.refresh();
        }
        
        return null;
    }
  } 
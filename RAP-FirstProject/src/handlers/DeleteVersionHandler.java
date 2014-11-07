package handlers;

import model.EquipmentModel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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
            equipment.removeVersion(version);
            treeViewPart.treeViewer.refresh();
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
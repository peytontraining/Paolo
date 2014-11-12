package handlers;



import java.util.ArrayList;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import entity.Device;
import entity.Equipment;
import entity.Type;
import entity.Version;
import view.project.TreeViewPart;

public class SaveDeviceHandler extends AbstractHandler {

    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final IWorkbenchWindow window = HandlerUtil
                .getActiveWorkbenchWindowChecked(event);
        MessageDialog dg = new MessageDialog(
                window.getShell(),
                "Save",
                null,
                "Do you want to change device?",
                MessageDialog.QUESTION_WITH_CANCEL, 
                new String[]{
                    "YES", 
                    "CANCEL"},
                0
                );
//        dg.open();
        switch(dg.open()) {
        case 0: 
            //yes
            System.out.println("yes");
            break;
        case 1:
            //cancel
            dg.close();
            System.out.println("cancel");
            break;
        }
        return null;
    }
}
/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package handlers;

import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import view.template.DetailDeviceTemplateView;
import dialog.AddDeviceTemplateDialog;
import dialog.NewDeviceView;

    public class AddDeviceTemplateHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        AddDeviceTemplateDialog dialog = new AddDeviceTemplateDialog(window.getShell());
        if (dialog.open() == Window.OK && dialog.getDevice()!= null) {
                try {
                    window.getActivePage().showView("RAP-FirstProject.newDevice");
                    NewDeviceView newDeviceView = (NewDeviceView) window.getActivePage()
                            .findViewReference("RAP-FirstProject.newDevice").getView(true);
                    newDeviceView.setData(dialog.getDevice());
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}

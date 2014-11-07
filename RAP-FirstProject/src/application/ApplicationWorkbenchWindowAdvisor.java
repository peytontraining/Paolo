package application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * Configures the initial size and appearance of a workbench window.
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }


    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 400));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
        configurer.setShowMenuBar(false);
        configurer.setTitle("RAP First Project");
        configurer.setShowPerspectiveBar(true);
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.PERSPECTIVE_BAR_EXTRAS, Perspective.ID + ", " + TemplatePerspective.ID + ", " + LogoutPerspective.ID);
    }
    
    @Override
    public void postWindowOpen() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
        .setFullScreen(true);
    }
    
    @Override
    public boolean isDurableFolder(String perspectiveId, String folderId) {
        return true;
    }
}

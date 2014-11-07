/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class LogoutPerspective implements IPerspectiveFactory {
    final static String  ID = "RAP-FirstProject.logoutPerspective";

    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        
        IFolderLayout topLeft = layout.createFolder( "topLeft",IPageLayout.LEFT,0.4f,editorArea );
        topLeft.addView("RAP-FirstProject.project");
        topLeft.addView("RAP-FirstProject.drivers");
        topLeft.addView("RAP-FirstProject.areas");
        
        layout.addStandaloneView("RAP-FirstProject.VersionPropertiesView", false, IPageLayout.BOTTOM,0.6f, "topLeft");

        IFolderLayout bottomRight = layout.createFolder( "bottom", IPageLayout.BOTTOM,0.60f,editorArea );        
        IFolderLayout topRight = layout.createFolder( "topRight",IPageLayout.RIGHT, 0.60f,editorArea );
        topRight.addView("RAP-FirstProject.deviceSelection");
        topRight.addView("RAP-FirstProject.services");
        topRight.addView("RAP-FirstProject.scenes");
        topRight.addView("RAP-FirstProject.rules");
        layout.addPerspectiveShortcut("RAP-FirstProject.planning");
        layout.addPerspectiveShortcut("RAP-FirstProject.perspective");        
    }
}

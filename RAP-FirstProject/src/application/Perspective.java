/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class Perspective implements IPerspectiveFactory {
    final static String  ID = "RAP-FirstProject.projectPerspective";
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        
        IFolderLayout topLeft = layout.createFolder( "topLeft",IPageLayout.LEFT,0.3f,editorArea );
        topLeft.addView("RAP-FirstProject.project");
        topLeft.addView("RAP-FirstProject.drivers");
        topLeft.addView("RAP-FirstProject.areas");
        
        layout.getViewLayout("RAP-FirstProject.project").setCloseable(false);
        layout.getViewLayout("RAP-FirstProject.drivers").setCloseable(false);
        layout.getViewLayout("RAP-FirstProject.areas").setCloseable(false);
        
        IFolderLayout bottomLeft = layout.createFolder( "bottomLeft",IPageLayout.BOTTOM, 0.7f,"topLeft" );
        bottomLeft.addPlaceholder("RAP-FirstProject.projectPropertiesViewPart:*");
        bottomLeft.addView("RAP-FirstProject.VersionPropertiesView");
//        layout.addStandaloneView("RAP-FirstProject.VersionPropertiesView", false, IPageLayout.BOTTOM,0.6f, "topLeft");
//        layout.addStandaloneViewPlaceholder("RAP-FirstProject.projectPropertiesViewPart:*", IPageLayout.BOTTOM, 0.6f, "topLeft", false);
        
        IFolderLayout bottomRight = layout.createFolder( "bottom", IPageLayout.BOTTOM,0.5f,editorArea );
        bottomRight.addPlaceholder("RAP-FirstProject.detailDeviceView:*");
        IFolderLayout topRight = layout.createFolder( "topRight",IPageLayout.RIGHT, 0.7f,editorArea );
        
        topRight.addView("RAP-FirstProject.deviceSelection");
        topRight.addView("RAP-FirstProject.services");
        topRight.addView("RAP-FirstProject.scenes");
        
        topRight.addView("RAP-FirstProject.rules");
        layout.addPerspectiveShortcut("RAP-FirstProject.planning");
        layout.addPerspectiveShortcut("RAP-FirstProject.perspective");
        
    }
}

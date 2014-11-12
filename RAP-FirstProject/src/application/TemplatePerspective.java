/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class TemplatePerspective implements IPerspectiveFactory {
    final static String  ID = "RAP-FirstProject.templatePerspective";
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        
        IFolderLayout topLeft = layout.createFolder( "topLeft",IPageLayout.LEFT,0.3f,editorArea );
        topLeft.addView("RAP-FirstProject.areas");
        
        IFolderLayout bottomRight = layout.createFolder( "bottom", IPageLayout.BOTTOM,0.60f,editorArea );
//        bottomRight.addView("RAP-FirstProject.detailDeviceTemplateView");
        bottomRight.addPlaceholder("RAP-FirstProject.detailDeviceTemplateView:*");
        
        IFolderLayout topRight = layout.createFolder( "topRight",IPageLayout.RIGHT, 0.60f,editorArea );
        topRight.addView("RAP-FirstProject.devicesTemplatesView");
//        topRight.addView("RAP-FirstProject.devicesTemplatesView");
//        topRight.addView("RAP-FirstProject.services");
//        topRight.addView("RAP-FirstProject.scenes");
//        topRight.addView("RAP-FirstProject.rules");
//        layout.addPerspectiveShortcut("RAP-FirstProject.projectsPerspective");
    }
}

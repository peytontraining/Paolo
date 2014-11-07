/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package view.project;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord.NULL;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.Equipment;
import entity.Version;
import model.EquipmentModel;

/*-- View Properties Of Version --*/
public class VersionPropertiesView extends ViewPart{
    ToolItem saveItem;
    EquipmentModel equipmentModel;
    static Text versionText;
    static Text projectText;
    static Text deployTimeText;
    static Text deploySourceText;
    static Text saveTimeText;
    static Text targetVersionText;

    String versionString;
    String project;
    String deployTime;
    String deploySource;
    String saveTime;
    String targetVersion;
    
    boolean isFirst = false;
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/save_as.png").createImage();
    
    ScrolledForm form;
    SimpleDateFormat dateformatJava = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Version version;
    @Override
    public void createPartControl(Composite parent) {
        
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
        ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        saveItem = new ToolItem(toolBar, SWT.PUSH);
        saveItem.setImage(SAVE_IMAGE);
        saveItem.setEnabled(false);
        saveItem.addSelectionListener(new SelectionAdapter(){
        public void widgetSelected( final SelectionEvent event ) {
              
              equipmentModel = new EquipmentModel();
              version.setVersionName(versionText.getText());
              equipmentModel.editEntity(version);
              IWorkbenchWindow window = getSite().getWorkbenchWindow();
              TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");
              treeViewPart.treeViewer.refresh();
        }
        });
        
        section.setText("Version Properties");
        section.setTextClient(toolBar);
        
        form = toolkit.createScrolledForm(section);
        section.setClient(form);
        form.setExpandHorizontal(true);
        toolkit.decorateFormHeading(form.getForm());
        form.getBody().setLayout(new GridLayout(2, false));
        
        /* Create labels and text fields */
        //Version
        GridData textGridData = new GridData(SWT.FILL, SWT.NONE, true, false,
              1, 1);
        toolkit.createLabel(form.getBody(), "Version");
        versionText = toolkit.createText(form.getBody(), "", SWT.NONE);        
        versionText.setLayoutData(textGridData);
        
        //Project
        toolkit.createLabel(form.getBody(), "Project");
        projectText = toolkit.createText(form.getBody(), "", SWT.NONE);
        projectText.setLayoutData(textGridData);

        //Deploy Time
        toolkit.createLabel(form.getBody(), "Deploy Time");
        deployTimeText = toolkit.createText(form.getBody(), "", SWT.NONE);
        deployTimeText.setLayoutData(textGridData);
        
        //Deploy Source
        toolkit.createLabel(form.getBody(), "Deploy Source");
        deploySourceText = toolkit.createText(form.getBody(), "", SWT.NONE);
        deploySourceText.setLayoutData(textGridData);
        
        //Save Time
        toolkit.createLabel(form.getBody(), "Save Time");
        saveTimeText = toolkit.createText(form.getBody(), "", SWT.NONE);
        saveTimeText.setLayoutData(textGridData);

        //Target Version
        toolkit.createLabel(form.getBody(), "Target Version");
        targetVersionText = toolkit.createText(form.getBody(), "",
              SWT.NONE);
        targetVersionText.setLayoutData(textGridData);
        
        versionText.addModifyListener(new ModifyListener() {
          /**
           * Set Enalbled for Image Save when modify version Text
           */
          private static final long serialVersionUID = 5316574315246156511L;

          @Override
          public void modifyText(ModifyEvent event) {
                IWorkbenchWindow window = getSite().getWorkbenchWindow();
                TreeViewPart treeViewPart = (TreeViewPart)window.getActivePage().findView("RAP-FirstProject.project");
                final ControlDecoration dec = new ControlDecoration(versionText, SWT.TOP | SWT.LEFT);
                dec.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR));
//                        dec.setShowOnlyOnFocus(true);
                dec.hide();
                saveItem.setEnabled(true);
                if(isFirst == true){
                            List<Version> versions = version.getEquipment().getVersions();
////                            versions.remove(version);
//                            versions.remove(version);
                            for (Version v : versions) {
//                                System.out.println("name:"+v.getVersionName());
                                if(v.getVersionName().equals(versionText.getText())){
                                    dec.show();
//                                    versions.remove(version);
                                    break;
                                }
                            }
                }
          }
        });
        
        createSelectionListener(parent);
    }
    
    private void createSelectionListener(final Composite parent) {
        IWorkbenchWindow window = PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow();
        ISelectionService selectionService = window.getSelectionService();
        selectionService.addSelectionListener(new ISelectionListener() {
          @Override
          public void selectionChanged(IWorkbenchPart part, ISelection selection) {
              IStructuredSelection structuredSelection = (IStructuredSelection) selection;
              Object element = structuredSelection.getFirstElement();
              if(element != null){
                if(element instanceof Version){
//                      getViewSite().getPage().hideView(.this)
                    version = (Version) element;
                    //Get data
                    versionString = version.getVersionName();
                    deployTime =  dateformatJava.format(version.getDeployTime());
                    deploySource = version.getDeploySource();
                    saveTime = dateformatJava.format(version.getSaveTime());
                    targetVersion = version.getTargetVersion();
                    setData();
                    parent.setVisible(true);
                }
              }
              
              else{
                parent.setVisible(false);
              }
          }
          
        private void setData() {
              //Set data to Text
              versionText.setText(versionString);
              projectText.setText(version.getEquipment().getNameEquipment());
              
              if(deployTime != null)
              {
                deployTimeText.setText(deployTime);
              } else deployTimeText.setText("");
              
              if(saveTime != null)
              {
                saveTimeText.setText(saveTime);
              } else saveTimeText.setText("");
              
              //Set enable for text
              projectText.setEnabled(false);
              deployTimeText.setEnabled(false);
              saveTimeText.setEnabled(false);
              targetVersionText.setEnabled(false);
              saveItem.setEnabled(false);
              
              isFirst = true;
        }
        });
    }
    
    @Override
    public void setFocus(){
    }
}

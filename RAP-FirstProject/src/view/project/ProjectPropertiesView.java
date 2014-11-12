package view.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.Equipment;
import entity.Type;
import entity.Version;

public class ProjectPropertiesView extends ViewPart{
    static Text nameText;
    private Button gatewayUserButton1;
    private Button gatewayUserButton2;
    
    static Text gatewayHostText;
    static Text gatewayPortext;
    static Text gatewayUUIDText;
    static Text liscenseText;
    static Text multiText;
    
    static Button deploymentLockedButton;
    
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/save_as.png").createImage();
    ScrolledForm form;    
    Equipment equipment;
    Type type ;
    Version version;
    
    @Override
    public void createPartControl(Composite parent) {
//        getViewSite().getPage().hideView(.this);
//        final TreeViewPart treeViewPart = new TreeViewPart();
        final TreeViewPart treeViewPart = (TreeViewPart) getSite().getPage().findView("RAP-FirstProject.project");
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
        ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        item.setImage(SAVE_IMAGE);
        
        item.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected( final SelectionEvent event ) {
               EquipmentModel equipmentModel = new EquipmentModel();
               type = new Type();
               ISelection selection = getSite().getPage().getSelection("RAP-FirstProject.project");
               IStructuredSelection sselection = (IStructuredSelection) selection;
               Object element = sselection.getFirstElement();
               equipment = new Equipment();
               if(element instanceof Type){
                   type = (Type) element; 
                   equipment.setNameEquipment(nameText.getText());
                   equipment.setType(type);
                   
                   version = createNewVersion(equipment);
                   List<Version> versions = new ArrayList<>();
                   versions.add(version);
                   equipment.setVersions(versions);
                   
                   type.getEquipments().add(equipment);
                   equipmentModel.editEntity(equipment);
                   treeViewPart.treeViewer.refresh();
               } 
               
               if(element instanceof Equipment){
                   equipment = (Equipment) element;
                   type = equipment.getType();
                   Equipment e = new Equipment();
                   e.setNameEquipment(nameText.getText());
                   e.setType(type);

                   version = createNewVersion(equipment);
                   List<Version> versions = new ArrayList<>();
                   versions.add(version);
                   e.setVersions(versions);
                   type.getEquipments().add(e);
                   equipmentModel.editEntity(e);
                   treeViewPart.treeViewer.refresh();
               }
               
               if(element instanceof Version){
                   version = (Version) element;
                   equipment = version.getEquipment();
                   type = equipment.getType();
                   Equipment e2 = new Equipment();
                   e2.setNameEquipment(nameText.getText());
                   e2.setType(type);
                   
                   version = createNewVersion(equipment);
                   List<Version> versions = new ArrayList<>();
                   versions.add(version);
                   e2.setVersions(versions);
                   type.getEquipments().add(e2);
                   equipmentModel.editEntity(e2);
                   treeViewPart.treeViewer.refresh();
               }
            }
            
            private Version createNewVersion(Equipment equipment) {
                Calendar cal = Calendar.getInstance();
                cal.set(2014, 10, 10, 15, 20, 30);
                Date date = cal.getTime();
                Version newVersion = new Version();
                newVersion.setEquipment(equipment);
                newVersion.setDeploySource("abc");
                newVersion.setVersionName("1.0.*");
                newVersion.setDeployTime(date);
                newVersion.setSaveTime(date);
                newVersion.setTargetVersion("2.x");
//                newVersion.setDevice1s(device1s);
//                newVersion.setDevices(devices);
                return newVersion;
            }
        });
        
        section.setText("Project Properties");
        section.setTextClient(toolBar);
        
        form = toolkit.createScrolledForm(section);
        section.setClient(form);
        form.setExpandHorizontal(true);
        
        toolkit.decorateFormHeading(form.getForm());
        form.getBody().setLayout(new GridLayout(3, false));        
        GridData textGridData = new GridData(SWT.FILL, SWT.NONE, true, false,
                2, 1);
        GridData radioButtonData = new GridData(SWT.FILL, SWT.NONE, true, false,
                1, 1);
        
        //Name
        toolkit.createLabel(form.getBody(), "Name:");
        nameText = toolkit.createText(form.getBody(), "Unknow", SWT.NONE);
        nameText.setLayoutData(textGridData);
        
        GridData radioData = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        
        //Gateway uses
        toolkit.createLabel(form.getBody(), "Gateway uses:");
        gatewayUserButton1 = toolkit.createButton(form.getBody(), "UUID", SWT.RADIO);
        gatewayUserButton2 = toolkit.createButton(form.getBody(), "Host/Port", SWT.RADIO);
        
        //Gateway host
        toolkit.createLabel(form.getBody(), "Gateway Host:");
        gatewayHostText = toolkit.createText(form.getBody(), "", SWT.NONE);
        gatewayHostText.setLayoutData(textGridData);
        
        //Gateway port
        toolkit.createLabel(form.getBody(), "Gateway Port:");
        gatewayPortext = toolkit.createText(form.getBody(), "8080",SWT.NONE);
        gatewayPortext.setLayoutData(textGridData);
        
        //Gateway UUID
        toolkit.createLabel(form.getBody(), "Gateway UUID:");
        gatewayUUIDText = toolkit.createText(form.getBody(), "", SWT.NONE);        
        gatewayUUIDText.setLayoutData(textGridData);
        
        //License
        toolkit.createLabel(form.getBody(), "License:");
        liscenseText = toolkit.createText(form.getBody(), "");
        liscenseText.setLayoutData(textGridData);
        
        //Deployment locked
        deploymentLockedButton = toolkit.createButton(form.getBody(), "Deployment locked", SWT.CHECK);
        multiText = toolkit.createText(form.getBody(), "",SWT.MULTI | SWT.BORDER );
//        multiText.setLayoutData( new RowData( 80, 60 ) );
//        multiText.setLayoutData(new RowData(80, 60));
    }
    
    @Override
    public void setFocus() {
    }
}

package view.template;

import model.EquipmentModel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import view.project.DeviceTableView;
import entity.Device;
import entity.Device1;

public class DetailDeviceTemplateView1 extends ViewPart implements ISaveablePart{

    private GridLayout gridLayout;
    private ToolItem detailToolItem;
    private TabFolder tabFolder;
    private ToolBar detailToolBar;
    private Composite newComposite;
    private GridData gridData;
    
    private Button certifiedCheckBtn;
    private Button setBtn;
    private Button removeBtn;
    private Button editTypesBtn;
    private Button selectDriverButton;
    private Text nameText;
    private Text manufacturerText;
    private Text modelNumberText;
    private Text notesText;
    private Label typesLabel;
    private Label deviceDriverLabel;
    private Label icon;
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/save_as.png").createImage();

    private boolean isDirty;
    private boolean isFirst =false;
    @Override
    public void doSave(IProgressMonitor monitor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doSaveAs() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        // TODO Auto-generated method stub
        return false;
    }
    
    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
        firePropertyChange(PROP_DIRTY);
    }
    
    @Override
    public boolean isSaveOnCloseNeeded() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void createPartControl(Composite parent) {
        gridLayout = new GridLayout(1, false);
        parent.setLayout(gridLayout);
        createToolbar(parent);
        tabFolder = new TabFolder(parent, SWT.NONE);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
        tabFolder.setLayoutData(gridData);
        createDetailDeviceTemplateItem(tabFolder);
    }

    private void createDetailDeviceTemplateItem(Composite parent) {
        
        Display display = Display.getCurrent();
        Color bgColor = display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
        
        TabItem tabDetail = new TabItem(tabFolder, SWT.BORDER);
        tabDetail.setText("Details");
        tabDetail.setToolTipText("Detail Device Modify");
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm detailTemplateForm = toolkit.createScrolledForm(tabFolder);
        detailTemplateForm.getBody().setLayout(new GridLayout(4,false));
        
        //certified
        gridData = new GridData(SWT.LEFT, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailTemplateForm.getBody(), "Certified:");
        certifiedCheckBtn = toolkit.createButton(detailTemplateForm.getBody(), "", SWT.CHECK);
        certifiedCheckBtn.setLayoutData(gridData);
        
        //iconText
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 1, 1);
        toolkit.createLabel(detailTemplateForm.getBody(), "Icon:");
        icon = toolkit.createLabel(detailTemplateForm.getBody(), "");
        setBtn = toolkit.createButton(detailTemplateForm.getBody(), "set", SWT.PUSH);
        setBtn.setLayoutData(gridData);
        removeBtn = toolkit.createButton(detailTemplateForm.getBody(), "remove", SWT.NONE);
        removeBtn.setLayoutData(gridData);
        
        //nameText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailTemplateForm.getBody(), "Name:");
        nameText = toolkit.createText(detailTemplateForm.getBody(), "");
        nameText.setLayoutData(gridData);

        //manufacturerText;
        toolkit.createLabel(detailTemplateForm.getBody(), "Manufacturer:");
        manufacturerText = toolkit.createText(detailTemplateForm.getBody(), "");
        manufacturerText.setLayoutData(gridData);
        
        //modelNumberText
        toolkit.createLabel(detailTemplateForm.getBody(), "modelNumber:");
        modelNumberText = toolkit.createText(detailTemplateForm.getBody(), "");
        modelNumberText.setLayoutData(gridData);
        
        //typesText
        toolkit.createLabel(detailTemplateForm.getBody(), "Types:");
        Composite typesComposite =toolkit.createComposite(detailTemplateForm.getBody(), SWT.BORDER);
        typesComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        typesComposite.setLayout(new GridLayout(2, false));
        
        typesLabel = toolkit.createLabel(typesComposite, "");
        typesLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        editTypesBtn = toolkit.createButton(typesComposite, "Edit Types", SWT.PUSH);
        editTypesBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));
        
        //deviceDriver
        toolkit.createLabel(detailTemplateForm.getBody(), "Device Drive:");
        Composite driverComposite = toolkit.createComposite(detailTemplateForm.getBody(), SWT.BORDER);
        driverComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        driverComposite.setLayout(new GridLayout(2, false));
        
        deviceDriverLabel = toolkit.createLabel(driverComposite, "");
        deviceDriverLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        selectDriverButton = toolkit.createButton(driverComposite, "Select Driver", SWT.PUSH);
        selectDriverButton.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));
        
        //notesText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailTemplateForm.getBody(), "Notes:");
        notesText = toolkit.createText(detailTemplateForm.getBody(),"",SWT.MULTI);
        gridData.heightHint = notesText.getLineHeight() * 4;
        notesText.setLayoutData(gridData);
        
        modelNumberText.addModifyListener(new ModifyListener() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 6086165916707604597L;

            @Override
            public void modifyText(ModifyEvent event) {
                    setEnabledSave(isFirst);
            }
        });
        
        nameText.addModifyListener(new ModifyListener() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 6086165916707604597L;

            @Override
            public void modifyText(ModifyEvent event) {
                    setEnabledSave(isFirst);
            }
        });
        
        manufacturerText.addModifyListener(new ModifyListener() {
                
                /**
                 * 
                 */
                private static final long serialVersionUID = 6086165916707604597L;
    
                @Override
                public void modifyText(ModifyEvent event) {
                        setEnabledSave(isFirst);
                }
            });
    
        notesText.addModifyListener(new ModifyListener() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 6086165916707604597L;

            @Override
            public void modifyText(ModifyEvent event) {
                    setEnabledSave(isFirst);
            }
        });

        parent.setVisible(false);
        tabDetail.setControl(detailTemplateForm);
    }

    private void setEnabledSave(boolean isFirst) {
        if(isFirst == true){
          setDirty(true);
          detailToolItem.setEnabled(true);
      }
    }
    
    
    private void createToolbar(Composite parent) {
        // Create composite Toolbar and set layout
        Display display = Display.getCurrent();
        Color bgColor = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
        newComposite = new Composite(parent, SWT.NONE);
        
        gridLayout = new GridLayout(1, false);
        newComposite.setLayout(gridLayout);
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        newComposite.setLayoutData(gridData);
        
        newComposite.setBackground(bgColor);
        
        // Create Toolbar
        gridData = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        detailToolBar = new ToolBar(newComposite, SWT.FLAT);
        detailToolBar.setLayoutData(gridData);
        
        // Create Item
        detailToolItem = new ToolItem(detailToolBar, SWT.PUSH);
        detailToolItem.setImage(SAVE_IMAGE);
        detailToolItem.setToolTipText("Save (Ctrl + S)");
        detailToolItem.setEnabled(false);
        detailToolItem.addSelectionListener(new SelectionAdapter() {
            private static final long serialVersionUID = -102212312093090431L;
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                IWorkbenchWindow window = getSite().getWorkbenchWindow();
                MessageDialog dg = new MessageDialog(window.getShell(),"Save Resource",null,
                        "Device has been modified. Save changes",MessageDialog.QUESTION_WITH_CANCEL, 
                        new String[]{"YES", "CANCEL"},0
                        );
//                dg.open();
                switch(dg.open()) {
                case 0: 
                    //yes
                    DevicesTemplatesTableView devicesTemplatesTableView = (DevicesTemplatesTableView)window.getActivePage().findView("RAP-FirstProject.devicesTemplatesView");
                    ISelection selection = window.getActivePage().getSelection("RAP-FirstProject.devicesTemplatesView");
                    IStructuredSelection sselection = (IStructuredSelection) selection;
                    Object element = sselection.getFirstElement();
//                    
                    if(element instanceof Device1){
                        Device1 device = (Device1) element;
                        device.setName1(nameText.getText());
                        device.setManufacturer1(manufacturerText.getText());
                        device.setModelNumber1(modelNumberText.getText());
                        device.setNotes(notesText.getText());
                        EquipmentModel equipmentModel = new EquipmentModel();
                        equipmentModel.editEntity(device);
                        devicesTemplatesTableView.m_treeViewer.refresh();
                    }
                    System.out.println("OK");
                    break;
                case 1:
                    //cancel
                    System.out.println("cancel");
                    dg.close();
                    break;
                }
            }
        });
    }

    public void setData(Device1 device) {
        isFirst = false;
        setPartName(device.getName1());
        nameText.setText(device.getName1());
        manufacturerText.setText(device.getManufacturer1());
        modelNumberText.setText(device.getModelNumber1());
        typesLabel.setText( device.getDeviceType().getNameDeviceType());
        deviceDriverLabel.setText(device.getDriverBean().getNameDriver());
        if(device.getNotes() == null){
            notesText.setText("");
        } else 
            notesText.setText(device.getNotes());
        tabFolder.setVisible(true);
        isFirst = true;
    }
    
    @Override
    public void setFocus() {
        
    }

}

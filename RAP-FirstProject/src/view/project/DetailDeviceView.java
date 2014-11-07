package view.project;

import java.util.List;

import model.EquipmentModel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entity.AppModule;
import entity.Configure;
import entity.Device;
import entity.Device1;

public class DetailDeviceView extends ViewPart implements ISaveablePart{
    
    private GridLayout gridLayout;
    private TabFolder tabFolder;
    private Button setBtn;
    private Text nameText;
    private Text manufacturerText;
    private Text modelNumberText;
    private Text masterTemplateText;
    private Text notesText;
    private Text versionText;
    private Text typesText;
    private Label icon;
    private ToolItem detailToolItem;
    private ToolBar detailToolBar;
    private Composite newComposite;
    private GridData gridData;

    private Table table;
    private TableViewer tableViewer;
    private static final String[] columnProperties = { "Name", "Value", "Mandotary", "Description"};
    public final Image CAMERA_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/camera.png").createImage();
    public final Image FIREPLACE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject","/icons/fireplace-controller.png").createImage();
    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/save_as.png").createImage();

    private boolean isDirty,saveAsAllowed;
    private boolean isFirst = false;
    @Override
    public void createPartControl(Composite parent) {
        gridLayout = new GridLayout(1, false);
        parent.setLayout(gridLayout);
        createToolbar(parent);
        tabFolder = new TabFolder(parent, SWT.NONE);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
        tabFolder.setLayoutData(gridData);
        createDetailDeviceItem(tabFolder);
        createConfigureDeviceItem(tabFolder);
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
                        new String[]{"YES", "NO", "CANCEL"},0
                        );
//                dg.open();
                switch(dg.open()) {
                case 0: 
                    //yes
                    DeviceTableView deviceTableView = (DeviceTableView)window.getActivePage().findView("RAP-FirstProject.deviceSelection");                
                    ISelection selection = window.getActivePage().getSelection("RAP-FirstProject.deviceSelection");
                    IStructuredSelection sselection = (IStructuredSelection) selection;
                    Object element = sselection.getFirstElement();
                    
                    if(element instanceof Device){
                        Device device = (Device) element;
                        device.setName(nameText.getText());
                        device.setNote(notesText.getText());
                        EquipmentModel equipmentModel = new EquipmentModel();
                        equipmentModel.editEntity(device);
                        deviceTableView.tableViewer.refresh();
                    }
                    
                    break;
                case 1:
                    //no
                    dg.close();
                    
                    break;
                case 2:
                    //cancel
                    dg.close();
                    break;
                }
            }
        });
    }

    private void createConfigureDeviceItem(Composite parent) {
        TabItem tabConfig = new TabItem(tabFolder, SWT.BORDER);
        tabConfig.setText("Configure");
        tabConfig.setToolTipText("Config Device");
        
        tableViewer = new TableViewer(tabFolder, SWT.MULTI | SWT.BORDER);
        table = tableViewer.getTable();
        tableViewer.setContentProvider(new ConfigTableViewContentProvider());
        tableViewer.setLabelProvider(new ConfigTableViewLabelProvider());
        tableViewer.setColumnProperties(initColumnProperties(table));

        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setForeground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_BLUE));
        getSite().setSelectionProvider(tableViewer);
        parent.setVisible(false);
        tabConfig.setControl(tableViewer.getTable());
    }
    
    private String[] initColumnProperties(Table tbl) {
        int numberOfColumn = columnProperties.length;
        for (int i = 0; i < numberOfColumn; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(columnProperties[i]);
            column.setWidth(200);
            column.pack();
        }
        return columnProperties;
    }
    
    private void createDetailDeviceItem(Composite parent) {
        
        Display display = Display.getCurrent();
        Color bgColor = display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
        
        TabItem tabDetail = new TabItem(tabFolder, SWT.BORDER);
        tabDetail.setText("Details");
        tabDetail.setToolTipText("Detail Device Modify");
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm detailForm = toolkit.createScrolledForm(tabFolder);
        detailForm.getBody().setLayout(new GridLayout(4,false));
        
        //iconText
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 2, 1);
        toolkit.createLabel(detailForm.getBody(), "Icon:");
        icon = toolkit.createLabel(detailForm.getBody(), "");
        setBtn = toolkit.createButton(detailForm.getBody(), "set", SWT.PUSH);
        setBtn.setLayoutData(gridData);
        
        //nameText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailForm.getBody(), "Name:");
        nameText = toolkit.createText(detailForm.getBody(), "");
        nameText.setLayoutData(gridData);
        
        //manufacturerText;
        toolkit.createLabel(detailForm.getBody(), "Manufacturer:");
        manufacturerText = toolkit.createText(detailForm.getBody(), "");
        manufacturerText.setLayoutData(gridData);
        manufacturerText.setEditable(false);
        manufacturerText.setBackground(bgColor);
        
        //modelNumberText
        toolkit.createLabel(detailForm.getBody(), "ModelNumber:");
        modelNumberText = toolkit.createText(detailForm.getBody(), "");
        modelNumberText.setLayoutData(gridData);
        modelNumberText.setEditable(false);
        modelNumberText.setBackground(bgColor);

        //masterTemplateText
        toolkit.createLabel(detailForm.getBody(), "Master Template:");
        masterTemplateText = toolkit.createText(detailForm.getBody(), "");
        masterTemplateText.setLayoutData(gridData);
        masterTemplateText.setEditable(false);
        masterTemplateText.setBackground(bgColor);
        
        //typesText
        toolkit.createLabel(detailForm.getBody(), "Types text:");
        typesText = toolkit.createText(detailForm.getBody(), "");
        typesText.setLayoutData(gridData);
        typesText.setEditable(false);
        typesText.setBackground(bgColor);
        
        //notesText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailForm.getBody(), "Notes:");
        notesText = toolkit.createText(detailForm.getBody(),"",SWT.MULTI);
        gridData.heightHint = notesText.getLineHeight() * 4;
        notesText.setLayoutData(gridData);
        
        //versionText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(detailForm.getBody(), "Version:");
        versionText = toolkit.createText(detailForm.getBody(),"",SWT.MULTI);
        versionText.setLayoutData(gridData);
        versionText.setEditable(false);
        versionText.setBackground(bgColor);
        
        nameText.addModifyListener(new ModifyListener() {
            /**
             * Display * when modifying
             */
            private static final long serialVersionUID = -8934067289077033693L;
            @Override
            public void modifyText(ModifyEvent event) {
                if(isFirst == true){
                    setDirty(true);
                    detailToolItem.setEnabled(true);
                }
            }
        });
        
        parent.setVisible(false);
        tabDetail.setControl(detailForm);
    }
    
    public void setData(Device device) {
        List<Configure> configures = device.getConfigures();
        tableViewer.setInput(configures);
      
        nameText.setText(device.getName());
        manufacturerText.setText(device.getManufacturer());
        if(device.getModelNumber() == null){
            modelNumberText.setText("");
        } else
            modelNumberText.setText(device.getModelNumber());
            typesText.setText( device.getDeviceType());
        
        if(device.getDevice1() == null){
            masterTemplateText.setText("");
        }else{
                masterTemplateText.setText(device.getDevice1().getName1());
                super.setPartName(device.getDevice1().getName1());
                super.setTitleImage(CAMERA_IMAGE);
                }
        if(device.getNote()== null){
            notesText.setText("");
        }else
            notesText.setText(device.getNote());
        versionText.setText(device.getVersionBean().getVersionName());
        tabFolder.setVisible(true);
        isFirst = true;
//        return device;
    }
    
    @Override
    public void setFocus() {
    }
    
    @Override
    public void doSave(IProgressMonitor monitor) {
        
    }
    
    @Override
    public void doSaveAs() {
        
    }

    
    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }
    @Override
    public boolean isSaveOnCloseNeeded() {
        return false;
    }
    

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
        firePropertyChange(PROP_DIRTY);
    }
    public void setSaveAsAllowed(boolean isSaveAsAllowed) {
        this.saveAsAllowed = isSaveAsAllowed;
    }
}

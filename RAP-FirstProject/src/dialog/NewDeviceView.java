package dialog;

//import javax.swing.plaf.FileChooserUI;
import java.util.ArrayList;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import view.project.DeviceTableView;
import view.project.TreeViewPart;
import entity.AppModule;
import entity.Device;
import entity.Device1;
import entity.Equipment;
import entity.Version;

public class NewDeviceView extends ViewPart {

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
    private static Device1 deviceTemplate;

    public final Image SAVE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(
            "RAP-FirstProject", "/icons/save_as.png").createImage();

    @Override
    public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Section section = toolkit.createSection(parent, Section.DESCRIPTION
                | Section.TITLE_BAR);
        ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        final ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        item.setImage(SAVE_IMAGE);
        item.addSelectionListener(new SelectionAdapter() {
            /**
             * 
             */
            private static final long serialVersionUID = 5452852162339717636L;
            public void widgetSelected(final SelectionEvent event) {
                ISelection selection = getSite().getWorkbenchWindow()
                        .getActivePage()
                        .getSelection("RAP-FirstProject.project");
                IStructuredSelection sselection = (IStructuredSelection) selection;
                Object element = sselection.getFirstElement();
                if (element != null) {
                    if (element instanceof Version) {
                        IWorkbenchWindow window = getSite().getWorkbenchWindow();
                        DeviceTableView deviceTableView = (DeviceTableView) window
                              .getActivePage().findView("RAP-FirstProject.deviceSelection");
                        
                        MessageDialog dg = new MessageDialog(
                                window.getShell(),
                                "My title",
                                null,
                                "Do you want to add add this device?",
                                MessageDialog.QUESTION_WITH_CANCEL, 
                                new String[]{
                                    "YES", 
                                    "CANCEL"},
                                0
                                );
                        switch(dg.open()) {
                        case 0: 
                            //yes
                            Version version = (Version) element;
                            Device device = new Device();
                            device.setDevice1(deviceTemplate);
                            device.setName(deviceTemplate.getName1());
                            device.setManufacturer(deviceTemplate
                                    .getManufacturer1());
                            device.setModelNumber(deviceTemplate.getModelNumber1());
                            device.setDeviceType(deviceTemplate.getDeviceType()
                                    .getNameDeviceType());
                            device.setVersionBean(version);
                            version.addDevice(device);

                            EquipmentModel equipmentModel = new EquipmentModel();
                            equipmentModel.editEntity(version);
                            deviceTableView.tableViewer.refresh();
                            break;
                            
                        case 1:
                            //cancel
                            dg.close();
                            System.out.println("cancel");
                            break;
                        }
             
                    }
                    if (element instanceof Equipment) {
                        IWorkbenchWindow window = getSite()
                                .getWorkbenchWindow();
                        MessageDialog.openInformation(window.getShell(),
                                "Dialog", "Choose version to add device!");
                    }
                }
            }
        });
        item.setEnabled(true);

        section.setText("Device");
        section.setTextClient(toolBar);
        ScrolledForm form = toolkit.createScrolledForm(section);
        section.setClient(form);
        form.setExpandHorizontal(true);
        toolkit.decorateFormHeading(form.getForm());
        form.getBody().setLayout(new GridLayout(4, false));
        GridData gridData;

        // certified
        gridData = new GridData(SWT.LEFT, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Certified:");
        certifiedCheckBtn = toolkit.createButton(form.getBody(), "", SWT.CHECK);
        certifiedCheckBtn.setLayoutData(gridData);

        // iconText
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 1, 1);
        toolkit.createLabel(form.getBody(), "Icon:");
        icon = toolkit.createLabel(form.getBody(), "");
        setBtn = toolkit.createButton(form.getBody(), "set", SWT.PUSH);
        setBtn.setLayoutData(gridData);
        removeBtn = toolkit.createButton(form.getBody(), "remove", SWT.NONE);
        removeBtn.setLayoutData(gridData);

        // nameText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Name:");
        nameText = toolkit.createText(form.getBody(), "");
        nameText.setLayoutData(gridData);

        // manufacturerText;
        toolkit.createLabel(form.getBody(), "Manufacturer:");
        manufacturerText = toolkit.createText(form.getBody(), "");
        manufacturerText.setLayoutData(gridData);

        // modelNumberText
        toolkit.createLabel(form.getBody(), "modelNumber:");
        modelNumberText = toolkit.createText(form.getBody(), "");
        modelNumberText.setLayoutData(gridData);

        // typesText
        toolkit.createLabel(form.getBody(), "Types:");
        Composite typesComposite = toolkit.createComposite(form.getBody(),
                SWT.BORDER);
        typesComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
                false, 3, 1));
        typesComposite.setLayout(new GridLayout(2, false));

        typesLabel = toolkit.createLabel(typesComposite, "");
        typesLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        editTypesBtn = toolkit.createButton(typesComposite, "Edit Types",
                SWT.PUSH);
        editTypesBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true,
                false));

        // deviceDriver
        toolkit.createLabel(form.getBody(), "Device Drive:");
        Composite driverComposite = toolkit.createComposite(form.getBody(),
                SWT.BORDER);
        driverComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
                false, 3, 1));
        driverComposite.setLayout(new GridLayout(2, false));

        deviceDriverLabel = toolkit.createLabel(driverComposite, "");
        deviceDriverLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
                false));

        selectDriverButton = toolkit.createButton(driverComposite,
                "Select Driver", SWT.PUSH);
        selectDriverButton.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE,
                true, false));

        // notesText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Notes:");
        notesText = toolkit.createText(form.getBody(), "", SWT.MULTI);
        gridData.heightHint = notesText.getLineHeight() * 4;
        notesText.setLayoutData(gridData);

    }

    @Override
    public void setFocus() {
    }

    public void setData(Device1 setDevice) {
        System.out.println("setdata");
        nameText.setText(setDevice.getName1());
        manufacturerText.setText(setDevice.getManufacturer1());
        modelNumberText.setText(setDevice.getModelNumber1());
        typesLabel.setText(setDevice.getDeviceType().getNameDeviceType());
        deviceDriverLabel.setText(setDevice.getDriverBean().getNameDriver());
        if (setDevice.getNotes() == null) {
            notesText.setText("");
        } else
            notesText.setText(setDevice.getNotes());
        deviceTemplate = setDevice;
    }
}

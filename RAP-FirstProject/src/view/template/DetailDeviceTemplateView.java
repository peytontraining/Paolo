package view.template;

//import javax.swing.plaf.FileChooserUI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.internal.part.Part;
import org.eclipse.ui.part.ViewPart;

import entity.AppModule;
import entity.Device1;

public class DetailDeviceTemplateView extends ViewPart{
    private Composite composite;
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
    
    @Override
    public void createPartControl(Composite parent) {
        
        composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new FillLayout() );
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm form = toolkit.createScrolledForm( composite );
        form.setExpandHorizontal(true);
//        form.getBody().setLayout( new TableWrapLayout() );
        form.getBody().setLayout(new GridLayout(4,false));
        GridData gridData;
        
        //certified
        gridData = new GridData(SWT.LEFT, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Certified:");
        certifiedCheckBtn = toolkit.createButton(form.getBody(), "", SWT.CHECK);
        certifiedCheckBtn.setLayoutData(gridData);
        
        //iconText
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false, 1, 1);
        toolkit.createLabel(form.getBody(), "Icon:");
        icon = toolkit.createLabel(form.getBody(), "");
        setBtn = toolkit.createButton(form.getBody(), "set", SWT.PUSH);
        setBtn.setLayoutData(gridData);
        removeBtn = toolkit.createButton(form.getBody(), "remove", SWT.NONE);
        removeBtn.setLayoutData(gridData);
        
        //nameText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Name:");
        nameText = toolkit.createText(form.getBody(), "");
        nameText.setLayoutData(gridData);

        //manufacturerText;
        toolkit.createLabel(form.getBody(), "Manufacturer:");
        manufacturerText = toolkit.createText(form.getBody(), "");
        manufacturerText.setLayoutData(gridData);
        
        //modelNumberText
        toolkit.createLabel(form.getBody(), "modelNumber:");
        modelNumberText = toolkit.createText(form.getBody(), "");
        modelNumberText.setLayoutData(gridData);
        
        //typesText
        toolkit.createLabel(form.getBody(), "Types:");
        Composite typesComposite =toolkit.createComposite(form.getBody(), SWT.BORDER);
        typesComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        typesComposite.setLayout(new GridLayout(2, false));
        
        typesLabel = toolkit.createLabel(typesComposite, "");
        typesLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        editTypesBtn = toolkit.createButton(typesComposite, "Edit Types", SWT.PUSH);
        editTypesBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));
        
        //deviceDriver
        toolkit.createLabel(form.getBody(), "Device Drive:");
        Composite driverComposite = toolkit.createComposite(form.getBody(), SWT.BORDER);
        driverComposite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
        driverComposite.setLayout(new GridLayout(2, false));
        
        deviceDriverLabel = toolkit.createLabel(driverComposite, "");
        deviceDriverLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
        
        selectDriverButton = toolkit.createButton(driverComposite, "Select Driver", SWT.PUSH);        
        selectDriverButton.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false));
        
        //notesText
        gridData = new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1);
        toolkit.createLabel(form.getBody(), "Notes:");
        notesText = toolkit.createText(form.getBody(),"",SWT.MULTI);
        gridData.heightHint = notesText.getLineHeight() * 4;
        notesText.setLayoutData(gridData);
        composite.setVisible(false);
//        createSelectionListener(parent);
    }
    
    private void createSelectionListener(final Composite parent) {
        ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
        selectionService.addSelectionListener(new ISelectionListener() {
            @Override
            public void selectionChanged(IWorkbenchPart part,
                    ISelection selection) {
              IStructuredSelection sselection = (IStructuredSelection) selection;
                Object firstElement = sselection.getFirstElement();
              if(firstElement instanceof Device1){
                  Device1 device = (Device1) firstElement;
                  setData(device);
                  parent.setVisible(true);
              }
            }
        });
    }
    @Override
    public void setFocus() {
    }
    
    public void setData(Device1 device) {
        nameText.setText(device.getName1());
        manufacturerText.setText(device.getManufacturer1());
        modelNumberText.setText(device.getModelNumber1());
        typesLabel.setText( device.getDeviceType().getNameDeviceType());
        deviceDriverLabel.setText(device.getDriverBean().getNameDriver());        
        if(device.getNotes() == null){
            notesText.setText("");
        } else 
            notesText.setText(device.getNotes());
        composite.setVisible(true);
    }
}

package dialog;


import java.util.List;

import model.ChildTreeModel;
import model.EquipmentModel;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import view.dialog.TemplateTreeViewContentProvider;
import view.dialog.TemplateTreeViewLabelProvider;
import view.project.TreeViewContentProvider;
import view.project.TreeViewLabelProvider;
import view.template.DeviceTemplateContentProvider;
import view.template.DeviceTemplateLabelProvider;
import entity.AppModule;
import entity.Device1;
import entity.Type;

public class AddDeviceTemplateDialog extends Dialog {
    private TreeViewer treeViewer;
    private Device1 device;
    private FilteredTree filterTree;
    
    public AddDeviceTemplateDialog(Shell parentShell) {
        super(parentShell);
    }
    
    public Device1 getDevice() {
        return device;
    }
    /**
     *
     */
    private static final long serialVersionUID = 7397661282248588566L;
    @Override
    protected Control createDialogArea(Composite parent){
        //Create composite
        Composite composite = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = new GridLayout(2, false);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        composite.setLayout(gridLayout);
        composite.setLayoutData(gridData);
        
        //Create section
        FormToolkit toolkit= new FormToolkit(parent.getDisplay());
        Section section = toolkit.createSection(composite, Section.TITLE_BAR);
        section.setText("Device Template");
        section.setLayout(new FillLayout());
        section.setLayoutData(gridData);
        
        //Create filter
        PatternFilter filter = new PatternFilter();
        filterTree = new FilteredTree(section,SWT.H_SCROLL | SWT.V_SCROLL, filter, true);
        section.setClient(filterTree);
        treeViewer = filterTree.getViewer();
        treeViewer.setContentProvider(new TemplateTreeViewContentProvider());
        treeViewer.setLabelProvider(new TemplateTreeViewLabelProvider());
        
        //Set data
        EquipmentModel equipmentModel = new EquipmentModel();
        List<AppModule> appModule = equipmentModel.getAllAppModule();
//        System.out.println("app module"+appModule);
        treeViewer.setInput(appModule);
        treeViewer.expandAll();
        
        //Create combo Communication Method
        toolkit.createLabel(composite, "Communication Method:");
        CCombo combo = new CCombo(composite, SWT.BORDER);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return composite;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Select Template");
    }
    
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, "OK", false);
        createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
    }
    
    @Override
    protected void okPressed() {
        Object firstElement = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
        if (firstElement instanceof Device1) {
            device = (Device1) firstElement;
        }
        super.okPressed();
    }
    
}

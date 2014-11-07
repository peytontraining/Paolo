package view.template;

import java.util.ArrayList;
import java.util.List;

import model.EquipmentModel;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import view.project.DetailDeviceView;
//import view.project.DeviceFilter;
import entity.*;
public class DevicesTemplatesTableView extends ViewPart implements IDoubleClickListener{
//    public static final String ID  = "TreeTableDemo.view";
    private TreeViewer m_treeViewer;
    private DeviceTemplateFilter filter;
    public static Text filterText;
    public static Text findText;
    
    public final Image UP_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/arrow_up.png").createImage();
    public final Image DOWN_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/arrow_down.png").createImage();
    @Override
    public void createPartControl(Composite parent) {
        GridLayout layout = new GridLayout(9, false);
        parent.setLayout(layout);
        GridData dataText = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        
        Label filterLabel = new Label(parent, SWT.NONE);
        filterLabel.setText("Filter: ");
        filterText = new Text(parent, SWT.BORDER | SWT.SEARCH);
        filterText.setLayoutData(dataText);
        Button btnErase = new Button(parent, SWT.PUSH);
        btnErase.setText("Erase");
        
        Label findLabel = new Label(parent, SWT.NONE);
        findLabel.setText("Find: ");
        findText = new Text(parent, SWT.BORDER | SWT.SEARCH);
        findText.setLayoutData(dataText);
        
        Button btnDown = new Button(parent, SWT.PUSH);
        btnDown.setImage(DOWN_IMAGE);
        
        Button btnUp = new Button(parent, SWT.PUSH);
        btnUp.setImage(UP_IMAGE);
        
        GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 9, 1);
        Tree deviceTree = new Tree(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        deviceTree.setLayoutData(data);
        deviceTree.setHeaderVisible(true);
        m_treeViewer = new TreeViewer(deviceTree);
        
        TreeColumn nameColumn = new TreeColumn(deviceTree, SWT.LEFT);
        deviceTree.setLinesVisible(true);
        
        nameColumn.setAlignment(SWT.LEFT);
        nameColumn.setText("Name");
        nameColumn.setWidth(270);
        
        TreeColumn lastModifiedColumn = new TreeColumn(deviceTree, SWT.LEFT);
        deviceTree.setLinesVisible(true);
        lastModifiedColumn.setAlignment(SWT.LEFT);
        lastModifiedColumn.setText("Last Modified");
        lastModifiedColumn.setWidth(170);
        
        TreeColumn manufacturerColumn = new TreeColumn(deviceTree, SWT.LEFT);
        deviceTree.setLinesVisible(true);
        manufacturerColumn.setAlignment(SWT.LEFT);
        manufacturerColumn.setText("Manufacturer");
        manufacturerColumn.setWidth(130);
        
        // "Module Model","Version","Needs Update"};
        TreeColumn modelNumberColumn = new TreeColumn(deviceTree, SWT.LEFT);
        deviceTree.setLinesVisible(true);
        modelNumberColumn.setAlignment(SWT.LEFT);
        modelNumberColumn.setText("Model Number");
        modelNumberColumn.setWidth(120);
        
        TreeColumn versionColumn = new TreeColumn(deviceTree, SWT.LEFT);
        deviceTree.setLinesVisible(true);
        versionColumn.setAlignment(SWT.LEFT);
        versionColumn.setText("Version");
        versionColumn.setWidth(200);
        
//        TreeColumn needsUpdateColumn = new TreeColumn(deviceTree, SWT.LEFT);
//        deviceTree.setLinesVisible(true);
//        needsUpdateColumn.setAlignment(SWT.LEFT);
//        needsUpdateColumn.setText("Land/Stadt");
//        needsUpdateColumn.setWidth(160);
        
        m_treeViewer.setContentProvider(new DeviceTemplateContentProvider());
        m_treeViewer.setLabelProvider(new DeviceTemplateLabelProvider());
        
        List<AppModule> listAppModule = new ArrayList<AppModule>();
        EquipmentModel equipmentModel = new EquipmentModel();
        listAppModule = equipmentModel.getAllAppModule();
        
        m_treeViewer.setInput(listAppModule);
        m_treeViewer.expandAll();
        
     // New to support the search
        filterText.addKeyListener(new KeyAdapter() {
                private static final long serialVersionUID = 1L;
                public void keyReleased(KeyEvent ke) {
                filter.setSearchText(filterText.getText());
                m_treeViewer.refresh();
                }
        });
        
        filter = new DeviceTemplateFilter();
        m_treeViewer.addFilter(filter);
        
        getSite().setSelectionProvider(m_treeViewer);
        m_treeViewer.addDoubleClickListener(this);

    }
    @Override
    public void setFocus() {
    }
    
    @Override
    public void doubleClick(DoubleClickEvent event) {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        ISelection selection = m_treeViewer.getSelection();
        IStructuredSelection sselection = (IStructuredSelection) selection;
        Object firstObject = sselection.getFirstElement();
        if(firstObject instanceof Device1){
            try {
                window.getActivePage().showView("RAP-FirstProject.detailDeviceTemplateView");
                DetailDeviceTemplateView detailDeviceTemplateView = (DetailDeviceTemplateView) window.getActivePage().findViewReference("RAP-FirstProject.detailDeviceTemplateView").getView(true);
                Device1 device = (Device1) firstObject;
                detailDeviceTemplateView.setData(device);
            
//            parent.setVisible(true);
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }
    }
}

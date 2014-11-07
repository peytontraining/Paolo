package view.project;



import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import view.dialog.NewDeviceView;
import entity.*;


public class DeviceTableView extends ViewPart implements
        IDoubleClickListener {

    private static final String[] columnProperties = { "Device Type", "Name", "Rooms", "Status", "Control Type"};
    public Table table;
    public TableViewer tableViewer;
    private Text txtFilter, txtFind;
    
    public final Image UP_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/arrow_up.png").createImage();
    public final Image DOWN_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/arrow_down.png").createImage();
    public final Image CAMERA_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject", "/icons/camera.png").createImage();
    public final Image FIREPLACE_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin("RAP-FirstProject","/icons/fireplace-controller.png").createImage();

    

    @Override
    public void createPartControl(Composite parent) {
        
        parent.setLayout(new GridLayout(8, false));
        /* Control filter and find */
        Label filterLabel = new Label(parent, SWT.NONE);
        filterLabel.setText("Filter: ");
        txtFilter = new Text(parent, SWT.BORDER | SWT.SEARCH);
        txtFilter.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false,
                2, 1));

        Label findLabel = new Label(parent, SWT.NONE);
        findLabel.setText("Find: ");
        txtFind = new Text(parent, SWT.BORDER | SWT.SEARCH);
        txtFind.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2,
                1));
        Button up = new Button(parent, SWT.NONE);
        up.setImage(UP_IMAGE);
        Button down = new Button(parent, SWT.NONE);
        down.setImage(DOWN_IMAGE);

        /* Table Viewer */
        tableViewer = new TableViewer(parent, SWT.MULTI | SWT.BORDER);
        table = tableViewer.getTable();
        tableViewer.setContentProvider(new DeviceTableViewContentProvider());
        tableViewer.setLabelProvider(new DeviceTableViewLabelProvider());
        tableViewer.setColumnProperties(initColumnProperties(table));

        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setForeground(getSite().getShell().getDisplay()
                .getSystemColor(SWT.COLOR_BLUE));
        getSite().setSelectionProvider(tableViewer);

        tableViewer.addDoubleClickListener(this);

        /* Filter */
        final Filter filter = new Filter();
        txtFilter.addModifyListener(new ModifyListener() {

            private static final long serialVersionUID = 4906617449904102933L;

            @Override
            public void modifyText(ModifyEvent event) {
                filter.setFilterString(txtFilter.getText());
                tableViewer.refresh();
            }
        });
        tableViewer.addFilter(filter);

        /* Find */
        txtFind.addModifyListener(new ModifyListener() {

            private static final long serialVersionUID = -1718429326062300859L;

            @Override
            public void modifyText(ModifyEvent event) {
                find(txtFind.getText());
            }
        });
        txtFind.addKeyListener(new KeyAdapter() {

            private static final long serialVersionUID = -5866801124911784172L;

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.CR) {
                    find(txtFind.getText());
                }
            }
        });
        createSelectionListener();
    }

    /**
     * Find.
     */
    private void find(String find) {
        Table table = tableViewer.getTable();
        TableItem[] rows = table.getItems();
        int numberOfRow = rows.length;
        int nubmerOfColumn = table.getColumnCount();
        int index = -1;
        if (numberOfRow != 0) {
            for (int i = 0; i < numberOfRow; i++) {
                for (int j = 0; j < nubmerOfColumn; j++) {
                    if (rows[i].getText(j).toLowerCase()
                            .contains(find.trim().toLowerCase())) {
                        index = i;
                        table.select(index);
                        break;
                    }
                }
                
                if (index != -1) {
                    break;
                }
            }
            
            if (find.length() == 0 || find == null || index == -1) {
                table.deselectAll();
            }
        }
    }
    
    /**
     * The Class Filter.
     */
    class Filter extends ViewerFilter {

        private static final long serialVersionUID = 1L;

        private String filterString;

        public void setFilterString(String filterString) {
            this.filterString = filterString.toLowerCase();
        }

        @Override
        public boolean select(Viewer viewer, Object parentElement,
                Object element) {

            if (filterString == null || filterString.length() == 0) {
                return true;
            }

            Device device = (Device) element;
            String name = device.getName().toLowerCase();
            String appModule = device.getAppModule().toLowerCase();
            String deviceType = device.getDeviceType().toLowerCase();
            String physicalLocation = device.getPhysicalLocation()
                    .toLowerCase();
            String manufacture = device.getManufacturer().toLowerCase();

            if (name != null && name.contains(filterString)) {
                return true;
            }

            if (appModule != null && appModule.contains(filterString)) {
                return true;
            }

            if (deviceType != null && deviceType.contains(filterString)) {
                return true;
            }

            if (physicalLocation != null
                    && physicalLocation.contains(filterString)) {
                return true;
            }

            if (manufacture != null && manufacture.contains(filterString)) {
                return true;
            }
            return false;
        }
    }

    /**
     * Creates the selection listener.
     */
    private void createSelectionListener() {
        IWorkbenchWindow window = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow();
        ISelectionService selectionService = window.getSelectionService();
        selectionService.addSelectionListener(new ISelectionListener() {
            @Override
            public void selectionChanged(IWorkbenchPart part,
                    ISelection selection) {
                IStructuredSelection iSelection = (IStructuredSelection) selection;
                Object firstElement = iSelection.getFirstElement();
                if(firstElement != null && firstElement instanceof Version){ 
                    List<Device> devices = ((Version) firstElement).getDevices();
                    tableViewer.setInput(devices);
                    setData(firstElement);
                }
            }
        });
    }

    private void setData(Object firstElement) {
        if (firstElement != null && firstElement instanceof Version) {
            List<Device> devices = ((Version) firstElement).getDevices();
            tableViewer.setInput(devices);
        }
    }

    protected String[] initColumnProperties(Table table) {
        int numberOfColumn = columnProperties.length;
        for (int i = 0; i < numberOfColumn; i++) {
            
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setWidth(200);
            column.setText(columnProperties[i]);

            column.pack();
        }
        
        return columnProperties;
    }

    @Override
    public void doubleClick(DoubleClickEvent event) {
        IWorkbenchWindow window = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow();
        try {
            ISelection selection = tableViewer.getSelection();
            IStructuredSelection sselection = (IStructuredSelection) selection;
            Object firstObject = sselection.getFirstElement();
            if (firstObject!= null &&firstObject instanceof Device) {
                String secondaryId = String.valueOf(((Device) firstObject).getIdDevice());
                DetailDeviceView detailDeviceView = (DetailDeviceView) window.getActivePage().showView("RAP-FirstProject.detailDeviceView", secondaryId, IWorkbenchPage.VIEW_ACTIVATE);
                detailDeviceView.setData((Device) firstObject);
            }
            
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFocus() {
    }
}

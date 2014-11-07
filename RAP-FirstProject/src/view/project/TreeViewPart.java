package view.project;

import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;

import entity.Type;
import model.ChildTreeModel;

public class TreeViewPart extends ViewPart implements IDoubleClickListener{
    public TreeViewer treeViewer;
    private FilteredTree filterTree;
    
    @Override
    public void doubleClick(DoubleClickEvent event) {
    }

    @Override
    public void createPartControl(Composite parent) {
        //Create filter
        PatternFilter filter = new PatternFilter();
        filterTree = new FilteredTree(parent, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL, filter, true);
        treeViewer = filterTree.getViewer();
        treeViewer.setContentProvider(new TreeViewContentProvider());
        treeViewer.setLabelProvider(new TreeViewLabelProvider());
        ChildTreeModel childTreemodel = new ChildTreeModel();
        List<Type> type = childTreemodel.getAllType();
        treeViewer.setInput(type);
        
        //Create menu manager
        MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(treeViewer.getTree());
        
        //Set the menu manager
        treeViewer.getTree().setMenu(menu);
        getSite().registerContextMenu(menuManager, treeViewer);
        
        getSite().setSelectionProvider(treeViewer);
    }
    
    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
    
}

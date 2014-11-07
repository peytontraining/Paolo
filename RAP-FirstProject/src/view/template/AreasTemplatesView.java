package view.template;


import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

public class AreasTemplatesView extends ViewPart{
    static Text templateText;
    static Text nameText;
    static Text notesText;
    
//    String template;
//    String name;
//    String notes;
    
    @Override
    public void createPartControl(Composite parent) {
        Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new FillLayout() );
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        ScrolledForm form = toolkit.createScrolledForm( composite );
        form.setExpandHorizontal(true);
//        form.getBody().setLayout( new TableWrapLayout() );
        form.getBody().setLayout(new GridLayout(3,false));
        
        GridData textGridData = new GridData(SWT.FILL, SWT.NONE, true, false,
                2, 1);
        
        //Templates
        toolkit.createLabel(form.getBody(), "Templates:");
        templateText = toolkit.createText(form.getBody(), "");
        templateText.setLayoutData(textGridData);
        
        //Name
        toolkit.createLabel(form.getBody(), "Name:");
        nameText = toolkit.createText(form.getBody(), "");
        nameText.setLayoutData(textGridData);
        
        //Notes
        toolkit.createLabel(form.getBody(), "Notes:");
        notesText = toolkit.createText(form.getBody(), "",SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        GridData mtextGridData = new GridData(SWT.FILL, SWT.NONE, true, false,2, 1);
        mtextGridData.heightHint = notesText.getLineHeight() * 4;

        notesText.setLayoutData(mtextGridData);
    }
    @Override
    public void setFocus() {
    }
}

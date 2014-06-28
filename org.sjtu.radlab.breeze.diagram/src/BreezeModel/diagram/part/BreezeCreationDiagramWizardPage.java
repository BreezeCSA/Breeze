package BreezeModel.diagram.part;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class BreezeCreationDiagramWizardPage extends WizardPage {
	private String fileName = null;
	private Text txt = null;

	public BreezeCreationDiagramWizardPage(Image img) {
		super("New Breeze Project");
		this.setTitle("New Breeze Project");
		this.setDescription("Create New Breeze Model and Breeze Diagram");
		this.setImageDescriptor(ImageDescriptor.createFromImage(img));
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginLeft = 6;
		gl.marginTop = 5;
		container.setLayout(gl);

		this.setPageComplete(false);

		Label lb = new Label(container, SWT.LEFT);
		this.txt = new Text(container, SWT.BORDER);
		this.txt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		this.txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				String name = BreezeCreationDiagramWizardPage.this.txt.getText();
				if (!"".equals(name))
					BreezeCreationDiagramWizardPage.this.setPageComplete(true);
				else BreezeCreationDiagramWizardPage.this.setPageComplete(false);
			}

		});
		this.txt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				BreezeCreationDiagramWizardPage.this.fileName = BreezeCreationDiagramWizardPage.this.txt.getText();
			}

		});
		lb.setText("Name:");

		this.setControl(container);
	}

	public String getFileName() {
		return this.fileName;
	}

}

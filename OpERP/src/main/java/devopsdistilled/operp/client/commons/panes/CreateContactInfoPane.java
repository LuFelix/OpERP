package devopsdistilled.operp.client.commons.panes;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import devopsdistilled.operp.client.abstracts.SubTaskPane;
import devopsdistilled.operp.client.commons.panes.controllers.CreateContactInfoPaneController;
import devopsdistilled.operp.client.commons.panes.models.observers.CreateContactInfoPaneModelObserver;

public class CreateContactInfoPane extends SubTaskPane implements
		CreateContactInfoPaneModelObserver {

	private CreateContactInfoPaneController controller;

	private final JPanel pane;
	private final JTextField emailField;
	private final JTextField workNumField;
	private final JTextField mobileNumField;
	private final JTextField homeNumField;
	private final JPanel addressPanel;

	public CreateContactInfoPane() {
		pane = new JPanel();
		pane.setLayout(new MigLayout("", "[grow][grow]", "[][grow][][][][][]"));

		JLabel lblAddress = new JLabel("Address");
		pane.add(lblAddress, "flowx,cell 0 0");

		addressPanel = new JPanel();
		pane.add(addressPanel, "cell 0 1,grow");

		JLabel lblEmail = new JLabel("Email");
		pane.add(lblEmail, "cell 0 2,alignx trailing");

		emailField = new JTextField();
		pane.add(emailField, "cell 1 2,growx");
		emailField.setColumns(10);

		JLabel lblPhone = new JLabel("Phone");
		pane.add(lblPhone, "cell 0 3");

		JLabel lblWork = new JLabel("Work");
		pane.add(lblWork, "cell 0 4,alignx trailing");

		workNumField = new JTextField();
		pane.add(workNumField, "cell 1 4,growx");
		workNumField.setColumns(10);

		JLabel lblMobile = new JLabel("Mobile");
		pane.add(lblMobile, "cell 0 5,alignx trailing");

		mobileNumField = new JTextField();
		pane.add(mobileNumField, "cell 1 5,growx");
		mobileNumField.setColumns(10);

		JLabel lblHome = new JLabel("Home");
		pane.add(lblHome, "cell 0 6,alignx trailing");

		homeNumField = new JTextField();
		pane.add(homeNumField, "cell 1 6,growx");
		homeNumField.setColumns(10);
	}

	@Override
	public JComponent getPane() {
		return pane;
	}

	public void setController(CreateContactInfoPaneController controller) {
		this.controller = controller;
	}
}

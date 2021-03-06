package devopsdistilled.operp.client.account.panes.controllers.impl;

import javax.inject.Inject;

import devopsdistilled.operp.client.abstracts.EntityOperation;
import devopsdistilled.operp.client.account.models.ReceivedTransactionModel;
import devopsdistilled.operp.client.account.panes.ReceivedTransactionPane;
import devopsdistilled.operp.client.account.panes.controllers.ReceivedTransactionPaneController;
import devopsdistilled.operp.client.account.panes.models.ReceivedTransactionPaneModel;
import devopsdistilled.operp.client.exceptions.EntityValidationException;
import devopsdistilled.operp.client.exceptions.NullFieldException;
import devopsdistilled.operp.client.party.models.CustomerModel;
import devopsdistilled.operp.server.data.entity.account.ReceivedTransaction;

public class ReceivedTransactionPaneControllerImpl implements
		ReceivedTransactionPaneController {

	@Inject
	private ReceivedTransactionPane view;

	@Inject
	private ReceivedTransactionPaneModel model;

	@Inject
	private CustomerModel customerModel;

	@Inject
	private ReceivedTransactionModel receivedTransactionModel;

	@Override
	public void validate() throws EntityValidationException {
		if (model.getEntity().getAccount() == null)
			throw new NullFieldException("Customer must be selected");

		if (model.getEntity().getAmount().compareTo(0.0) < 1)
			throw new NullFieldException("Amount must be greater than 0");

		if (model.getEntity().getAccount().getBalance()
				.compareTo(model.getEntity().getAmount()) < 0)
			throw new EntityValidationException(
					"Received Amount must not be greater than balance");

	}

	@Override
	public ReceivedTransaction save() {
		return receivedTransactionModel.saveAndUpdateModel(model.getEntity());
	}

	@Override
	public ReceivedTransactionPaneModel getModel() {
		return model;
	}

	@Override
	public ReceivedTransactionPane getView() {
		return view;
	}

	@Override
	public void init(ReceivedTransaction receivedTransaction,
			EntityOperation entityOperation) {

		if (EntityOperation.Edit == entityOperation) {
			receivedTransactionModel.getService().delete(receivedTransaction);
		}

		view.setController(this);
		view.resetComponents();

		model.registerObserver(view);
		model.setEntityAndEntityOperation(receivedTransaction, entityOperation);
		customerModel.registerObserver(view);

		view.init();
	}

}

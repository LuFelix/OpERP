package devopsdistilled.operp.client.business.sales.panes.controllers.impl;

import javax.inject.Inject;
import javax.swing.JPanel;

import devopsdistilled.operp.client.abstracts.EntityOperation;
import devopsdistilled.operp.client.business.sales.panes.SaleDescPane;
import devopsdistilled.operp.client.business.sales.panes.controllers.SaleDescPaneController;
import devopsdistilled.operp.client.business.sales.panes.controllers.SaleDescRowPaneController;
import devopsdistilled.operp.client.business.sales.panes.models.SaleDescPaneModel;
import devopsdistilled.operp.client.exceptions.EntityValidationException;
import devopsdistilled.operp.client.exceptions.NullFieldException;
import devopsdistilled.operp.server.data.entity.business.SaleDesc;
import devopsdistilled.operp.server.data.entity.business.SaleDescRow;

public class SaleDescPaneControllerImpl implements SaleDescPaneController {

	@Inject
	private SaleDescPane view;

	@Inject
	private SaleDescPaneModel model;

	@Inject
	private SaleDescRowPaneController saleDescRowPaneController;

	@Override
	public void validate() throws EntityValidationException {
		if (model.getSaleDescRow().getItem() == null
				|| model.getSaleDescRow().getWarehouse() == null
				|| model.getSaleDescRow().getQuantity().equals(0L)
				|| model.getSaleDescRow().getRate().equals(0.0)) {

			throw new NullFieldException();
		}

		// XXX More validation checking
	}

	@Override
	public SaleDesc save() {
		return null;
	}

	@Override
	public SaleDescPaneModel getModel() {
		return model;
	}

	@Override
	public SaleDescPane getView() {
		return view;
	}

	@Override
	public void init(SaleDesc saleDesc, EntityOperation entityOperation) {
		if (EntityOperation.Create == entityOperation) {
			SaleDescRow saleDescRow = new SaleDescRow();
			model.setSaleDescRow(saleDescRow);
			saleDescRowPaneController.init(saleDescRow, entityOperation);
		}

		view.setSaleDescRowpanel((JPanel) saleDescRowPaneController.getView()
				.getPane());
		view.setController(this);
		view.resetComponents();

		model.registerObserver(view);
		model.setEntityAndEntityOperation(saleDesc, entityOperation);
	}

	@Override
	public void addNewSaleDescRow() {

		model.getSaleDescRow().setBusinessDesc(model.getEntity());

		if (EntityOperation.Edit != model.getEntityOperation()) {
			model.getEntity().getDescRows().add(model.getSaleDescRow());
		}

		model.getEntity().setTotalAmount(
				model.getEntity().getTotalAmount()
						+ model.getSaleDescRow().getAmount());

		model.setEntityAndEntityOperation(model.getEntity(),
				EntityOperation.Create);

		SaleDescRow saleDescRow = new SaleDescRow();
		model.setSaleDescRow(saleDescRow);
		saleDescRowPaneController.init(saleDescRow, EntityOperation.Create);
	}

	@Override
	public void initEditSaleDescRow(SaleDescRow saleDescRow) {
		model.setSaleDescRow(saleDescRow);
		model.getEntity().setTotalAmount(
				model.getEntity().getTotalAmount()
						- model.getSaleDescRow().getAmount());
		model.setEntityAndEntityOperation(model.getEntity(),
				EntityOperation.Edit);
		saleDescRowPaneController.init(saleDescRow, EntityOperation.Edit);
	}
}

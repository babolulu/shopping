package logic;

import java.sql.Timestamp;

public class SaleLine {

	private Sale sale;

	private Integer saleLineId;

	private Item item;

	private Timestamp updateTime;

	private Integer quantity;

	public SaleLine(Sale sale, Integer saleLineId, ItemSet itemSet, Timestamp currentTime) {
		this.sale = sale;
		this.saleLineId = saleLineId;
		this.item = itemSet.getItem();
		this.quantity = itemSet.getQuantity();
		this.updateTime = currentTime;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Sale getSale() {
		return this.sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Integer getSaleLineId() {
		return this.saleLineId;
	}

	public void setSaleLineId(Integer saleLineId) {
		this.saleLineId = saleLineId;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
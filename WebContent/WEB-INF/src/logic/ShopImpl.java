package logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopImpl implements Shop {

	@Autowired
	private ItemCatalog itemCatalog;

	@Autowired
	private UserCatalog userCatalog;

	@Autowired
	private SaleCatalog saleCatalog;

	public Cart getCart() {
		return new Cart();
	}

	public Integer calculateTotalAmount(List<ItemSet> itemList) {
		// 합계금액
		int totalAmount = 0;
		for (ItemSet itemSet : itemList) {
			int price = itemSet.getItem().getPrice().intValue();
			int quantity = itemSet.getQuantity().intValue();
			totalAmount = totalAmount + (price * quantity);
		}
		return new Integer(totalAmount);
	}

	public void checkout(User user, Cart cart) {
		// 매상 정보 작성
		Sale sale = createSale(user, cart);
		// 매상 정보 등록
		entrySale(sale);
	}

	public List<Item> getItemList() {
		return this.itemCatalog.getItemList();
	}

	public Item getItemByItemId(Integer itemId) {
		return this.itemCatalog.getItemByItemId(itemId);
	}

	public User getUserByUserIdAndPassword(String userId, String password) {
		return this.userCatalog.getUserByUserIdAndPassword(userId, password);
	}

	public void entryUser(User user) {
		this.userCatalog.entryUser(user);
	}

	private void entrySale(Sale sale) {
		this.saleCatalog.entrySale(sale);
	}

	private Sale createSale(User user, Cart cart) {
		// 매상 정보 작성
		Sale sale = new Sale();
		sale.setSaleId(getNewSaleId());
		sale.setUser(user);
		Timestamp currentTime = getCurrentTime();
		sale.setUpdateTime(currentTime);

		// 카트 상품 종류별 매상 명세정보를 작성
		List<ItemSet> itemList = cart.getItemList();
		for (int i = 0; i < itemList.size(); i++) {
			ItemSet itemSet = (ItemSet) itemList.get(i);
			// 매상 상세ID는 첫 번째부터 설정
			int saleLineId = i + 1;
			SaleLine saleLine = createSaleLine(sale, saleLineId, itemSet, currentTime);
			sale.addSaleLine(saleLine);
		}
		return sale;
	}

	private Integer getNewSaleId() {
		return this.saleCatalog.getNewSaleId();
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	private SaleLine createSaleLine(Sale sale, int saleLineId, ItemSet itemSet, Timestamp currentTime) {
		return new SaleLine(sale, new Integer(saleLineId), itemSet, currentTime);
	}
}
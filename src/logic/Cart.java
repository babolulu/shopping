package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ItemSet> itemList = new ArrayList<ItemSet>();

	public List<ItemSet> getItemList() {
		return this.itemList;
	}

	public boolean isEmpty() {
		if (this.itemList == null || this.itemList.isEmpty()) {
			return true;
		}
		return false;
	}

	public void push(ItemSet pushedItemSet) {
		// 추가 상품이 카트에 이미 존재하는지 확인하는 플래그
		boolean itemExistInCart = false;

		// 추가된 상품의 상품ID를 취득
		Item pushedItem = pushedItemSet.getItem();
		int pushedItemId = pushedItem.getItemId().intValue();

		// 카트의 상품 수만큼 반복 실행
		for (ItemSet cartItemSet : this.itemList) {

			// 카트에 있는 상품의 ID를 취득
			Item cartItem = cartItemSet.getItem();
			int cartItemId = cartItem.getItemId().intValue();

			if (cartItemId == pushedItemId) {
				// 동일ID의 상품이 카트에 존재하는 경우, 수량만 가산
				cartItemSet.addQuantity(pushedItemSet.getQuantity());
				// 추가 상품이 카트 안에 이미 존재함
				itemExistInCart = true;
				break;
			}
		}

		if (!itemExistInCart) {
			// 카트에 동일 상품이 없으므로 추가
			this.itemList.add(pushedItemSet);
		}
	}

	public void clearAll() {
		this.itemList = new ArrayList<ItemSet>();
	}
}
package logic;

import java.util.List;

public interface Shop {

	Cart getCart();

	Integer calculateTotalAmount(List<ItemSet> itemList);

	void checkout(User user, Cart cart);

	List<Item> getItemList();

	Item getItemByItemId(Integer itemId);

	User getUserByUserIdAndPassword(String userId, String password);

	void entryUser(User user);
}
package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import logic.Cart;
import logic.ItemSet;
import logic.Shop;
import logic.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;
import exception.CartEmptyException;
import exception.LoginRequiredException;

@Controller
public class CheckoutController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView checkOut(HttpSession session) {
		// 로그인을 확인
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser == null) {
			throw new LoginRequiredException("로그인되어 있지 않습니다.");
		}

		// 카트를 취득
		Cart cart = (Cart) session.getAttribute(WebConstants.CART_KEY);
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("카트가 비어있습니다.");
		}

		// 유저 정보와 카트 안의 상품 정보를 설정
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("loginUser", loginUser);
		List<ItemSet> itemList = cart.getItemList();
		modelAndView.addObject("itemList", itemList);

		// 합계 금액을 설정
		Integer totalAmount = this.shopService.calculateTotalAmount(itemList);
		modelAndView.addObject("totalAmount", totalAmount);

		return modelAndView;
	}
}
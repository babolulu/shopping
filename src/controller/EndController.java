package controller;

import javax.servlet.http.HttpSession;

import logic.Cart;
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
public class EndController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView end(HttpSession session) {

		// 로그인을 확인
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser == null) {
			throw new LoginRequiredException("로그인되어 있지 않습니다.");
		}

		// 카트 상태를 확인
		Cart cart = (Cart) session.getAttribute(WebConstants.CART_KEY);
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("카트가 비어있습니다.");
		}

		// 매상 데이터 기록
		this.shopService.checkout(loginUser, cart);

		// 카트 정보 제거
		cart.clearAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("loginUser", loginUser);
		return modelAndView;
	}
}
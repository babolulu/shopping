package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.Item;
import logic.Shop;
import logic.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class DetailController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView handleRequestInternal(Integer itemId, HttpSession session) {

		// 선택된 상품ID로부터 상품 정보를 취득
		Item item = this.shopService.getItemByItemId(itemId);

		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);

		// 반환값이 되는 ModelAndView 인스턴스를 생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(model);

		// 로그인 유저 취득
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser != null) {
			modelAndView.addObject("loginUser", loginUser);
		}
		return modelAndView;
	}
}
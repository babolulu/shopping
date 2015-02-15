package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import logic.Shop;
import logic.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import utils.UserEntryValidator;
import utils.WebConstants;

@Controller
public class UserEntryFormController {

	@Autowired
	private Shop shopService;

	@Autowired
	private UserEntryValidator userEntryValidator;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String toUserEntryView() {
		return "userentryform/userEntry";
	}

	@ModelAttribute
	public User setUpForm() {
		User user = new User();
		MessageSourceAccessor accessor = new MessageSourceAccessor(this.messageSource);
		user.setUserId(accessor.getMessage("user.userId.default"));
		user.setUserName(accessor.getMessage("user.userName.default"));
		return user;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// Date 타입인 birthDay 프로퍼티를 커스터마이즈
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDay", new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, BindingResult bindingResult, HttpSession session) throws Exception {

		this.userEntryValidator.validate(user, bindingResult);

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}

		try {
			this.shopService.entryUser(user);
			session.setAttribute(WebConstants.USER_KEY, user);
			if (this.shopService.getCart() == null) {
				session.setAttribute(WebConstants.CART_KEY, this.shopService.getCart());
			}
			modelAndView.setViewName("userentryform/userEntrySuccess");
			modelAndView.addObject("user", user);
			return modelAndView;

		} catch (DataIntegrityViolationException e) {
			// 유저ID 중복 시, 폼을 송신했던 곳으로 이동
			bindingResult.reject("error.duplicate.user");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
}
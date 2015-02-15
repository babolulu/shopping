package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;

@Service
public class UserCatalogImpl implements UserCatalog {
	
	@Autowired
	private UserDao userDao;

	public void entryUser(User user) {
		this.userDao.create(user);
	}

	public User getUserByUserIdAndPassword(String userId, String password) {
		return this.userDao.findByUserIdAndPassword(userId, password);
	}
}
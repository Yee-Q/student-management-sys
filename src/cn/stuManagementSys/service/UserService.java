package cn.stuManagementSys.service;

import cn.stuManagementSys.dao.UserDao;
import cn.stuManagementSys.entity.User;
import cn.stuManagementSys.exception.UserException;

public class UserService {

	private UserDao userDao = new UserDao();

	/**
	 * 登录
	 * @throws UserException 
	 */
	public void Login(String username, String password) throws UserException {

		User user = userDao.searchUserByUsername(username);
		
		if(user == null) {
			throw new UserException("用户不存在");
		} else if(!(user.getPassword().equals(password))) {
			throw new UserException("密码错误");
		}
	}
	
	/**
	 * 修改密码
	 * @param username
	 * @param oldPw
	 * @param newPw
	 * @throws UserException 
	 */
	public void UpdatePw(String username, String oldPw, String newPw) throws UserException {
		
		User user = userDao.searchUserByUsername(username);
		
		if(user == null) {
			throw new UserException("用户不存在");
		} else if(!(user.getPassword().equals(oldPw))) {
			throw new UserException("密码错误");
		} else {
			userDao.updatePwByUsername(newPw, username);
		}
	}
	
	/**
	 * 按用户名查询用户
	 * @param username
	 * @return
	 */
	public User searchUserByUsername(String username) {
		
		return userDao.searchUserByUsername(username);
	}
	
	/**
	 * 新增用户
	 * @param user
	 */
	public void createUser(User user) {
		
		userDao.createUser(user);
	}
}

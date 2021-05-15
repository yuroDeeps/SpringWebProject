package pl.yuro.crudandloginexercisepage.dao;

import javax.transaction.Transactional;

import pl.yuro.crudandloginexercisepage.entity.User;


public class UserDaoImpl implements UserDao {

	@Override
	@Transactional
	public User findUserByUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}

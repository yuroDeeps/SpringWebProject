package pl.yuro.crudandloginexercisepage.dao;

import pl.yuro.crudandloginexercisepage.entity.User;

public interface UserDao {

	User findUserByUsername();
}

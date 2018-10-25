package com.zinios.dealab.dao;


import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;

import java.util.List;

/**
 * Created by vihanga on 08/29/18.
 */
public interface UserDao extends BaseDao<User> {

	User login(String userName, String password, Company company);

	User findByUserName(String userName, Company company);

	User findByEmail(String email);

	List<User> getSortedList(int offset, int limit);
}

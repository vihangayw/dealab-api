package com.zinios.dealab.dao;


import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.User;

import java.util.List;

/**
 * Created by vihanga on 08/29/18.
 */
public interface UserDao extends BaseDao<User> {

	User login(String email, String password, Company company);

	User login(String email, String password);

	User findByUserName(String email, Company company);

	User findByCompany(Company company);

	User findByEmail(String email);

	List<User> getSortedList(int offset, int limit);
}

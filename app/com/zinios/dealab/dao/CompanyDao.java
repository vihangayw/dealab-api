package com.zinios.dealab.dao;


import com.zinios.dealab.models.Company;

import java.util.List;

/**
 * Created by vihanga on 10/29/17.
 */
public interface CompanyDao extends BaseDao<Company> {

	Company findByEmail(String email);

	Company findById(Long id);

	List<Company> getSortedInactiveCompanyList(int offset, int limit);

	List<Company> getSortedList(int offset, int limit);

	Company findByEmailId(String email, String id);
}

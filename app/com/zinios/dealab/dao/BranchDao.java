package com.zinios.dealab.dao;


import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.entity.Images;

import java.util.List;

/**
 * Created by vihanga on 10/29/18.
 */
public interface BranchDao extends BaseDao<Branch> {

	Branch findById(Long id);

	List<Branch> getSortedInactiveBranchList(Company company, int offset, int limit);

	List<Branch> getSortedList(Company company, int offset, int limit);

	Branch isExists(Company company, String name, String placeId);

	List<Images> branchImages(Long idToFind);

}

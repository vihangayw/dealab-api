package com.zinios.dealab.dao;


import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Review;
import com.zinios.dealab.models.entity.Reviews;

import java.util.List;

/**
 * Created by vihanga on 10/29/18.
 */
public interface ReviewDao extends BaseDao<Review> {

	List<Reviews> getSortedList(Branch branch);

}

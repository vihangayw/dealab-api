package com.zinios.dealab.parsers;

import com.zinios.dealab.models.Branch;
import com.zinios.dealab.parsers.util.CustomBodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class BranchBodyParser extends CustomBodyParser<Branch> {

	@Inject
	public BranchBodyParser(Json jsonParser, Executor executor) {
		super(jsonParser, executor, Branch.class);
	}
}


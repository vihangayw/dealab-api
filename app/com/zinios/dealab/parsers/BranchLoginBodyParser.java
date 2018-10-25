package com.zinios.dealab.parsers;

import com.zinios.dealab.models.parse.LoginBranch;
import com.zinios.dealab.parsers.util.CustomBodyParser;
import play.mvc.BodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class BranchLoginBodyParser extends CustomBodyParser<LoginBranch> {

	@Inject
	public BranchLoginBodyParser(BodyParser.Json jsonParser, Executor executor) {
		super(jsonParser, executor, LoginBranch.class);
	}
}


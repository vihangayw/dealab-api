package com.zinios.dealab.parsers;

import com.zinios.dealab.models.parse.Login;
import com.zinios.dealab.parsers.util.CustomBodyParser;
import play.mvc.BodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class LoginBodyParser extends CustomBodyParser<Login> {

	@Inject
	public LoginBodyParser(BodyParser.Json jsonParser, Executor executor) {
		super(jsonParser, executor, Login.class);
	}
}


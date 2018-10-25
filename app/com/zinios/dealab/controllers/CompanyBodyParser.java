package com.zinios.dealab.controllers;

import com.zinios.dealab.models.Company;
import com.zinios.dealab.parsers.util.CustomBodyParser;
import play.mvc.BodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class CompanyBodyParser extends CustomBodyParser<Company> {

	@Inject
	public CompanyBodyParser(BodyParser.Json jsonParser, Executor executor) {
		super(jsonParser, executor, Company.class);
	}
}


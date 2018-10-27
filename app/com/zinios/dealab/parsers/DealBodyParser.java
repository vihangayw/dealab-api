package com.zinios.dealab.parsers;

import com.zinios.dealab.models.Deal;
import com.zinios.dealab.parsers.util.CustomBodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class DealBodyParser extends CustomBodyParser<Deal> {

	@Inject
	public DealBodyParser(Json jsonParser, Executor executor) {
		super(jsonParser, executor, Deal.class);
	}
}


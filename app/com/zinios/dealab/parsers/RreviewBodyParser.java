package com.zinios.dealab.parsers;

import com.zinios.dealab.models.Review;
import com.zinios.dealab.parsers.util.CustomBodyParser;

import javax.inject.Inject;
import java.util.concurrent.Executor;

/**
 * Created by vihanga on 08/10/2018.
 */
public class RreviewBodyParser extends CustomBodyParser<Review> {

	@Inject
	public RreviewBodyParser(Json jsonParser, Executor executor) {
		super(jsonParser, executor, Review.class);
	}
}


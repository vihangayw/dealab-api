package com.zinios.dealab.parsers.util;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.Executor;

public class CustomBodyParser<T> implements BodyParser<T> {

	private final Class<T> genericType;
	private Json jsonParser;
	private Executor executor;

	@Inject
	public CustomBodyParser(Json jsonParser, Executor executor, Class<T> genericType) {
		this.jsonParser = jsonParser;
		this.executor = executor;
		this.genericType = genericType;
	}

	@Override
	public Accumulator<ByteString, F.Either<Result, T>> apply(Http.RequestHeader request) {
		Accumulator<ByteString, F.Either<Result, JsonNode>> jsonAccumulator = jsonParser.apply(request);
		return jsonAccumulator.map(resultOrJson -> {
			if (resultOrJson.left.isPresent()) {
				return F.Either.Left(resultOrJson.left.get());
			} else {
				JsonNode json = resultOrJson.right.get();
				try {
					T t = play.libs.Json.fromJson(json, genericType);
					return F.Either.Right(t);
				} catch (Exception e) {
					Logger.debug(e.getMessage(), e);
					return F.Either.Left(Results.badRequest(
							"Unable to read from json: " + e.getMessage()));
				}
			}
		}, executor);
	}
}

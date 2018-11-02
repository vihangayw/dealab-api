package com.zinios.dealab.utils;

import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;

import javax.inject.Inject;

public class MyCorsFilter extends DefaultHttpFilters {
	@Inject
	public MyCorsFilter(CORSFilter corsFilter) {
		super(corsFilter);
	}
}
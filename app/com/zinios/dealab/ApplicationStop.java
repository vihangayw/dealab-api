package com.zinios.dealab;

import play.Logger;
import play.inject.ApplicationLifecycle;
import play.libs.F;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationStop {

	@Inject
	public ApplicationStop(ApplicationLifecycle lifecycle) {

		lifecycle.addStopHook(() -> {
			Logger.info("Application shutdown...");
			return F.Promise.pure(null);
		});

	}
}

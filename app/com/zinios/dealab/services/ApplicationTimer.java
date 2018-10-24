package com.zinios.dealab.services;

import com.zinios.dealab.Module;
import play.Logger;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

/**
 * This class demonstrates how to run code when the
 * application starts and stops. It starts a timer when the
 * application starts. When the application stops it prints out how
 * long the application was running for.
 * <p>
 * This class is registered for Guice dependency injection in the
 * {@link Module} class. We want the class to start when the application
 * starts, so it is registered as an "eager singleton". See the code
 * in the {@link Module} class to see how this happens.
 * <p>
 * This class needs to run code when the server stops. It uses the
 * application's {@link ApplicationLifecycle} to register a stop hook.
 */
@Singleton
public class ApplicationTimer {

	private final Instant start;

	@Inject
	public ApplicationTimer(Clock clock, ApplicationLifecycle appLifecycle) {
		// This code is called when the application starts.
		start = clock.instant();
		Logger.info("ApplicationTimer demo: Starting application at " + start);

		// When the application starts, register a stop hook with the
		// ApplicationLifecycle object. The code inside the stop hook will
		// be run when the application stops.
		appLifecycle.addStopHook(() -> {
			Instant stop = clock.instant();
			long runningTime = stop.getEpochSecond() - start.getEpochSecond();
			Logger.info("ApplicationTimer demo: Stopping application at " + clock.instant() + " after " + runningTime + "s.");
			return CompletableFuture.completedFuture(null);
		});
	}

}

package com.zinios.dealab;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.zinios.dealab.dao.*;
import com.zinios.dealab.dao.impl.*;
import com.zinios.dealab.services.ApplicationTimer;
import com.zinios.dealab.services.AtomicCounter;
import com.zinios.dealab.services.Counter;

import java.time.Clock;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 * <p>
 * Play will automatically use any class called `com.zinios.dealab.Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

	@Override
	public void configure() {

		// Use the system clock as the default implementation of Clock
		bind(Clock.class).toInstance(Clock.systemDefaultZone());
		// Ask Guice to create an instance of ApplicationTimer when the
		// application starts.
		bind(ApplicationTimer.class).asEagerSingleton();
		//firebase start here
//		bind(FirebaseStart.class).asEagerSingleton();
		//application stops
		bind(ApplicationStop.class).asEagerSingleton();
		// Set AtomicCounter as the implementation for CounterDisplay.
		bind(Counter.class).to(AtomicCounter.class);

		bind(CompanyDao.class).annotatedWith(Names.named("company")).to(CompanyDaoImpl.class);
		bind(BranchDao.class).annotatedWith(Names.named("branch")).to(BranchDaoImpl.class);
		bind(UserDao.class).annotatedWith(Names.named("user")).to(UserDaoImpl.class);
		bind(DealDao.class).annotatedWith(Names.named("deal")).to(DealDaoImpl.class);
		bind(DealBranchDao.class).annotatedWith(Names.named("deal_branch")).to(DealBranchDaoImpl.class);
//		bind(ProductMediaDao.class).annotatedWith(Names.named("product_media")).to(ProductMediaDaoImpl.class);
//		bind(DisplayDao.class).annotatedWith(Names.named("display")).to(DisplayDaoImpl.class);
//		bind(DisplayMediaDao.class).annotatedWith(Names.named("display_media")).to(DisplayMediaDaoImpl.class);
//		bind(MDMDao.class).annotatedWith(Names.named("mdm")).to(MDMDaoImpl.class);
//		bind(ProductLocationDao.class).annotatedWith(Names.named("product_location")).to(ProductLocationDaoImpl.class);

	}

}

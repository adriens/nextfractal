/*
 * NextFractal 7.0 
 * http://www.nextbreakpoint.com
 *
 * Copyright 2001, 2015 Andrea Medeghini
 * andrea@nextbreakpoint.com
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.queue.jxta;

import com.nextbreakpoint.nextfractal.core.Application;
import com.nextbreakpoint.nextfractal.core.ApplicationContext;
import com.nextbreakpoint.nextfractal.core.launcher.Launcher;
import com.nextbreakpoint.nextfractal.core.launcher.LauncherContext;
import com.nextbreakpoint.nextfractal.core.launcher.LauncherContextListener;
import com.nextbreakpoint.nextfractal.core.launcher.LauncherThreadFactory;

/**
 * Application implementation.
 * 
 * @author Andrea Medeghini
 */
public class ProxyApplication implements Application {
	private final Launcher<DefaultLauncherContext> launcher = new Launcher<DefaultLauncherContext>(new DefaultLauncherContext(), new DefaultLauncherThreadFactory());
	private LauncherContextListener listener;

	@Override
	public Object start(final ApplicationContext context) throws Exception {
//		Properties log4jProperties = new Properties();
//		log4jProperties.put("log4j.rootLogger", "INFO, console");
//		log4jProperties.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
//		log4jProperties.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
//		log4jProperties.put("log4j.appender.console.layout.ConversionPattern", "%d{HH:mm:ss,SSS} %-5p %c - %m%n");
//		log4jProperties.put("log4j.logger.com.nextbreakpoint.nextfractal", "INFO");
//		log4jProperties.put("log4j.logger.org.apache.derby", "INFO");
//		PropertyConfigurator.configure(log4jProperties);
		boolean restart = false;
		try {
			launcher.init();
			launcher.start();
			if (listener != null) {
				listener.started();
			}
			context.applicationRunning();
			restart = launcher.dispatch();
			if (listener != null) {
				listener.stopped();
			}
			launcher.dispose();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		if (restart) {
			return Application.EXIT_RESTART;
		}
		return Application.EXIT_OK;
	}

	@Override
	public void stop() {
		launcher.stop();
	}

	private class DefaultLauncherContext implements LauncherContext {
		/**
		 * @see com.nextbreakpoint.nextfractal.launcher.LauncherContext#exit()
		 */
		@Override
		public void exit() {
			launcher.stop();
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.launcher.LauncherContext#restart()
		 */
		@Override
		public void restart() {
			launcher.restart();
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.launcher.LauncherContext#setContextListener(com.nextbreakpoint.nextfractal.launcher.LauncherContextListener)
		 */
		@Override
		public void setContextListener(final LauncherContextListener listener) {
			ProxyApplication.this.listener = listener;
		}
	}

	private class DefaultLauncherThreadFactory implements LauncherThreadFactory<DefaultLauncherContext> {
		/**
		 * @see com.nextbreakpoint.nextfractal.networking.jxta.spool.LauncherThreadFactory#createThread(com.nextbreakpoint.nextfractal.launcher.LauncherContext)
		 */
		@Override
		public Thread createThread(final DefaultLauncherContext context) {
			return new ProxyLauncherThread(context);
		}
	}
}

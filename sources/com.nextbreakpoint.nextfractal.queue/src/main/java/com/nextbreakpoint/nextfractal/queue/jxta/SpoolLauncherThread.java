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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.nextbreakpoint.nextfractal.core.launcher.LauncherContext;
import com.nextbreakpoint.nextfractal.core.launcher.LauncherContextListener;
import com.nextbreakpoint.nextfractal.core.util.ConnectionFactory;
import com.nextbreakpoint.nextfractal.core.util.DefaultThreadFactory;
import com.nextbreakpoint.nextfractal.core.util.Worker;
import com.nextbreakpoint.nextfractal.queue.DefaultConnectionFactory;
import com.nextbreakpoint.nextfractal.queue.network.spool.DistributedServiceProcessor;
import com.nextbreakpoint.nextfractal.queue.spool.DefaultDistributedJobService;
import com.nextbreakpoint.nextfractal.queue.spool.job.DistributedJob;
import com.nextbreakpoint.nextfractal.queue.spool.job.DistributedJobFactory;

/**
 * @author Andrea Medeghini
 */
public class SpoolLauncherThread extends Thread implements LauncherContextListener {
	private DistributedServiceProcessor processor;
	private JXTANetworkService service;
	private final LauncherContext context;
	private Worker worker;

	/**
	 * @param context
	 */
	public SpoolLauncherThread(final LauncherContext context) {
		super("Launcher");
		setDaemon(true);
		this.context = context;
		context.setContextListener(this);
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			final Properties properties = new Properties();
			File workspace = null;
			try {
				properties.load(new FileInputStream(System.getProperty("user.home") + "/JXTASpool.properties"));
				if ((properties.get("workspace") == null) || (System.getProperty("nextfractal.workspace") != null)) {
					workspace = new File(System.getProperty("nextfractal.workspace", System.getProperty("user.home") + "/JXTASpool-workspace").replace("${user.home}", System.getProperty("user.home")));
					properties.put("workspace", workspace.getAbsolutePath());
					try {
						properties.store(new FileOutputStream(System.getProperty("user.home") + "/JXTASpool.properties"), null);
					}
					catch (final Exception e) {
						e.printStackTrace();
					}
				}
				else {
					workspace = new File((String) properties.get("workspace"));
				}
			}
			catch (final Exception x) {
				x.printStackTrace();
				workspace = new File(System.getProperty("nextfractal.workspace", System.getProperty("user.home") + "/JXTASpool-workspace").replace("${user.home}", System.getProperty("user.home")));
				properties.put("workspace", workspace.getAbsolutePath());
				try {
					properties.store(new FileOutputStream(System.getProperty("user.home") + "/JXTASpool.properties"), null);
				}
				catch (final Exception e) {
					e.printStackTrace();
				}
			}
			ConnectionFactory connectionFactory = null;
			while (workspace != null) {
				connectionFactory = new DefaultConnectionFactory(workspace);
				final Connection connection = null;
				try {
					connectionFactory.createConnection();
					break;
				}
				catch (final Exception e) {
					e.printStackTrace();
					workspace = null;
				}
				finally {
					if (connection != null) {
						try {
							connection.close();
						}
						catch (final SQLException e) {
						}
					}
				}
			}
			if (workspace != null) {
				final File tmpDir = new File(workspace, "/JXTASpool");
				worker = new Worker(new DefaultThreadFactory("JSpaceSpool Worker", true, Thread.NORM_PRIORITY));
				processor = new DistributedServiceProcessor(new DefaultDistributedJobService<DistributedJob>(0, "DistributedProcessor", new DistributedJobFactory(new File(tmpDir, "spool"), worker), worker), 10);
				service = new JXTANetworkService(tmpDir, "http://nextfractal.sf.net", "JXTASpool", "Andrea Medeghini", "2.0.0", processor);
				processor.start();
				service.start();
				worker.start();
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
			context.exit();
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.launcher.LauncherContextListener#started()
	 */
	@Override
	public void started() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.launcher.LauncherContextListener#stopped()
	 */
	@Override
	public void stopped() {
		worker.stop();
		service.stop();
		processor.stop();
	}
}

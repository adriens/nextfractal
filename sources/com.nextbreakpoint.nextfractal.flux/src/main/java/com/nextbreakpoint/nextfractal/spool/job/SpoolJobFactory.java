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
package com.nextbreakpoint.nextfractal.spool.job;

import com.nextbreakpoint.nextfractal.core.Worker;
import com.nextbreakpoint.nextfractal.spool.JobFactory;
import com.nextbreakpoint.nextfractal.spool.JobListener;

/**
 * @author Andrea Medeghini
 */
public class SpoolJobFactory extends JobFactory<SpoolJob> {
	private final Worker worker;

	/**
	 * @param service
	 * @param worker
	 */
	public SpoolJobFactory(final Worker worker) {
		this.worker = worker;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.spool.JobFactory#createJob(java.lang.String, com.nextbreakpoint.nextfractal.queue.spool.JobListener)
	 */
	@Override
	public SpoolJob createJob(final String jobId, final JobListener listener) {
		return new SpoolJob(worker, jobId, listener);
	}
}
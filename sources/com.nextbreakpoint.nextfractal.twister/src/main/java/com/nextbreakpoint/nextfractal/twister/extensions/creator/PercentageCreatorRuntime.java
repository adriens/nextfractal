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
package com.nextbreakpoint.nextfractal.twister.extensions.creator;

import com.nextbreakpoint.nextfractal.core.extensionPoints.creator.CreatorExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.scripting.JSException;

/**
 * @author Andrea Medeghini
 */
public class PercentageCreatorRuntime extends CreatorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.creator.CreatorExtensionRuntime#create(java.lang.Object[])
	 */
	@Override
	public Object create(final Object... args) throws JSException {
		try {
			final int value = ((Double) args[0]).intValue();
			if ((value >= 0) && (value <= 100)) {
				return new Integer(value);
			}
			throw new Exception("Value out of range");
		}
		catch (Exception e) {
			throw new JSException("Percentage creator requires one arguments: #1 of type Number (from 0 to 100)", e);
		}
	}
}

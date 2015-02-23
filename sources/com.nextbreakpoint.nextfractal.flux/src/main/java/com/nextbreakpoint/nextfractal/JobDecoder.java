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
package com.nextbreakpoint.nextfractal;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @author Andrea Medeghini
 */
public class JobDecoder {
	private ExportProfile profile;
	private byte[] frameData;

	/**
	 * @param encodedData
	 * @throws Exception
	 */
	public JobDecoder(final byte[] encodedData) throws Exception {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(encodedData);
			ois = new ObjectInputStream(bais);
			profile = (ExportProfile) ois.readObject();
			frameData = (byte[]) ois.readObject();
		} catch (final Exception e) {
			throw new Exception("An error has happened unmarshalling the data: " + e.getMessage(), e);
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (bais != null) {
				bais.close();
			}
		}
		if (profile == null) {
			throw new Exception("profile is null");
		}
		if (frameData == null) {
			throw new Exception("frameData is null");
		}
	}

	/**
	 * @return
	 */
	public ExportProfile getProfile() {
		return profile;
	}

	/**
	 * @return the frameData
	 */
	public byte[] getFrameData() {
		return frameData;
	}
}
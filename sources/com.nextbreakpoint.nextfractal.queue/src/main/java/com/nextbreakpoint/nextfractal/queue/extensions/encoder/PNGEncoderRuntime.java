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
package com.nextbreakpoint.nextfractal.queue.extensions.encoder;

import net.sf.freeimage4java.FREE_IMAGE_FORMAT;

/**
 * @author Andrea Medeghini
 */
public class PNGEncoderRuntime extends FreeImageEncoderRuntime<PNGEncoderConfig> {
	/**
	 * @see com.nextbreakpoint.nextfractal.queue.extensions.encoder.FreeImageEncoderRuntime#getFormat()
	 */
	@Override
	protected FREE_IMAGE_FORMAT getFormat() {
		return FREE_IMAGE_FORMAT.FIF_PNG;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.extension.EncoderExtensionRuntime#getImageSuffix()
	 */
	@Override
	public String getImageSuffix() {
		return ".png";
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.extension.EncoderExtensionRuntime#getMovieSuffix()
	 */
	@Override
	public String getMovieSuffix() {
		return null;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.extension.EncoderExtensionRuntime#isAlphaSupported()
	 */
	@Override
	public boolean isAlphaSupported() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.extension.EncoderExtensionRuntime#isImageSupported()
	 */
	@Override
	public boolean isImageSupported() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.extension.EncoderExtensionRuntime#isMovieSupported()
	 */
	@Override
	public boolean isMovieSupported() {
		return false;
	}
}

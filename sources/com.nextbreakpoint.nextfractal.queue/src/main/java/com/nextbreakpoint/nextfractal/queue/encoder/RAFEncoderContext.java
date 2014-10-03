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
package com.nextbreakpoint.nextfractal.queue.encoder;

import java.io.IOException;

import com.nextbreakpoint.nextfractal.queue.io.ChunkedRandomAccessFile;

/**
 * @author Andrea Medeghini
 */
public class RAFEncoderContext implements EncoderContext {
	private final ChunkedRandomAccessFile raf;
	private final int imageWidth;
	private final int imageHeight;
	private final int frameRate;
	private final int frameCount;

	public RAFEncoderContext(final ChunkedRandomAccessFile raf, final int imageWidth, final int imageHeight, final int frameRate, final int frameCount) throws IOException {
		this.raf = raf;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.frameRate = frameRate;
		this.frameCount = frameCount;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.EncoderContext#getTileAsByteArray(int, int, int, int, int, int)
	 */
	@Override
	public byte[] getTileAsByteArray(final int n, final int x, final int y, final int w, final int h, final int s) throws IOException {
		final byte[] data = new byte[w * h * s];
		final byte[] row = new byte[w * 4];
		long pos = (n * getImageWidth() * getImageHeight() + y * getImageWidth() + x) * 4;
		int t = 0;
		for (int k = 0; k < h; k++) {
			raf.seek(pos);
			raf.readFully(row);
			for (int j = 0, i = 0; i < row.length; j += s, i += 4) {
				if (s == 3) {
					data[t + j + 0] = row[i + 0];
					data[t + j + 1] = row[i + 1];
					data[t + j + 2] = row[i + 2];
				}
				else if (s == 4) {
					data[t + j + 0] = row[i + 0];
					data[t + j + 1] = row[i + 1];
					data[t + j + 2] = row[i + 2];
					data[t + j + 3] = row[i + 3];
				}
			}
			t += w * s;
			pos += getImageWidth() * 4;
		}
		return data;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.queue.encoder.EncoderContext#getTileAsByteArray(int, int, int, int, int, int)
	 */
	@Override
	public int[] getTileAsIntArray(final int n, final int x, final int y, final int w, final int h, final int s) throws IOException {
		final int[] data = new int[w * h * s];
		final byte[] row = new byte[w * 4];
		long pos = (n * getImageWidth() * getImageHeight() + y * getImageWidth() + x) * 4;
		int t = 0;
		for (int k = 0; k < h; k++) {
			raf.seek(pos);
			raf.readFully(row);
			for (int j = 0, i = 0; i < row.length; j += s, i += 4) {
				if (s == 3) {
					data[t + j + 0] = row[i + 0];
					data[t + j + 1] = row[i + 1];
					data[t + j + 2] = row[i + 2];
				}
				else if (s == 4) {
					data[t + j + 0] = row[i + 0];
					data[t + j + 1] = row[i + 1];
					data[t + j + 2] = row[i + 2];
					data[t + j + 3] = row[i + 3];
				}
			}
			t += w * s;
			pos += getImageWidth() * 4;
		}
		return data;
	}

	@Override
	public int getImageWidth() {
		return imageWidth;
	}

	@Override
	public int getImageHeight() {
		return imageHeight;
	}

	@Override
	public int getFrameRate() {
		return frameRate;
	}

	@Override
	public int getFrameCount() {
		return frameCount;
	}
}

/*
 * NextFractal 1.2.1
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015-2016 Andrea Medeghini
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
package com.nextbreakpoint.nextfractal.contextfree.test;

import com.nextbreakpoint.nextfractal.contextfree.core.ExtendedGeneralPath;
import com.nextbreakpoint.nextfractal.contextfree.grammar.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import com.nextbreakpoint.nextfractal.contextfree.grammar.enums.FlagType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class V3RenderTest extends AbstractBaseTest {
	@Parameterized.Parameters
	public static Iterable<Object[]> parameters() {
		List<Object[]> params = new ArrayList<>();
//		params.add(new Object[] { "/v3-shape-square.cfdg", "/v3-shape-square.png" });
//		params.add(new Object[] { "/v3-shape-triangle.cfdg", "/v3-shape-triangle.png" });
//		params.add(new Object[] { "/v3-shape-circle.cfdg", "/v3-shape-circle.png" });
//		params.add(new Object[] { "/v3-shape-transform.cfdg", "/v3-shape-transform.png" });
//		params.add(new Object[] { "/v3-shape-multiple-primitives.cfdg", "/v3-shape-multiple-primitives.png" });
//		params.add(new Object[] { "/v3-shape-initial-adjustment.cfdg", "/v3-shape-initial-adjustment.png" });
//		params.add(new Object[] { "/v3-shape-options.cfdg", "/v3-shape-options.png" });
//		params.add(new Object[] { "/v3-shapes-blah.cfdg", "/v3-shapes-blah.png" });
//		params.add(new Object[] { "/v3-shapes-blah-random.cfdg", "/v3-shapes-blah-random.png" });
//		params.add(new Object[] { "/v3-shape-variable.cfdg", "/v3-shape-variable.png" });
//		params.add(new Object[] { "/v3-shape-function.cfdg", "/v3-shape-function.png" });
		params.add(new Object[] { "/v3-shape-path.cfdg", "/v3-shape-path.png" });
		return params;
	}

	@Parameterized.Parameter(0)
	public String sourceName;

	@Parameterized.Parameter(1)
	public String imageName;

	@Test
	public void shouldRenderImage() throws IOException {
		System.out.println(sourceName);
		TestCanvas canvas = new TestCanvas(200, 200);
		CFDG cfdg = parseSource(sourceName);
		cfdg.rulesLoaded();
		CFDGRenderer renderer = cfdg.renderer(200, 200, 1, 0, 0.1);
		renderer.run(canvas, false);
		BufferedImage actualImage = canvas.getImage();
		saveImage("tmp" + imageName, actualImage);
		BufferedImage expectedImage = loadImage(imageName);
		assertThat(compareImages(expectedImage, actualImage), is(equalTo(0.0)));
	}

	private double compareImages(BufferedImage expectedImage, BufferedImage actualImage) {
		int[] expexctedPixels = new int[expectedImage.getWidth() * expectedImage.getHeight()];
		int[] actualPixels = new int[actualImage.getWidth() * actualImage.getHeight()];
		expectedImage.getRGB(0, 0, expectedImage.getWidth(), expectedImage.getHeight(), expexctedPixels, 0, expectedImage.getWidth());
		actualImage.getRGB(0, 0, actualImage.getWidth(), actualImage.getHeight(), actualPixels, 0, actualImage.getWidth());
		return error(convertFormat(expexctedPixels), convertFormat(actualPixels));
	}

	private byte[] convertFormat(int[] data) {
		byte[] buffer = new byte[data.length * 4];
		for (int j = 0; j < data.length; j += 1) {
			buffer[j * 4 + 0] = (byte)(data[j] >> 24);
			buffer[j * 4 + 1] = (byte)(data[j] >> 16);
			buffer[j * 4 + 2] = (byte)(data[j] >> 8);
			buffer[j * 4 + 3] = (byte)(data[j] >> 0);
		}
		return buffer;
	}

	private double error(byte[] data1, byte[] data2) {
		double error = 0;
		for (int j = 0; j < data1.length; j += 4) {
			error += distance(data1, data2, j);
		}
		return error / (data1.length / 4);
	}

	private double distance(byte[] data1, byte[] data2, int i) {
		return Math.sqrt(Math.pow(data1[i + 0] - data2[i + 0], 2) + Math.pow(data1[i + 1] - data2[i + 1], 2) + Math.pow(data1[i + 2] - data2[i + 2], 2) + Math.pow(data1[i + 3] - data2[i + 3], 2));
	}

	private void saveImage(String imageName, BufferedImage image) throws IOException {
		File file = new File(imageName);
		System.out.println(file.getAbsoluteFile());
		ImageIO.write(image, "png", file);
	}

	private BufferedImage loadImage(String imageName) throws IOException {
		return ImageIO.read(getResourceAsStream(imageName));
	}

	private class TestCanvas extends SimpleCanvas {
		private AffineTransform currTransform = new AffineTransform();
		private BufferedImage image;
		private Graphics2D g2d;

		private TestCanvas(int width, int height) {
			super(width, height);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}

		public void primitive(int shapeType, double[] color, AffineTransform transform) {
			g2d.setColor(new Color((float)color[0], (float)color[1], (float)color[2], (float)color[3]));
			AffineTransform oldTransform = g2d.getTransform();
			AffineTransform t = new AffineTransform(currTransform);
			t.concatenate(transform);
			g2d.setTransform(t);
			PrimShape primShape = PrimShape.getShapeMap().get(shapeType);
			if (primShape != null) {
				g2d.fill(primShape.getPath());
			} else {
				throw new RuntimeException("Unexpected shape " + shapeType);
			}
			g2d.setTransform(oldTransform);
		}

		public void path(double[] color, AffineTransform transform, CommandInfo attr) {
			g2d.setColor(new Color((float)color[0], (float)color[1], (float)color[2], (float)color[3]));
			AffineTransform oldTransform = g2d.getTransform();
			AffineTransform t = new AffineTransform(currTransform);
			t.concatenate(transform);
			g2d.setTransform(t);
			g2d.setStroke(new BasicStroke((float)attr.getStrokeWidth(), mapToCap(attr.getFlags()), mapToJoin(attr.getFlags()), (float)attr.getMiterLimit()));
			ExtendedGeneralPath path = attr.getPathStorage().getGeneralPath();
			if ((attr.getFlags() & FlagType.CF_FILL.getMask()) != 0) {
				g2d.fill(path);
			} else {
				g2d.draw(path);
			}
			g2d.setTransform(oldTransform);
		}

		private int mapToJoin(int flags) {
			if ((flags & FlagType.CF_MITER_JOIN.getMask()) != 0) {
				return BasicStroke.JOIN_MITER;
			} else if ((flags & FlagType.CF_ROUND_JOIN.getMask()) != 0) {
				return BasicStroke.JOIN_ROUND;
			} else if ((flags & FlagType.CF_BEVEL_JOIN.getMask()) != 0) {
				return BasicStroke.JOIN_BEVEL;
			} else {
				throw new RuntimeException("Invalid flags " + flags);
			}
		}

		private int mapToCap(int flags) {
			if ((flags & FlagType.CF_BUTT_CAP.getMask()) != 0) {
				return BasicStroke.CAP_BUTT;
			} else if ((flags & FlagType.CF_ROUND_CAP.getMask()) != 0) {
				return BasicStroke.CAP_ROUND;
			} else if ((flags & FlagType.CF_SQUARE_CAP.getMask()) != 0) {
				return BasicStroke.CAP_SQUARE;
			} else {
				throw new RuntimeException("Invalid flags " + flags);
			}
		}

		public void start(boolean first, double[] backgroundColor, int currWidth, int currHeight) {
			g2d = image.createGraphics();
			g2d.setColor(new Color((float)backgroundColor[0], (float)backgroundColor[1], (float)backgroundColor[2], (float)backgroundColor[3]));
			g2d.fillRect(0, 0, getWidth(), getHeight());
			currTransform = AffineTransform.getTranslateInstance(0, getHeight());
			currTransform.scale(1, -1);
			currTransform.translate(-(currWidth - getWidth()) / 2, -(currHeight - getHeight()) / 2);
		}

		public void end() {
			g2d.dispose();
		}

		public BufferedImage getImage() {
			return image;
		}
	}
}

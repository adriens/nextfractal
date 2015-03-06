package com.nextbreakpoint.nextfractal.core.renderer.java2D;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

import com.nextbreakpoint.nextfractal.core.renderer.RendererAffine;
import com.nextbreakpoint.nextfractal.core.renderer.RendererColor;
import com.nextbreakpoint.nextfractal.core.renderer.RendererFont;
import com.nextbreakpoint.nextfractal.core.renderer.RendererGraphicsContext;
import com.nextbreakpoint.nextfractal.core.renderer.RendererImage;

public class Java2DRendererGraphicsContext implements RendererGraphicsContext {
	private Graphics2D g2d;
	private Stack<State> stack = new Stack<>();
	private GeneralPath shape;
	private RendererColor strokeColor;
	private RendererColor fillColor;

	public Java2DRendererGraphicsContext(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public void setStroke(RendererColor color) {
		strokeColor = color;
	}

	public void setFill(RendererColor color) {
		fillColor = color;
	}

	public void setFont(RendererFont font) {
		font.setFont(this);
	}

	public void rect(int x, int y, int width, int height) {
		if (shape == null) {
			beginPath();
		}
		shape.append(new Rectangle2D.Double(x, y, width, height), false);
	}
	
	public void stroke() {
		if (shape != null) {
			g2d.draw(shape);
		}
	}

	public void fill() {
		if (shape != null) {
			g2d.fill(shape);
		}
	}

	public void clip() {
		if (shape != null) {
			g2d.clip(shape);
		}
	}

	public void beginPath() {
		shape = new GeneralPath();
	}

	public void closePath() {
		if (shape != null) {
			shape.closePath();
		}
	}

	public void strokeRect(int x, int y, int width, int height) {
		Color color = g2d.getColor();
		strokeColor.setStroke(this);
		g2d.drawRect(x, y, width, height);
		g2d.setColor(color);
	}
	
	public void fillRect(int x, int y, int width, int height) {
		Color color = g2d.getColor();
		fillColor.setFill(this);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(color);
	}
	
	public void strokeText(String text, int x, int y) {
		Color color = g2d.getColor();
		strokeColor.setStroke(this);
		g2d.drawString(text, x, y);
		g2d.setColor(color);
	}

	public void fillText(String text, int x, int y) {
		Color color = g2d.getColor();
		fillColor.setFill(this);
		g2d.drawString(text, x, y);
		g2d.setColor(color);
	}

	public void drawImage(RendererImage image, int x, int y) {
		image.draw(this, x, y);
	}

	public void drawImage(RendererImage image, int x, int y, int w, int h) {
		image.draw(this, x, y, w, h);
	}

	public void clearRect(int x, int y, int width, int height) {
		Color color = g2d.getColor();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.clearRect(x, y, width, height);
		g2d.setColor(color);
	}

	public void setAffine(RendererAffine affine) {
		affine.setAffine(this);
	}

	public void save() {
		State state = new State();
		state.transform = g2d.getTransform();
		state.clip = g2d.getClip();
		stack.push(state);
	}

	public void restore() {
		State state = stack.pop();
		g2d.setTransform(state.transform);
		g2d.setClip(state.clip);
	}

	public Graphics2D getGraphicsContext() {
		return g2d;
	}

	public void setClip(int x, int y, int width, int height) {
		g2d.setClip(x, y, width, height);
	}

	public void clearClip() {
		g2d.setClip(null);
	}
	
	private class State {
		AffineTransform transform;
		Shape clip;
	}

	@Override
	public void moveTo(int x, int y) {
		shape.moveTo(x, y);
	}

	@Override
	public void lineTo(int x, int y) {
		shape.lineTo(x, y);
	}

	@Override
	public void setAlpha(double alpha) {
		g2d.setComposite(AlphaComposite.SrcOver.derive((float)alpha));
	}
}
package com.nextbreakpoint.nextfractal.core.ui.javafx.util;

import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;

@FunctionalInterface
public interface ElementGridDelegate {
	public void didSelectValue(NodeObject node);
}

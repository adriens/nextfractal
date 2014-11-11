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
package com.nextbreakpoint.nextfractal.contextfree.extensions.image;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeConfigNodeBuilder;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;

/**
 * @author Andrea Medeghini
 */
public class ContextFreeImageConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ContextFreetImageNodeBuilder((ContextFreeImageConfig) config);
	}

	private class ContextFreetImageNodeBuilder implements NodeBuilder {
		private final ContextFreeImageConfig config;

		/**
		 * @param config
		 */
		public ContextFreetImageNodeBuilder(final ContextFreeImageConfig config) {
			this.config = config;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeBuilder#createNodes(NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			final ContextFreeConfigNodeBuilder builder = new ContextFreeConfigNodeBuilder(config.getContextFreeConfig());
			builder.createNodes(parentNode);
		}
	}
}

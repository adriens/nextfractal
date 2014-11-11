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
package com.nextbreakpoint.nextfractal.twister.layerFilter;

import com.nextbreakpoint.nextfractal.core.elements.ExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener;
import com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement;
import com.nextbreakpoint.nextfractal.core.runtime.ValueConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionNotFoundException;
import com.nextbreakpoint.nextfractal.twister.TwisterRegistry;
import com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionConfig;
import com.nextbreakpoint.nextfractal.twister.extensionPoints.layerFilter.LayerFilterExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class LayerFilterRuntimeElement extends RuntimeElement {
	private LayerFilterExtensionRuntime<?> filterRuntime;
	private LayerFilterConfigElement filterElement;
	private ExtensionListener extensionListener;
	private EnabledListener enabledListener;
	private boolean enabled;

	/**
	 * Constructs a new filter.
	 * 
	 * @param registry
	 * @param filterElement
	 */
	public LayerFilterRuntimeElement(final LayerFilterConfigElement filterElement) {
		if (filterElement == null) {
			throw new IllegalArgumentException("filterElement is null");
		}
		this.filterElement = filterElement;
		createRuntime(filterElement.getReference());
		extensionListener = new ExtensionListener();
		filterElement.getExtensionElement().addChangeListener(extensionListener);
		setEnabled(filterElement.isEnabled());
		enabledListener = new EnabledListener();
		filterElement.getEnabledElement().addChangeListener(enabledListener);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((filterElement != null) && (extensionListener != null)) {
			filterElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if ((filterElement != null) && (enabledListener != null)) {
			filterElement.getEnabledElement().removeChangeListener(enabledListener);
		}
		enabledListener = null;
		if (filterRuntime != null) {
			filterRuntime.dispose();
			filterRuntime = null;
		}
		filterElement = null;
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<LayerFilterExtensionConfig> reference) {
		try {
			if (reference != null) {
				final LayerFilterExtensionRuntime filterRuntime = TwisterRegistry.getInstance().getLayerFilterExtension(reference.getExtensionId()).createExtensionRuntime();
				final LayerFilterExtensionConfig filterConfig = reference.getExtensionConfig();
				filterRuntime.setConfig(filterConfig);
				setFilterRuntime(filterRuntime);
			}
			else {
				setFilterRuntime(null);
			}
		}
		catch (final ExtensionNotFoundException e) {
			e.printStackTrace();
		}
		catch (final ExtensionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		final boolean filterChanged = (filterRuntime != null) && filterRuntime.isChanged();
		return super.isChanged() || filterChanged;
	}

	/**
	 * @return the filterRuntime
	 */
	public LayerFilterExtensionRuntime<?> getFilterRuntime() {
		return filterRuntime;
	}

	private void setFilterRuntime(final LayerFilterExtensionRuntime<?> filterRuntime) {
		if (this.filterRuntime != null) {
			this.filterRuntime.dispose();
		}
		this.filterRuntime = filterRuntime;
	}

	/**
	 * @return the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	private class ExtensionListener implements ElementChangeListener {
		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener#valueChanged(com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent)
		 */
		@Override
		@SuppressWarnings("unchecked")
		public void valueChanged(final ElementChangeEvent e) {
			switch (e.getEventType()) {
				case ExtensionReferenceElement.EXTENSION_REFERENCE_CHANGED: {
					createRuntime((ConfigurableExtensionReference<LayerFilterExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}

	private class EnabledListener implements ElementChangeListener {
		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener#valueChanged(com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent)
		 */
		@Override
		public void valueChanged(final ElementChangeEvent e) {
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setEnabled((Boolean) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}

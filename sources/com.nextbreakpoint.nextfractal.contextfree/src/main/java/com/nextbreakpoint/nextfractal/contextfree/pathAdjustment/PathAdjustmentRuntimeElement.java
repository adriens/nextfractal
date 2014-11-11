/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.pathAdjustment;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeRegistry;
import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathAdjustment.PathAdjustmentExtensionConfig;
import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathAdjustment.PathAdjustmentExtensionRuntime;
import com.nextbreakpoint.nextfractal.contextfree.renderer.support.CFModification;
import com.nextbreakpoint.nextfractal.core.elements.ExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener;
import com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionNotFoundException;

/**
 * @author Andrea Medeghini
 */
 public class PathAdjustmentRuntimeElement extends RuntimeElement {
	private PathAdjustmentConfigElement pathAdjustmentElement;
	private PathAdjustmentExtensionRuntime<?> extensionRuntime;
	private ExtensionListener extensionListener;

	/**
	 * Constructs a new PathAdjustmentRuntimeElement.
	 * 
	 * @param registry
	 * @param PathAdjustmentRuntimeElementElement
	 */
	public PathAdjustmentRuntimeElement(final PathAdjustmentConfigElement pathAdjustmentElement) {
		if (pathAdjustmentElement == null) {
			throw new IllegalArgumentException("pathAdjustmentElement is null");
		}
		this.pathAdjustmentElement = pathAdjustmentElement;
		createRuntime(pathAdjustmentElement.getExtensionReference());
		extensionListener = new ExtensionListener();
		pathAdjustmentElement.getExtensionElement().addChangeListener(extensionListener);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement#dispose()
	 */
	@Override
	public void dispose() {
		if ((pathAdjustmentElement != null) && (extensionListener != null)) {
			pathAdjustmentElement.getExtensionElement().removeChangeListener(extensionListener);
		}
		extensionListener = null;
		if (extensionRuntime != null) {
			extensionRuntime.dispose();
			extensionRuntime = null;
		}
		pathAdjustmentElement = null;
		super.dispose();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.RuntimeElement#isChanged()
	 */
	@Override
	public boolean isChanged() {
		boolean pathAdjustmentChanged = false;
		pathAdjustmentChanged |= (extensionRuntime != null) && extensionRuntime.isChanged();
		return super.isChanged() || pathAdjustmentChanged;
	}

	@SuppressWarnings("unchecked")
	private void createRuntime(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference) {
		try {
			if (reference != null) {
				final PathAdjustmentExtensionRuntime extensionRuntime = ContextFreeRegistry.getInstance().getPathAdjustmentExtension(reference.getExtensionId()).createExtensionRuntime();
				final PathAdjustmentExtensionConfig extensionConfig = reference.getExtensionConfig();
				extensionRuntime.setConfig(extensionConfig);
				setExtensionRuntime(extensionRuntime);
			}
			else {
				setExtensionRuntime(null);
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
	 * @return the PathAdjustmentExtensionRuntimeRuntime
	 */
	public PathAdjustmentExtensionRuntime<?> getExtensionRuntime() {
		return extensionRuntime;
	}

	private void setExtensionRuntime(final PathAdjustmentExtensionRuntime<?> extensionRuntime) {
		if (this.extensionRuntime != null) {
			this.extensionRuntime.dispose();
		}
		this.extensionRuntime = extensionRuntime;
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
					createRuntime((ConfigurableExtensionReference<PathAdjustmentExtensionConfig>) e.getParams()[0]);
					fireChanged();
					break;
				}
				default: {
					break;
				}
			}
		}
	}
	
	public void apply(CFModification mod) {
		if (extensionRuntime != null) {
			extensionRuntime.apply(mod);
		}
	}
}

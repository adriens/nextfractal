/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.pathAdjustment;

import com.nextbreakpoint.nextfractal.contextfree.CFDGBuilder;
import com.nextbreakpoint.nextfractal.contextfree.pathAdjustment.extension.PathAdjustmentExtensionConfig;
import com.nextbreakpoint.nextfractal.core.common.ConfigurableExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.config.AbstractConfigElement;
import com.nextbreakpoint.nextfractal.core.config.ConfigContext;
import com.nextbreakpoint.nextfractal.core.config.ConfigElement;
import com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtensionReference;

/**
 * @author Andrea Medeghini
 */
public class PathAdjustmentConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "PathAdjustment";
	private final ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public PathAdjustmentConfigElement() {
		super(PathAdjustmentConfigElement.CLASS_ID);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.AbstractConfigElement#setContext(com.nextbreakpoint.nextfractal.core.config.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public PathAdjustmentConfigElement clone() {
		final PathAdjustmentConfigElement element = new PathAdjustmentConfigElement();
		if (getExtensionReference() != null) {
			element.setExtensionReference(getExtensionReference().clone());
		}
		return element;
	}
	
	/**
	 *
	 */
	@Override
	public void copyFrom(ConfigElement source) {
		PathAdjustmentConfigElement pathAdjustmentElement = (PathAdjustmentConfigElement) source;
		if (pathAdjustmentElement.getExtensionReference() != null) {
			setExtensionReference(pathAdjustmentElement.getExtensionReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<PathAdjustmentExtensionConfig> getExtensionElement() {
		return extensionElement;
	}
	
	/**
	 * @return
	 */
	public ConfigurableExtensionReference<PathAdjustmentExtensionConfig> getExtensionReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setExtensionReference(final ConfigurableExtensionReference<PathAdjustmentExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}
		
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final PathAdjustmentConfigElement other = (PathAdjustmentConfigElement) obj;
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.config.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		super.dispose();
	}

	public void toCFDG(CFDGBuilder builder) {
		if (extensionElement.getReference() != null) {
			if (extensionElement.getReference().getExtensionConfig() != null) {
				extensionElement.getReference().getExtensionConfig().toCFDG(builder);
			}
		}
	}
}

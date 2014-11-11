/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.action;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeRegistry;
import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.shapeAdjustment.ShapeAdjustmentExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.util.AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime;
/**
 * @author Andrea Medeghini
 */
public class ShapeAdjustmentReferenceNodeActionXMLImporterRuntime extends AbstractConfigurableExtensionReferenceElementNodeActionXMLImporterRuntime<ShapeAdjustmentExtensionConfig> {
	/**
	 * 
	 */
	public ShapeAdjustmentReferenceNodeActionXMLImporterRuntime() {
		super(ContextFreeRegistry.getInstance().getShapeAdjustmentRegistry());
	}
}

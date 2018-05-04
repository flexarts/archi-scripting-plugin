/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import com.archimatetool.model.IDiagramModelArchimateConnection;

/**
 * Diagram Model Archimate Concept wrapper proxy
 * 
 * @author Phillip Beauvoir
 */
public class DiagramModelArchimateConnectionProxy extends DiagramModelArchimateComponentProxy {
    
    public DiagramModelArchimateConnectionProxy() {
    }
    
    public DiagramModelArchimateConnectionProxy(IDiagramModelArchimateConnection component) {
        super(component);
    }
    
    @Override
    public ArchimateRelationshipProxy getArchimateConcept() {
        return (ArchimateRelationshipProxy)super.getEObject();
    }
    
    @Override
    protected IDiagramModelArchimateConnection getEObject() {
        return (IDiagramModelArchimateConnection)super.getEObject();
    }
}

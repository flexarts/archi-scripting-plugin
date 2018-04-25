/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import com.archimatetool.model.IArchimateConcept;

/**
 * Archimate Concept wrapper proxy
 * 
 * @author Phillip Beauvoir
 */
public abstract class ArchimateConceptProxy extends EObjectProxy {
    
    public ArchimateConceptProxy() {
    }
    
    public ArchimateConceptProxy(IArchimateConcept concept) {
        super(concept);
    }
    
    @Override
    protected IArchimateConcept getEObject() {
        return (IArchimateConcept)super.getEObject();
    }

    public String getDocumentation() {
        return (String)attr(DOCUMENTATION);
    }
    
    public ArchimateConceptProxy setDocumentation(String documentation) {
        return (ArchimateConceptProxy)attr(DOCUMENTATION, documentation);
    }
    
    @Override
    protected boolean canReadAttr(String attribute) {
        return super.canReadAttr(attribute) || DOCUMENTATION.equals(attribute);
    }

    @Override
    protected boolean canWriteAttr(String attribute) {
        return super.canReadAttr(attribute) || DOCUMENTATION.equals(attribute);
    }

}

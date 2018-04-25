/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import com.archimatetool.model.FolderType;
import com.archimatetool.model.IFolder;

/**
 * DiagramModel wrapper proxy
 * 
 * @author Phillip Beauvoir
 */
public class FolderProxy extends EObjectProxy {
    
    public FolderProxy() {
    }
    
    public FolderProxy(IFolder folder) {
        super(folder);
    }
    
    @Override
    protected IFolder getEObject() {
        return (IFolder)super.getEObject();
    }
    
    @Override
    protected boolean canWriteAttr(String attribute) {
        // Can only rename user folders
        if(NAME.equals(attribute) && getEObject().getType() != FolderType.USER) {
            return false;
        }
        
        return super.canReadAttr(attribute) || DOCUMENTATION.equals(attribute);
    }
}

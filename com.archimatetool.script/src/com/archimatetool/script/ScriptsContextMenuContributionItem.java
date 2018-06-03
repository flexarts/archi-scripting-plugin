/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import com.archimatetool.editor.utils.FileUtils;


/**
 * Contribute Menu Items to context menu
 * 
 * @author Phillip Beauvoir
 */
public class ScriptsContextMenuContributionItem extends ContributionItem implements IWorkbenchContribution {
    
    private MenuManager menuManager;
    
    public ScriptsContextMenuContributionItem() {
    }

    public ScriptsContextMenuContributionItem(String id) {
        super(id);
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
    
    @Override
    public void fill(Menu menu, int index) {
        if(menuManager != null) {
            menuManager.dispose();
        }
        
        menuManager = new MenuManager();
        
        fillItems(menuManager, ArchiScriptPlugin.INSTANCE.getUserScriptsFolder().listFiles());
        
        for(IContributionItem item : menuManager.getItems()) {
            item.fill(menu, index++);
        }
    }

    private void fillItems(MenuManager menuManager, File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                if(f1.isDirectory() && f2.isFile()) {
                    return -1;
                }
                return f1.compareTo(f2);
            }
        });
        
        for(File file : files) {
            if(file.isDirectory() && doShowFile(file)) {
                MenuManager subMenu = new MenuManager(file.getName());
                subMenu.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER));
                menuManager.add(subMenu);
                fillItems(subMenu, file.listFiles());
            }
            else if(doShowFile(file)) {
                menuManager.add(new Action(FileUtils.getFileNameWithoutExtension(file),
                        IArchiScriptImages.ImageFactory.getImageDescriptor(IArchiScriptImages.ICON_SCRIPT)) {

                    @Override
                    public void run() {
                        RunArchiScript script = new RunArchiScript(file);
                        script.run();
                    }
                });
            }
        }
    }
    
    private boolean doShowFile(File file) {
        if(file.isDirectory()) {
            for(File f : file.listFiles()) {
                if(doShowFile(f)) {
                    return true;
                }
            }
            return false;
        }
        
        return file.getName().toLowerCase().endsWith(ArchiScriptPlugin.SCRIPT_EXTENSION);
    }
    
    public void initialize(IServiceLocator serviceLocator) {
    }
}

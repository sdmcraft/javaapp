/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.core;

import java.io.File;
import java.util.List;

/**
 *
 * @author satyam
 */
public class CollateRootTask implements Runnable {

    private final String root;
    private final List<DiffFile> diffFileList;

    public CollateRootTask(String root, List<DiffFile> diffFileList) {
        this.root = root;
        this.diffFileList = diffFileList;
    }

    @Override
    public void run() {
        List<File> fileList = DirectoryUtils.listContents(root);
        for (File f : fileList) {
            try {
                String strippedName = DirectoryUtils.stripRoot(f, root);
                boolean found = false;
                synchronized (diffFileList) {
                    for (DiffFile diffFile : diffFileList) {
                        if (strippedName.equals(diffFile.name)) {
                            diffFile.add(f, root);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        diffFileList.add(new DiffFile(strippedName, f, root));
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

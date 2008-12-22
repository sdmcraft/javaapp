/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.core;

/**
 *
 * @author satyam
 */
public class BuildTextBucketsTask implements Runnable {

    private final DiffFile[] batch;

    public BuildTextBucketsTask(DiffFile[] batch) {
        this.batch = batch;
    }

    @Override
    public void run() {
        try {
        	if(batch == null)
        		return;
            for (DiffFile f : batch) {
            	if(f==null)
            		continue;
                f.buildTextBuckets();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

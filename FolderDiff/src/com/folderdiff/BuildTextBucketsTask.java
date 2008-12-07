/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff;

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
            for (DiffFile f : batch) {
                if(f == null)
                    break;
                f.buildTextBuckets();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}

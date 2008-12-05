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
                f.buildTextBuckets();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

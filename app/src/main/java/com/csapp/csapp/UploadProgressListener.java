package com.csapp.csapp;

/**
 * Created by Shubhi on 4/28/2016.
 */
public interface UploadProgressListener {
    /**
     * This method updated how much data size uploaded to server
     * @param num
     */
    void transferred(long num);
}

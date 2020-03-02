package com.hyl.service;

import java.util.concurrent.Future;

public interface SyncService {

    public void asyncEvent();


    public void asyncEvent01();

    /**
     * 有返回值的
     * @return
     */
    public Future<String> asyncEvent02();


    public void syncEvent();

}

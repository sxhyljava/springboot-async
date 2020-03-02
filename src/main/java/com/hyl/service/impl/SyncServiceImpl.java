package com.hyl.service.impl;

import com.hyl.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class SyncServiceImpl implements SyncService{


    @Override
    @Async
    public void asyncEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncEvent 异步方法输出：{}!  |  name = {}", System.currentTimeMillis(),Thread.currentThread().getName());
    }

    @Override
    @Async("taskServiceExecutor")
    public void asyncEvent01() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncEvent01异步方法输出：{}!  |  name = {}", System.currentTimeMillis(),Thread.currentThread().getName());
    }

    @Override
    @Async("taskServiceExecutor")
    public Future<String> asyncEvent02() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncEvent02异步方法输出：{}!  |  name = {}", System.currentTimeMillis(),Thread.currentThread().getName());
        return new AsyncResult<>("异步方法返回值");
    }

    @Override
    public void syncEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("同步方法输出：{}!   |  name = {}", System.currentTimeMillis(),Thread.currentThread().getName());
    }
}

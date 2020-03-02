package com.hyl.controller;

import com.hyl.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author
 * @Description
 * @date 2020-03-02 14:21
 */
@Slf4j
@RestController
public class AsyncController {

    @Resource
    private SyncService syncService;


    @RequestMapping("/async")
    public String doAsync(){
        long start = System.currentTimeMillis();
        log.info("方法执行开始：{}", start);

        //调用同步方法
        syncService.syncEvent();
        long syncTime = System.currentTimeMillis();
        log.info("同步方法用时：{}", syncTime - start);

        //调用异步方法
        syncService.asyncEvent();
        long asyncTime = System.currentTimeMillis();
        log.info("asyncEvent异步方法用时：{}", asyncTime - syncTime);
        //log.info("方法执行完成：{}!",asyncTime);

        //调用异步方法01
        syncService.asyncEvent01();
        long asyncTime01 = System.currentTimeMillis();
        log.info("asyncEvent01异步方法用时：{}", asyncTime01 - asyncTime);

        //调用异步方法02
        Future<String> doFuture = syncService.asyncEvent02();
        while (true){
            //判断异步任务是否完成
            if(doFuture.isDone()){
                break;
            }
        }
        try {
            log.info("doFuture 返回值： " + doFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long asyncTime02 = System.currentTimeMillis();
        log.info("asyncEvent02异步方法用时：{}", asyncTime02 - asyncTime01);

        log.info("方法执行完成：{}!",asyncTime02);
        return "ok";
    }





}

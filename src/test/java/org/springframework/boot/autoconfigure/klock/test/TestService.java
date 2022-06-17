package org.springframework.boot.autoconfigure.klock.test;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.annotation.KlockKey;
import org.springframework.boot.autoconfigure.klock.model.LockTimeoutStrategy;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.stereotype.Service;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by kl on 2017/12/29.
 */
@Logger
@Service
public class TestService {
    private static Integer i = 1;

    @Klock(waitTime =10,leaseTime = 60,keys = {"#param"},lockTimeoutStrategy = LockTimeoutStrategy.FAIL_FAST)
    public String getValue(String param) throws Exception {
        if ("sleep".equals(param)) {//线程休眠或者断点阻塞，达到一直占用锁的测试效果
            Thread.sleep(1000*3);
        }
        return "success";
    }

    @Klock(keys = {"#userId"})
    public String getValue(String userId,@KlockKey int id)throws Exception{
        Thread.sleep(60*1000);
        return "success";
    }

    @Klock(keys = {"#user.name","#user.id"})
    public String getValue(User user)throws Exception{
        Thread.sleep(60*1000);
        return "success";
    }

}

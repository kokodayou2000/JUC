package com.deng.juc.c_021_02_AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Sync extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        //通过cas尝试设置当前线程
        if (compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        //独占线程设置为null
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() { //是独占的嘛？
        //判断是否为1
        return getState() == 1;
    }
}

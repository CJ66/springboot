/*******************************************************************************
 * @(#)Counter.java 2017年7月14日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年7月14日 上午11:50:46
 */
public class Counter {

    private static final Counter COUNTER = new Counter();

    private Timer timer = new Timer();

    class MyTimerTask extends TimerTask {

        /**
         * run TODO 这里请补充overriding方法的简要说明 TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
         * @see java.util.TimerTask#run()
         */
        @Override
        public void run() {
            System.out.println("rate:" + get());
            clear();
        }
    }

    private Counter() {

    }

    public static Counter getInstance() {
        return COUNTER;
    }

    private AtomicInteger num = new AtomicInteger();

    public void add() {
        num.incrementAndGet();
    }

    public int get() {
        return num.get();
    }

    public void clear() {
        num.set(0);
    }

    public void start() {
        timer.schedule(new MyTimerTask(), 0, 1000);
    }

    public void stop() {
        timer.cancel();
    }

}

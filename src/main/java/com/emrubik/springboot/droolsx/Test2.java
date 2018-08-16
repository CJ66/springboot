/*******************************************************************************
 * @(#)Test.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月22日 上午10:02:20
 */
public class Test2 {
    
    private static Logger log = LoggerFactory.getLogger(Test2.class);
    
    public static String rule1 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
            + "rule airSwitch  \n"
            + "  salience 100  \n"
            + " lock-on-active true  \n"
            + " when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + "  then  \n"
            + "      AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
            + "      airSwitchControl.execute($ruleDomain,\"airSwitch\");\n"
            + " end";

    public static String rule2 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.LightingControl;\n"
            + "rule lighting  \n"
            + "   salience 99  \n"
            + "  lock-on-active true  \n"
            + "  when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + " then  \n"
            + "     LightingControl lightingControl = new LightingControl();\n"
            + "     lightingControl.execute($ruleDomain,\"lighting\");\n"
            + "end";

    public static String rule3 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.DoorControl;\n"
            + "rule door  \n"
            + "    salience 98\n"
            + "    lock-on-active true  \n"
            + "    when   \n"
            + "        $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + "    then  \n"
            + "        DoorControl doorControl = new DoorControl();\n"
            + "       doorControl.execute($ruleDomain,\"door\");\n"
            + "end";
    
    public static AtomicInteger count = new AtomicInteger();
    
    public static void main(String[] args) throws IOException {
        
        final PointRuleEngine pointRuleEngine = new RuleEngine();
        
        Long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 50; i++) {  
            new Thread(new Runnable() {  
                public void run() {  
                    for (int i = 0; i < 1000; i++) {
                        log.info("当前线程：" + Thread.currentThread().getName() + "---" + count.get());
                        
                        final RuleDomain ruleDomain = new RuleDomain();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("aaa", 400);
                        map.put("aab", 400);
                        ruleDomain.setMap(map);
                        
                        
                        String rule =null;
                        if(i%3 == 0){
                            rule = rule1;
                        }else if(i%3 == 1){
                            rule = rule2;
                        }else if(i%3 == 2){
                            rule = rule3;
                        }
                        pointRuleEngine.refreshEnginRule(rule);
                        pointRuleEngine.executeRuleEngine(ruleDomain);
                        
                        count.incrementAndGet();  
                    }
                }  
            }).start();  
        }
        
        Long endTime = System.currentTimeMillis();
        log.info("毫秒"+ (endTime - startTime));
        Long s = (endTime - startTime)/1000;
        log.info("秒："+s);
        log.info("数量："+count);
    }
 
}

//class Dog extends Thread {
//    
//    private PointRuleEngine pointRuleEngine;
//    
//    public Dog (PointRuleEngine pointRuleEngine){
//        this.pointRuleEngine = pointRuleEngine;
//    }
//    
//    
//    private Logger log = LoggerFactory.getLogger(getClass());
//    
//    public static String rule1 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
//            + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
//            + "rule airSwitch  \n"
//            + "  salience 100  \n"
//            + " lock-on-active true  \n"
//            + " when  \n"
//            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
//            + "  then  \n"
//            + "      AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
//            + "      airSwitchControl.execute($ruleDomain,\"airSwitch\");\n"
//            + " end";
//
//    public static String rule2 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
//            + "import com.emrubik.springboot.droolsx.LightingControl;\n"
//            + "rule lighting  \n"
//            + "   salience 99  \n"
//            + "  lock-on-active true  \n"
//            + "  when  \n"
//            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
//            + " then  \n"
//            + "     LightingControl lightingControl = new LightingControl();\n"
//            + "     lightingControl.execute($ruleDomain,\"lighting\");\n"
//            + "end";
//
//    public static String rule3 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
//            + "import com.emrubik.springboot.droolsx.DoorControl;\n"
//            + "rule door  \n"
//            + "    salience 98\n"
//            + "    lock-on-active true  \n"
//            + "    when   \n"
//            + "        $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
//            + "    then  \n"
//            + "        DoorControl doorControl = new DoorControl();\n"
//            + "       doorControl.execute($ruleDomain,\"door\");\n"
//            + "end";
//    /*
//     * 覆写run()方法，定义该线程需要执行的代码
//     */
//    @Override
//    public void run() {
//        Long startTime = System.currentTimeMillis();
//        int count = 0;
//        for (int i = 0; i < 1000; i++) {
//            log.info("当前线程：" + Thread.currentThread().getName() + "---" + i);
//            
//            final RuleDomain ruleDomain = new RuleDomain();
//            Map<Object, Object> map = new HashMap<>();
//            map.put("aaa", 400);
//            map.put("aab", 400);
//            ruleDomain.setMap(map);
//            
//            
//            String rule =null;
//            if(i%3 == 0){
//                rule = rule1;
//            }else if(i%3 == 1){
//                rule = rule2;
//            }else if(i%3 == 2){
//                rule = rule3;
//            }
//            pointRuleEngine.refreshEnginRule(rule);
//            pointRuleEngine.executeRuleEngine(ruleDomain);
////            pointRuleEngine.refreshEnginRule(rule2);
////            pointRuleEngine.executeRuleEngine(ruleDomain);
////            pointRuleEngine.refreshEnginRule(rule3);
////            pointRuleEngine.executeRuleEngine(ruleDomain);
//            
//            count++;
//        }
//        Long endTime = System.currentTimeMillis();
//        log.info("毫秒"+ (endTime - startTime));
//        Long s = (endTime - startTime)/1000;
//        log.info("秒："+s);
//        log.info("数量："+count);
//        Long a = count/s;
//        log.info("条/秒："+a);
//    }
//}

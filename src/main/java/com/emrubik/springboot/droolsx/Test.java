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

import org.antlr.grammar.v3.ANTLRv3Parser.atom_return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月22日 上午10:02:20
 */
public class Test {

    private static Logger log = LoggerFactory.getLogger(Test.class);

    private static String rule1 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n" + "rule airSwitch  \n"
            + "  salience 100  \n" + " lock-on-active true  \n" + " when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + "  then  \n" + "      AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
            + "      airSwitchControl.execute($ruleDomain,\"airSwitch\");\n" + " end";

    private static String rule2 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.LightingControl;\n" + "rule lighting  \n"
            + "   salience 99  \n" + "  lock-on-active true  \n" + "  when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + " then  \n" + "     LightingControl lightingControl = new LightingControl();\n"
            + "     lightingControl.execute($ruleDomain,\"lighting\");\n" + "end";

    private static String rule3 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.DoorControl;\n" + "rule door  \n"
            + "    salience 98\n" + "    lock-on-active true  \n" + "    when   \n"
            + "        $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + "    then  \n" + "        DoorControl doorControl = new DoorControl();\n"
            + "       doorControl.execute($ruleDomain,\"door\");\n" + "end";

    private static String rule4 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
            + "import com.emrubik.springboot.droolsx.LightingControl;\n"
            + "import com.emrubik.springboot.droolsx.DoorControl;\n" 
            + "rule airSwitch  \n"
            + "  salience 100  \n"
            + " lock-on-active true  \n" 
            + " when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180)  \n"
            + "  then  \n" 
            + "      AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
            + "      airSwitchControl.execute($ruleDomain,\"airSwitch\");\n" 
            + " end\n"
            + "rule lighting  \n"
            + "   salience 99  \n" + "  lock-on-active true  \n" + "  when  \n"
            + "     $ruleDomain : RuleDomain(map.get(\"baa\") > 300 && map.get(\"bab\") >= 180)  \n"
            + " then  \n" + "     LightingControl lightingControl = new LightingControl();\n"
            + "     lightingControl.execute($ruleDomain,\"lighting\");\n" + "end \n"
            + "rule door  \n"
            + "    salience 98\n" + "    lock-on-active true  \n" + "    when   \n"
            + "        $ruleDomain : RuleDomain(map.get(\"caa\") > 300 && map.get(\"cab\") >= 180)  \n"
            + "    then  \n" + "        DoorControl doorControl = new DoorControl();\n"
            + "       doorControl.execute($ruleDomain,\"door\");\n" + "end";
    
    private final static String rule5 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
                    + " import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
                    + " rule airSwitch  \n"
                    + "    salience 100  \n"
                    + "    lock-on-active true  \n"
                    + "    when  \n"
                    + "         $ruleDomain : RuleDomain((map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180) || (map.get(\"baa\") > 300 && map.get(\"bab\") >= 180) || (map.get(\"caa\") > 300 && map.get(\"cab\") >= 180)) \n" 
                    + "     then  \n"
                    + "         AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
                    + "        airSwitchControl.execute($ruleDomain,\"airSwitch\");\n"
                    + " end";
    
    private final static String rule6 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
                    + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
                    + "rule airSwitch  \n"
                    + "   salience 100  \n"
                    + "   lock-on-active true  \n"
                    + "   when  \n"
                    + "       $ruleDomain : RuleDomain((map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180) || (map.get(\"baa\") > 300 && map.get(\"bab\") >= 180) || (map.get(\"caa\") > 300 && map.get(\"cab\") >= 180) && (map.get(\"daa\") > 300 && map.get(\"dab\") >= 180) && map.get(\"daa\")>map.get(\"dab\") || map.get(\"caa\")>map.get(\"cab\")) \n" 
                    + "  then  \n"
                    + "      AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
                    + "      airSwitchControl.execute($ruleDomain,\"airSwitch\");\n"
                    + "end\n";
    
    private final static String rule7 = "import com.emrubik.springboot.droolsx.RuleDomain;\n"
            + "import com.emrubik.springboot.droolsx.AirSwitchControl;\n"
            + "rule airSwitch  \n"
            + "    salience 100  \n"
            + "   lock-on-active true  \n"
            + "   when  \n"
            + "       $ruleDomain : RuleDomain((map.get(\"aaa\") > 300 && map.get(\"aab\") >= 180) && (map.get(\"baa\") > 300 && map.get(\"bab\") >= 180) && (map.get(\"caa\") > 300 && map.get(\"cab\") >= 180) && (map.get(\"daa\") > 300 && map.get(\"dab\") >= 180) && map.get(\"daa\")>map.get(\"dab\") && (map.get(\"caa\")>map.get(\"cab\") && (map.get(\"eaa\") > 300 && map.get(\"eab\") >= 180) || ((map.get(\"faa\") > 300 && map.get(\"fab\") >= 180) && map.get(\"faa\") > map.get(\"fab\")) && ((map.get(\"haa\") > 300 && map.get(\"hab\") >= 180) && map.get(\"haa\") > map.get(\"hab\"))) || ((map.get(\"gaa\") > 300 && map.get(\"gab\") >= 180) && map.get(\"gaa\") > map.get(\"gab\")))\n"
            + "   then  \n"
            + "       AirSwitchControl airSwitchControl = new AirSwitchControl();\n"
            + "       airSwitchControl.execute($ruleDomain,\"airSwitch\");\n"
            + "end";
    
    public static void main(String[] args) throws IOException {

        RuleEngine pointRuleEngine = new RuleEngine();
        pointRuleEngine.refreshEnginRule(rule1);
        
        Counter.getInstance().start();

        for (int i = 0; i < 1; i++) {
            new Dog(pointRuleEngine).start();
        }
        RuleEngine2 pointRuleEngine2 = new RuleEngine2();
        pointRuleEngine2.refreshEnginRule(rule2);
        
        for (int i = 0; i < 1; i++) {
            new Dog(pointRuleEngine2).start();
        }
        
        RuleEngine3 pointRuleEngine3 = new RuleEngine3();
        pointRuleEngine3.refreshEnginRule(rule3);
        
        for (int i = 0; i < 1; i++) {
            new Dog(pointRuleEngine3).start();
        }
//        
//        RuleEngine pointRuleEngine4 = new RuleEngine();
//        pointRuleEngine4.refreshEnginRule(rule3);
//        
//        for (int i = 0; i < 1; i++) {
//            new Dog(pointRuleEngine4).start();
//        }
        
    }

    static class Dog extends Thread {

        private int current = 0;

        private PointRuleEngine pointRuleEngine;

        public Dog(PointRuleEngine pointRuleEngine) {
            this.pointRuleEngine = pointRuleEngine;
        }

        /*
         * 覆写run()方法，定义该线程需要执行的代码
         */
        @Override
        public void run() {
            while (true) {
                final RuleDomain ruleDomain = new RuleDomain();
                Map<Object, Object> map = new HashMap<>();
                map.put("aaa", 400);
                map.put("aab", 400);
                map.put("baa", 400);
                map.put("bab", 400);
                map.put("caa", 400);
                map.put("cab", 300);
                map.put("daa", 400);
                map.put("dab", 300);
                map.put("eaa", 400);
                map.put("eab", 300);
                map.put("faa", 200);
                map.put("fab", 300);
                map.put("gaa", 400);
                map.put("gab", 300);
                map.put("haa", 400);
                map.put("hab", 300);
                ruleDomain.setMap(map);

                pointRuleEngine.executeRuleEngine(ruleDomain);
                Counter.getInstance().add();
                current++;
            }
        }
    }
    
    
}

/*******************************************************************************
 * @(#)Test.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolstest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Maps;

import com.emrubik.springboot.droolsx.RuleDomain;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月22日 上午10:02:20
 */
public class Test {
    public static void main(String[] args) throws IOException {
        TestRuleEngine pointRuleEngine = new TestRuleEngineImpl();
        while (true) {
            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();

            if (null != input && "s".equals(input)) {
                System.out.println("初始化规则引擎...");
                pointRuleEngine.initEngine();
                System.out.println("初始化规则引擎结束.");
            } else if ("e".equals(input)) {
                final RuleDomain ruleDomain = new RuleDomain();
                Map<String,String> map = new HashMap<>();
                map.put("meetingBegin.door", "cccccccccc");
                
                Map<Object,Object> map1 = new HashMap<>();
                map1.put("meetingBegin.beginTime", "300");
                
                
                ruleDomain.setMap(map1);
                ruleDomain.setConditionMap(map);
                
                pointRuleEngine.executeRuleEngine(ruleDomain);
                System.out.println("执行完毕规则引擎完成");
            } else if ("r".equals(input)) {
                System.out.println("刷新规则文件...");
                pointRuleEngine.refreshEnginRule();
                System.out.println("刷新规则文件结束.");
            }
        }
    }
}

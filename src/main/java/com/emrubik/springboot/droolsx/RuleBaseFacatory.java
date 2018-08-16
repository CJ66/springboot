/*******************************************************************************
 * @(#)RuleBaseFacatory.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;  
  
/** 
 * RuleBaseFacatory 单实例RuleBase生成工具 
 * @author quzishen 
 */  
public class RuleBaseFacatory {  
    private static RuleBase ruleBase;  
      
    public static RuleBase getRuleBase(){  
//        System.out.println("RuleBaseFacatory :"+ruleBase);
        if(null != ruleBase){
            return ruleBase;
        }else{
            RuleBaseConfiguration config = new RuleBaseConfiguration();
            config.setMaxThreads(1000);
            ruleBase = RuleBaseFactory.newRuleBase(config);
            return ruleBase;
        }
    }  
     
} 

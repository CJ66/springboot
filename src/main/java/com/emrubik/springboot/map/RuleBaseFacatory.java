/*******************************************************************************
 * @(#)RuleBaseFacatory.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.map;

import org.drools.RuleBase;  
import org.drools.RuleBaseFactory;  
  
/** 
 * RuleBaseFacatory 单实例RuleBase生成工具 
 * @author quzishen 
 */  
public class RuleBaseFacatory {  
    private static RuleBase ruleBase;  
      
    public static RuleBase getRuleBase(){  
        return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();  
    }  
} 

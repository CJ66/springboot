/*******************************************************************************
 * @(#)DoorControl.java 2017年6月27日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolstest;

import java.io.Serializable;
import java.util.HashMap;

import com.emrubik.springboot.droolsx.RuleDomain;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月27日 下午2:39:42
 */
public class DoorControl implements Serializable{

    
    /**
     * serialVersionUID : TODO 这里请补充该字段的简述说明
     */
    private static final long serialVersionUID = 1L;
    
    public void commonexecute(RuleDomain ruleDomain,String key) {
        // TODO Auto-generated method stub
        System.out.println("门禁控制");
        System.out.println(key);
        System.out.println(ruleDomain.getConditionMap().get(key));
//        execute();
        
        
    }

    public void execute(RuleDomain ruleDomain,String key) {
        // TODO Auto-generated method stub
        System.out.println("门禁控制");
        System.out.println(key);
        System.out.println(ruleDomain.getConditionMap().get(key));
        
        
    }

}

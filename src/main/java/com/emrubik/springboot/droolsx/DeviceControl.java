/*******************************************************************************
 * @(#)DeviceControl.java 2017年6月26日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月26日 上午10:44:41
 */
public interface DeviceControl {
    
    /**
     * execute 控制执行
     * @param device 控制指令集合
     * @param json 保存条件信息
     */
    public void execute(RuleDomain ruleDomain,String json);
}

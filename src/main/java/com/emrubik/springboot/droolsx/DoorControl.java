/*******************************************************************************
 * @(#)DoorControl.java 2017年6月27日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月27日 下午2:39:42
 */
public class DoorControl implements DeviceControl{

    private Logger log = LoggerFactory.getLogger(getClass());
    public void execute(RuleDomain ruleDomain, String json) {
        // TODO Auto-generated method stub
//        log.info("门禁控制");
    }

}

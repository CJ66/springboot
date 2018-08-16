/*******************************************************************************
 * @(#)AirSwitchControl.java 2017年6月27日
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
 * @version $Revision 1.0 $ 2017年6月27日 下午2:40:33
 */
public class AirSwitchControl implements DeviceControl{
    private Logger log = LoggerFactory.getLogger(getClass());
    /** 
     * execute TODO 这里请补充overriding方法的简要说明
     * @param device
     * @param json TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see com.emrubik.intel.build.linkage.device.control.DeviceControl#execute(java.lang.Object, java.lang.String)
     */
    @Override
    public void execute(RuleDomain ruleDomain, String json) {
        // TODO Auto-generated method stub
        log.debug("空调开关控制");
//        log.info("空调开关控制");
        
//        try{
//            Thread.currentThread().sleep(1000);
//         }catch(InterruptedException ie){
//             ie.printStackTrace();
//         }
    }

}

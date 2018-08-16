/*******************************************************************************
 * @(#)TestDomain.java 2017年6月26日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolstest;

import java.util.Map;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月26日 下午2:03:29
 */
public class TestDomain {
    private Map<Object, Object> map;

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public void recordPointLog(String type) {
        System.out.println("测试TestDomain输出 : "+type);
    }
}

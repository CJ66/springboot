/*******************************************************************************
 * @(#)TestDomain.java 2017年6月26日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TODO 这里请补充该类型的简述说明
 * 
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月26日 下午2:03:29
 */
public class RuleDomain implements Serializable{

    /**
     * serialVersionUID : TODO 这里请补充该字段的简述说明
     */
    private static final long serialVersionUID = 1L;

    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 脚本实体
     */
    private Map<Object, Object> map;

    /**
     * 设备组
     */
    private String unitGroup;

    /**
     * 操作
     */
    private Map<String, String> conditionMap;

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getUnitGroup() {
        return unitGroup;
    }

    public void setUnitGroup(String unitGroup) {
        this.unitGroup = unitGroup;
    }

    public Map<String, String> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }

    /**
     * recordPointLog
     * 
     * @param type
     *            类型
     */
    public void recordPointLog(String type) {
        log.info("脚本控制类型: " + type);
    }
}

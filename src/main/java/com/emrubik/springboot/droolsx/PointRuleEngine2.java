/*******************************************************************************
 * @(#)PointRuleEngine.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月22日 上午9:55:33
 */
public interface PointRuleEngine2 {
    /**
     * 初始化规则引擎
     */
    public void initEngine(String ruleContext);

    /**
     * 刷新规则引擎中的规则
     */
    public void refreshEnginRule(String ruleContext);

    /**
     * 执行规则引擎
     * @param pointDomain 积分Fact
     */
    public void executeRuleEngine(final RuleDomain ruleDomain);
}

/*******************************************************************************
 * @(#)PointRuleEngineImpl.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolsx;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.spi.Activation;

/**
 * 规则实现
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月27日 下午3:15:15
 */
public class RuleEngine2 implements PointRuleEngine{
    /**
     * ruleBase
     */
    private RuleBase ruleBase;

    /**
     * initEngine 规则初始化
     * @param ruleContext 规则内容
     */
    public void initEngine(String ruleContext) {
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        ruleBase = RuleBaseFacatory2.getRuleBase();
        System.out.println("RuleEngine2:"+ruleBase);
        try {
            PackageBuilder backageBuilder = getPackBuilderFromDrlDB(ruleContext);
            ruleBase.addPackages(backageBuilder.getPackages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * refreshEnginRule 规则刷新
     * @param ruleContext 规则内容
     */
    public void refreshEnginRule(String ruleContext) {
        ruleBase = RuleBaseFacatory2.getRuleBase();
        org.drools.rule.Package[] packages = ruleBase.getPackages();
        for (org.drools.rule.Package pg : packages) {
            ruleBase.removePackage(pg.getName());
        }
        initEngine(ruleContext);
    }

    /**
     * executeRuleEngine 规则执行
     * @param ruleDomain 规则实体
     */
    public void executeRuleEngine(final RuleDomain ruleDomain) {
        if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
            return;
        }

        StatefulSession statefulSession = ruleBase.newStatefulSession();
        statefulSession.insert(ruleDomain);

        // fire
        statefulSession.fireAllRules(new org.drools.spi.AgendaFilter() {
            public boolean accept(Activation activation) {
                return !activation.getRule().getName().contains("_test");
            }
        });

        statefulSession.dispose();
    }

    /**
     * getPackBuilderFromDrlDB 从Drl规则DB中读取规则
     * @param ruleContext 规则内容
     * @return TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     */
    private PackageBuilder getPackBuilderFromDrlDB(String ruleContext) {
        // 装载规则
        List<Reader> readers = buildReadersFromDrlDB(ruleContext);

        // 装载PackageBuilder
        try {
            return buildPackageBuilder(readers);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * buildPackageBuilder 规则装载
     * @param readers readers
     * @return PackageBuilder
     * @throws Exception 异常处理
     */
    private PackageBuilder buildPackageBuilder(List<Reader> readers) throws Exception {
        if (null == readers || 0 == readers.size()) {
            return null;
        }

        PackageBuilder backageBuilder = new PackageBuilder();
        for (Reader r : readers) {
            backageBuilder.addPackageFromDrl(r);
        }

        // 检查脚本是否有问题
        if (backageBuilder.hasErrors()) {
            throw new Exception(backageBuilder.getErrors().toString());
        }

        return backageBuilder;
    }

    /**
     * buildReadersFromDrlDB
     * @param context 脚本内容
     * @return List<Reader>
     */

    private List<Reader> buildReadersFromDrlDB(String context) {
        List<Reader> readers = new ArrayList<Reader>();
        // 获取脚本
        List<DroolsRuleDomain> drlRuleDomains = getRuleFromDB(context);
        if (null == drlRuleDomains) {
            return readers;
        }
        for (DroolsRuleDomain droolsRuleDomain : drlRuleDomains) {
            String ruleContext = droolsRuleDomain.getRuleContext();

            Reader br = new StringReader(ruleContext);
            readers.add(br);
        }
        return readers;
    }

    /**
     * getRuleFromDB 读取
     * @param ruleContext 规则内容
     * @return List<DroolsRuleDomain>
     */
    private List<DroolsRuleDomain> getRuleFromDB(String ruleContext) {
        List<DroolsRuleDomain> droolsRuleDomains = new ArrayList<DroolsRuleDomain>();

        DroolsRuleDomain d1 = new DroolsRuleDomain();
        d1.setId(1);
        d1.setRuleContext(ruleContext);
        d1.setRuleName("meeting");
        d1.setVersion(1);

        droolsRuleDomains.add(d1);

        return droolsRuleDomains;
    }
}

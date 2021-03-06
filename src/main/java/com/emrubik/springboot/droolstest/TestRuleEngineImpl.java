/*******************************************************************************
 * @(#)PointRuleEngineImpl.java 2017年6月22日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot.droolstest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.spi.Activation;

import com.emrubik.springboot.drools.RuleBaseFacatory;
import com.emrubik.springboot.droolsx.RuleDomain;


/**
 * 规则接口实现类
 */
public class TestRuleEngineImpl implements TestRuleEngine {
    private RuleBase ruleBase;

    /*
     * (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#initEngine()
     */
    public void initEngine() {
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        ruleBase = RuleBaseFacatory.getRuleBase();
        try {
            PackageBuilder backageBuilder = getPackageBuilderFromDrlFile();
            ruleBase.addPackages(backageBuilder.getPackages());
        } catch (DroolsParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#refreshEnginRule()
     */
    public void refreshEnginRule() {
        ruleBase = RuleBaseFacatory.getRuleBase();
        org.drools.rule.Package[] packages = ruleBase.getPackages();
        for (org.drools.rule.Package pg : packages) {
            ruleBase.removePackage(pg.getName());
        }

        initEngine();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.drools.demo.point.PointRuleEngine#executeRuleEngine(com.drools.demo.point.PointDomain)
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
     * 从Drl规则文件中读取规则
     * @return
     * @throws Exception
     */
    private PackageBuilder getPackageBuilderFromDrlFile() throws Exception {
        // 获取测试脚本文件
        List<String> drlFilePath = getTestDrlFile();
        // 装载测试脚本文件
        List<Reader> readers = readRuleFromDrlFile(drlFilePath);

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
     * @param drlFilePath 脚本文件路径
     * @return
     * @throws FileNotFoundException
     */
    private List<Reader> readRuleFromDrlFile(List<String> drlFilePath)
            throws FileNotFoundException {
        if (null == drlFilePath || 0 == drlFilePath.size()) {
            return null;
        }

        List<Reader> readers = new ArrayList<Reader>();
        
        for (String ruleFilePath : drlFilePath) {
            readers.add(new FileReader(new File(ruleFilePath)));
        }

        return readers;
    }

    /**
     * 获取测试规则文件
     * @return
     */
    private List<String> getTestDrlFile() {
        List<String> drlFilePath = new ArrayList<String>();
        drlFilePath.add("F:/rules/meeting.drl");
//        drlFilePath.add("F:/rules/subpoint.drl");

        return drlFilePath;
    }
}

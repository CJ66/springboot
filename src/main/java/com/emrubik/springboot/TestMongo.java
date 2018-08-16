/*******************************************************************************
 * @(#)TestMongo.java 2017年6月21日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.emrubik.oak.mongo.MongoRepository;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月21日 上午10:00:43
 */
@Configuration
@EnableScheduling
public class TestMongo {
    
    /**
     * mongoRepository
     */
    @Autowired
    protected MongoRepository mongoRepository;
    
    @Autowired
    private TestService testService;
    
    @Scheduled(cron = "0/10 * * * * ?")
    public void test(){
        testService.test("aaaaaaaaaaaaaaa");
    }
    @Scheduled(cron = "0/10 * * * * ?")
    public void insert() throws Exception{
        testService.test("bbbbbbbbbbbbbbbbbb");
//        mongoRepository.insert("{'abc':'asc','abd':'abc'}", "abc");
    }
}

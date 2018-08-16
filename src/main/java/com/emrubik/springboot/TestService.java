/*******************************************************************************
 * @(#)TestService.java 2017年6月21日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springboot;

import org.springframework.stereotype.Service;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年6月21日 下午4:33:56
 */
@Service
public class TestService {
    
    public void test(String abc){
        System.out.println(abc);
    }
}

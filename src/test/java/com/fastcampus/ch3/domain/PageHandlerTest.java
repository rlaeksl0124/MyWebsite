package com.fastcampus.ch3.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void PageTest(){
        PageHandler ph = new PageHandler(250, 1, 20);
        System.out.println(ph);
        ph.print();
        assertTrue(ph.getNaviStart()==1);
        assertTrue(ph.getNaviEnd()==10);
    }
    @Test
    public void PageTest2(){
        PageHandler ph = new PageHandler(300, 15, 20);
        System.out.println(ph);
        ph.print();
        assertTrue(ph.getNaviStart()==11);
        assertTrue(ph.getNaviEnd()==15);
    }

    @Test
    public void PageTest3(){
        PageHandler ph = new PageHandler(3000, 132, 20);
        System.out.println(ph);
        ph.print();
        assertTrue(ph.getNaviStart()==131);
        assertTrue(ph.getNaviEnd()==140);
    }

    @Test
    public void PageTest4(){
        PageHandler ph = new PageHandler(3000, 10, 20);
        System.out.println(ph);
        ph.print();
        assertTrue(ph.getNaviStart()==1);
        assertTrue(ph.getNaviEnd()==10);
    }

}
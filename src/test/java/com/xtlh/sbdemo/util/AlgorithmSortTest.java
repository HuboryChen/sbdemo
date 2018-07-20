package com.xtlh.sbdemo.util;

import com.xtlh.sbdemo.SbdemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Array;


/**
 * @Author:
 * @Description:    测试排序算法
 * @Date: Created in ${Time} 2018/7/20
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SbdemoApplication.class)
@WebAppConfiguration
public class AlgorithmSortTest
{
    private int[] arr = {3,9,7,10,4,16,2,22,24,6,15,4};

    /**
     * 测试冒泡排序
     */
    @Test
    public void TestBubbleSort()
    {

        AlgorithmSort.bubbleSort(arr);
        for (int num:arr)
        {
            System.out.println(num);
        }

    }

    /**
     * 测试选择排序
     */
    @Test
    public void TestSelectionSort()
    {
        AlgorithmSort.selectionSort(arr);
        for (int num:arr)
        {
            System.out.println(num);
        }
    }

    /**
     * 测试插入排序
     */
    @Test
    public void TestInsertionSort()
    {
        AlgorithmSort.insertionSort(arr);
        for (int num:arr)
        {
            System.out.println(num);
        }
    }


    /**
     * 测试希尔排序
     */
    @Test
    public void TestShellSort()
    {
        AlgorithmSort.shellSort(arr);
        for (int num:arr)
        {
            System.out.println(num);
        }
    }

}

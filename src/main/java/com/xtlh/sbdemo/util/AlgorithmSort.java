package com.xtlh.sbdemo.util;

/**
 * 排序算法
 */
public class  AlgorithmSort
{

    /**
     * 算法名称：冒泡排序
     * 算法描述：
     *      比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *      对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *      针对所有的元素重复以上的步骤，除了最后一个；
     *      重复步骤1~3，直到排序完成。
     */
    public static int[] bubbleSort(int[] arr)
    {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++)
        {
            for (int j = 0; j <length - 1 -i; j++)
            {
                if(arr[i] > arr[j+1])
                {
                    int temp = arr[i];
                    arr[i] = arr[j+1];
                    arr[j+1] = arr[i];
                }
            }
        }
        return arr;

    }

}

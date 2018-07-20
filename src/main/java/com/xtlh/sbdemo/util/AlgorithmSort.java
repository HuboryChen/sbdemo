package com.xtlh.sbdemo.util;

/**
 * 排序算法
 */
public class  AlgorithmSort
{

    /**
     * 算法名称：冒泡排序(Bubble Sort)
     * 工作原理：重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换。
     * 算法描述：
     *      比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *      对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *      针对所有的元素重复以上的步骤，除了最后一个；
     *      重复步骤1~3，直到排序完成。
     * 算法分析：算法表现稳定，平均时间复杂度O(n2)，空间复杂度为O(1),适合数据规模较小的场合使用。
     */
    public static int[] bubbleSort(int[] arr)
    {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++)
        {
            for (int j = 0; j <length - 1 -i; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;

    }


    /**
     *  算法名称：选择排序(Selection Sort)
     *  工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *  算法描述：
     *       初始状态：无序区为R[1..n]，有序区为空；
     *       第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     *       n-1趟结束，数组有序化了。
     *  算法分析：表现最稳定的排序算法之一，时间复杂度O(n2)，所以使用此算法的数据规模越小越好，空间复杂度为O(1),不占用额外的内存空间。
     */
    public static int[] selectionSort(int[] arr)
    {
        int len = arr.length;
        int minIndex,temp;

        for (int i = 0; i < len-1; i++)
        {
            minIndex = i;
            for (int j = i+1; j <len; j++)
            {
                if (arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }


    /**
     * 算法名称：插入排序（Selection Sort）
     * 工作原理：通过构建有序序列，对于未排序数据，在已排序序列中，找到相应位置并插入
     * 算法描述：
     *      从第一个元素开始，该元素可以认为已经被排序；
     *      取出下一个元素，在已经排序的元素序列中从后向前扫描；
     *      如果该元素（已排序）大于新元素，将该元素移到下一位置；
     *      重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     *      将新元素插入到该位置后；
     *      重复步骤2~5。
     * 算法分析：插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
     *          因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     */
    public static int[] insertionSort(int[] arr)
    {
        int len = arr.length;
        int preIndex,current;
        for(int i = 1; i < len; i++)
        {
            preIndex = i -1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] >current)
            {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }


    /**
     * 算法名称：希尔排序（Shell Sort）
     * 算法描述：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序
     *      选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     *      按增量序列个数k，对序列进行k 趟排序；
     *      每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     *      仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *  算法分析：Shell排序是不稳定的，它的空间开销也是O(1),时间开销估计在O(N3/2)~O(N7/6)之间.希尔排序的的核心在于间隔序列的设定。既可以提前设定好间隔序列，也可以动态的定义间隔序列。
     */
    public static int[] shellSort(int[] arr)
    {
        int len = arr.length;
        int temp;
        int gap = 1;

        //动态定义间隔序列
        while(gap < len / 3)
        {
            gap = gap * 3 + 1;
        }

        while(gap > 0)
        {
            for (int i = gap; i < len; i += gap)
            {
                if(arr[i] < arr[i - gap])
                {
                    temp = arr[i];
                    int j = i - gap;
                    while(j >= 0 && arr[j] > temp)
                    {
                        arr[j + gap] = arr[j];
                        j -= gap;
                    }
                    arr[j + gap] = temp;
                }
            }
            gap = (gap - 1) / 3;
        }
        return arr;
    }


    /**
     * 算法名称：归并排序（Merge Sort）
     * 工作原理：将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * 算法描述：归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     *          把长度为n的输入序列分成两个长度为n/2的子序列；
     *          对这两个子序列分别采用归并排序；
     *          将两个排序好的子序列合并成一个最终的排序序列。
     * 算法分析：归并排序是一种稳定的排序方法。和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(nlogn）的时间复杂度。代价是需要额外的内存空间。
     */
    public static int[] mergeSort(int[] arr)
    {

        return arr;
    }


}

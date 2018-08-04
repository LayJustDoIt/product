package org.lay.product.test;


import java.util.ArrayList;
import java.util.Comparator;

/**
 * Create by Lay
 * 2018-05-01 21:46
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(1);
        list.add(5);
        list.sort(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
        System.out.println("匿名内部类排序输出:"+list);

        list.sort((o1, o2) -> o1.compareTo(o2));
        System.out.println("lambda排序输出:"+list);

        list.sort(Integer::compareTo);
        System.out.println("lambda2排序输出：" + list);
    }

}

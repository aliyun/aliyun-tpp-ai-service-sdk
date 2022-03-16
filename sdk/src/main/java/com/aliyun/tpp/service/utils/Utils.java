/**
 * author: oe date:   2021/8/31 comment:合并多个scoreItem
 */
package com.aliyun.tpp.service.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static <T> List<T> union(List<T> list1, List<T> list2) {

        Set<T> set = new HashSet<T>();
        if (list1 != null) {
            set.addAll(list1);
        }
        if (list2 != null) {
            set.addAll(list2);
        }
        return new ArrayList<>(set);
    }
}

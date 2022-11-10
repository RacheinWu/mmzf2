package com.rachein.mmzf2.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/11/7
 * @Description
 */
public class CollectionUtil {

    @SafeVarargs
    public static <T> List<T> combine(List<T> ... lists) {
        return Stream.of(lists)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Set<T> combine(Set<T>... lists) {
        return Stream.of(lists)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

//    @SafeVarargs
    public static <T> Set<T> combine(List<Set<T>> lists) {
//        return Stream.of(lists)
//                .flatMap(Set::stream)
//                .collect(Collectors.toSet());
        return null;
    }
}

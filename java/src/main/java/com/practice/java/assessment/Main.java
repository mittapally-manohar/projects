package com.practice.java.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /**
         *   1
         *  1 1
         * 1 2 1
         *1 3 3 1
         *
         * i = 0, j = 2
         * i = 1, j = 1, 3
         * i = 2, j = 0, 2,
         * n = 4
         */
        /**
         * 1
         * 1 1
         * 1 2 1
         * 1 3 3 1
         */
//        List<List<Integer>> finalResult = new ArrayList<>();
//        List<Integer> result = new ArrayList<>();
//        result.add(1);
//        finalResult.add(result);
//        for(int i = 0; i < 4; i++){
//           List<Integer> ans = finalResult.get(i);
//
//        }

        Stream.iterate(new int[]{0,1}, f-> new int[]{f[1],f[0]+f[1]}).limit(10).map(f->f[0]).forEach(System.out::println);
//        Stream.iterate(new int[]{0,1}, f-> f[0]+f[1]).limit(10).map(f->f[0]).forEach(System.out::println);

    }
}


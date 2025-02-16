package com.practice.java.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /**
         * For a given input 2D array of strings, find the maximum average grade, round off to the nearest floor integer.
         *
         * Input :                 {  {"Rohit", "85"},
         *                                 {"Rahul", "80"},
         *                                 {"Amit","85"},
         *                                 {"Rohit", "90"}   }
         *
         * Output : 87
         * Logic : Rohit's average grade is (85+90)/2 = 87.5  which when rounded off to floor is  87
         */
        String[][] input = new String[][]{

                {"Rohit", "85"},
                {"Rahul", "80"},
                {"Amit", "85"},
                {"Rohit", "90"}

        };

        Map<String, List<Integer>> map = new HashMap<>();
        for(String[] student: input) {
            String name = student[0];
            Integer marks = Integer.parseInt(student[1]);
            List<Integer> newList = new ArrayList<>();
            if(map.containsKey(name)){
                newList = map.get(name);
                newList.add(marks);
            }else {
                newList.add(marks);
            }
            map.put(name,newList);
        }

        System.out.println(map);
        String name="";
        double max = 0.0;
        for(Map.Entry<String, List<Integer>> m: map.entrySet()) {
            double avg = m.getValue().stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
            if(avg > max){
                name = m.getKey();
                max = avg;
            }
        }

        System.out.println("Name: "+name+" "+ max);

    }
}

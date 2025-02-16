package com.practice.java.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostAsked {
    public static void main(String[] args) {
        // Input declarations and initializations
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        List<Integer> integerList = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        String inputString = "swiss";
        List<Person> peopleList = Arrays.asList(new Person("Alice", 30), new Person("Bob", 25), new Person("Charlie", 30));

        // Sample outputs
        System.out.println(convertToUppercase(stringList)); // [APPLE, BANANA, CHERRY]
        System.out.println(findSecondHighest(integerList)); // 6
        System.out.println(removeDuplicates(integerList)); // [3, 1, 4, 5, 9, 2, 6]
        System.out.println(findFirstNonRepeatingCharacter(inputString)); // w
        System.out.println(findFirstRepeatingCharacter(inputString)); // s
        System.out.println(sortListAscending(integerList)); // [1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9]
        System.out.println(sortListDescending(integerList)); // [9, 6, 5, 5, 5, 4, 3, 3, 2, 1, 1]
        System.out.println(findMaxValue(integerList)); // 9
        System.out.println(findMinValue(integerList)); // 1
        System.out.println(findSum(integerList)); // 44
        System.out.println(findAverage(integerList)); // 4.0
        System.out.println(groupByAge(peopleList)); // {25=[Person{name='Bob', age=25}], 30=[Person{name='Alice', age=30}, Person{name='Charlie', age=30}]}
        System.out.println(findEvenNumbers(integerList)); // [4, 2, 6]
        System.out.println(findOddNumbers(integerList)); // [3, 1, 1, 5, 9, 5, 3, 5]
    }

    public static List<String> convertToUppercase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static Integer findSecondHighest(List<Integer> numbers) {
        return numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    public static List<Integer> removeDuplicates(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static Character findFirstNonRepeatingCharacter(String input) {
        // Implement logic here
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(map -> map.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static Character findFirstRepeatingCharacter(String input) {
//        return input.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(map -> map.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);
        Set<Character> seen = new HashSet<>();
        return input.chars()
                .mapToObj(c -> (char)c)
                .filter(c -> !seen.add(c))
                .findFirst()
                .orElse(null);
    }

    public static List<Integer> sortListAscending(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Integer> sortListDescending(List<Integer> numbers) {
        return numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static Integer findMaxValue(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compareTo)
                .get();
    }

    public static Integer findMinValue(List<Integer> numbers) {
        return numbers.stream()
                .min(Integer::compareTo)
                .get();
    }

    public static Integer findSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    public static Double findAverage(List<Integer> numbers) {
//        return numbers.stream()
//                .mapToInt(s -> s)
//                .average().getAsDouble();

        return numbers.stream()
                .collect(Collectors.averagingDouble(Integer::doubleValue));

    }

    public static Map<Integer, List<Person>> groupByAge(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getAge));

    }

    public static List<Integer> findEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
    }

    public static List<Integer> findOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> i % 2 != 0)
                .collect(Collectors.toList());
    }
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}


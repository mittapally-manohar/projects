package com.practice.java.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModeratelyAsked {
    public static void main(String[] args) {
        // Implement a custom comparator using Streams
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        List<String> sortedList = customComparator(stringList);
        System.out.println("Sorted List: " + sortedList); // Expected Output: [apple, banana, cherry]

        // Find duplicate elements in a list using Streams
        List<Integer> numberList = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> duplicates = findDuplicates(numberList);
        System.out.println("Duplicates: " + duplicates); // Expected Output: [2, 3]

        // Check if a list contains only even numbers
        boolean allEven = containsOnlyEvenNumbers(numberList);
        System.out.println("All Even: " + allEven); // Expected Output: false

        // Find the longest string in a list
        String longestString = findLongestString(stringList);
        System.out.println("Longest String: " + longestString); // Expected Output: banana

        // Find the most frequently occurring element in a list
        Integer mostFrequent = findMostFrequentElement(numberList);
        System.out.println("Most Frequent Element: " + mostFrequent); // Expected Output: 3

        // Convert a list of employees to a map with employee ID as the key
        List<Employee> employees = Arrays.asList(new Employee(1, "John", 50000), new Employee(2, "Jane", 60000));
        Map<Integer, Employee> employeeMap = convertListToMap(employees);
        System.out.println("Employee Map: " + employeeMap); // Expected Output: {1=Employee{id=1, name='John', salary=50000}, 2=Employee{id=2, name='Jane', salary=60000}}

        // Sort a list of employees by salary in descending order
        List<Employee> sortedEmployees = sortEmployeesBySalary(employees);
        System.out.println("Sorted Employees: " + sortedEmployees); // Expected Output: [Employee{id=2, name='Jane', salary=60000}, Employee{id=1, name='John', salary=50000}}

        // Merge two maps using Streams
        Map<Integer, String> map1 = Map.of(1, "A", 2, "B");
        Map<Integer, String> map2 = Map.of(3, "C", 4, "D");
        Map<Integer, String> mergedMap = mergeMaps(map1, map2);
        System.out.println("Merged Map: " + mergedMap); // Expected Output: {1=A, 2=B, 3=C, 4=D}

        // Find the common elements between two lists using Streams
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 4);
        List<Integer> commonElements = findCommonElements(list1, list2);
        System.out.println("Common Elements: " + commonElements); // Expected Output: [2, 3]

        // Find the difference between two lists using Streams
        List<Integer> difference = findDifference(list1, list2);
        System.out.println("Difference: " + difference); // Expected Output: [1]
    }

    public static List<String> customComparator(List<String> list) {
       return list.stream()
               .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
               .collect(Collectors.toList());
    }

    public static List<Integer> findDuplicates(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(map -> map.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static boolean containsOnlyEvenNumbers(List<Integer> list) {
        return list.stream()
                .filter(e -> e%2 == 0)
                .count()==list.size();
    }

    public static String findLongestString(List<String> list) {
        return list.stream()
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }

    public static Integer findMostFrequentElement(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static Map<Integer, Employee> convertListToMap(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    public static List<Employee> sortEmployeesBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }

    public static Map<Integer, String> mergeMaps(Map<Integer, String> map1, Map<Integer, String> map2) {
       return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> findCommonElements(List<Integer> list1, List<Integer> list2) {
       return list1.stream()
               .filter(e -> list2.contains(e))
               .collect(Collectors.toList());
    }

    public static List<Integer> findDifference(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .filter(e -> !list2.contains(e))
                .collect(Collectors.toList());
    }
    static class Employee {
        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
        }
    }
}


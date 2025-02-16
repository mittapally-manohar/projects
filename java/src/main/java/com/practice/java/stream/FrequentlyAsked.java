package com.practice.java.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequentlyAsked {
    public static void main(String[] args) {
        // Convert a List of Strings to a List of Integers
        List<String> stringList = Arrays.asList("1", "2", "3", "4");
        List<Integer> integerList = convertStringListToIntegerList(stringList);
        System.out.println("Converted List: " + integerList); // Expected Output: [1, 2, 3, 4]

        // Concatenate multiple lists using Streams
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> concatenatedList = concatenateLists(list1, list2);
        System.out.println("Concatenated List: " + concatenatedList); // Expected Output: [1, 2, 3, 4, 5, 6]

        // Flatten a list of lists using Streams
        List<List<Integer>> listOfLists = Arrays.asList(list1, list2);
        List<Integer> flattenedList = flattenListOfLists(listOfLists);
        System.out.println("Flattened List: " + flattenedList); // Expected Output: [1, 2, 3, 4, 5, 6]

        // Check if a given list contains a specific element using Streams
        boolean containsElement = containsElement(list1, 2);
        System.out.println("Contains Element: " + containsElement); // Expected Output: true

        // Filter employees with a salary greater than a given threshold
        List<Employee> employees = Arrays.asList(new Employee("John", 50000), new Employee("Jane", 60000));
        List<Employee> filteredEmployees = filterEmployeesBySalary(employees, 55000);
        System.out.println("Filtered Employees: " + filteredEmployees); // Expected Output: [Employee{name='Jane', salary=60000}]

        // Find the employee with the highest and lowest salary
        Employee highestSalaryEmployee = findEmployeeWithHighestSalary(employees);
        Employee lowestSalaryEmployee = findEmployeeWithLowestSalary(employees);
        System.out.println("Highest Salary Employee: " + highestSalaryEmployee); // Expected Output: Employee{name='Jane', salary=60000}
        System.out.println("Lowest Salary Employee: " + lowestSalaryEmployee); // Expected Output: Employee{name='John', salary=50000}

        // Count occurrences of each element in a list using Streams
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Map<Integer, Long> occurrences = countOccurrences(numbers);
        System.out.println("Occurrences: " + occurrences); // Expected Output: {1=1, 2=2, 3=3}

        // Check if all elements in a list are unique using Streams
        boolean allUnique = areAllElementsUnique(numbers);
        System.out.println("All Elements Unique: " + allUnique); // Expected Output: false

        // Partition a list of numbers into even and odd using partitioningBy()
        Map<Boolean, List<Integer>> partitionedNumbers = partitionEvenAndOdd(numbers);
        System.out.println("Partitioned Numbers: " + partitionedNumbers); // Expected Output: {false=[1, 3, 3, 3], true=[2, 2]}

        // Convert a list of strings to a comma-separated string using Collectors.joining()
        String commaSeparatedString = convertListToCommaSeparatedString(stringList);
        System.out.println("Comma Separated String: " + commaSeparatedString); // Expected Output: 1,2,3,4
    }

    public static List<Integer> convertStringListToIntegerList(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> concatenateLists(List<Integer> list1, List<Integer> list2) {
        return Stream.of(list1, list2).toList().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<Integer> flattenListOfLists(List<List<Integer>> listOfLists) {
       return listOfLists.stream()
               .flatMap(Collection::stream)
               .collect(Collectors.toList());
    }

    public static boolean containsElement(List<Integer> list, Integer element) {
        return list.stream()
                .anyMatch(list::contains);
    }

    public static List<Employee> filterEmployeesBySalary(List<Employee> employees, double threshold) {
        return employees.stream()
                .filter(employee -> employee.getSalary() > threshold)
                .collect(Collectors.toList());
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public static Employee findEmployeeWithLowestSalary(List<Employee> employees) {
        return employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public static Map<Integer, Long> countOccurrences(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    public static boolean areAllElementsUnique(List<Integer> list) {
        return list.stream()
                .distinct()
                .count()==list.size();
    }

    public static Map<Boolean, List<Integer>> partitionEvenAndOdd(List<Integer> list) {
        return list.stream()
                .collect(Collectors.partitioningBy(e -> e % 2 ==0));
    }

    public static String convertListToCommaSeparatedString(List<String> list) {
        return list.stream()
                .collect(Collectors.joining(","));
    }
    static class Employee {
        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{name='" + name + "', salary=" + salary + "}";
        }
    }
}


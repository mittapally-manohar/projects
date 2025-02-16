package com.practice.java.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LessFrequentlyAsked {
    public static void main(String[] args) {
        // Convert a list of objects to a map using Streams
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 50000),
                new Employee(2, "Jane", 60000)
        );
        Map<Integer, Employee> employeeMap = convertListToMap(employees);
        System.out.println("Employee Map: " + employeeMap); // Expected Output: {1=Employee{id=1, name='John', salary=50000.0}, 2=Employee{id=2, name='Jane', salary=60000.0}}

        // Calculate the total salary of all employees
        double totalSalary = calculateTotalSalary(employees);
        System.out.println("Total Salary: " + totalSalary); // Expected Output: 110000.0

        // Find the department with the highest number of employees
        List<Department> departments = Arrays.asList(
                new Department("HR", Arrays.asList(new Employee(1, "John", 50000))),
                new Department("IT", Arrays.asList(new Employee(2, "Jane", 60000), new Employee(3, "Doe", 70000)))
        );
        Department highestEmployeeDepartment = findDepartmentWithHighestEmployees(departments);
        System.out.println("Department with Highest Employees: " + highestEmployeeDepartment); // Expected Output: Department{name='IT', employees=[Employee{id=2, name='Jane', salary=60000.0}, Employee{id=3, name='Doe', salary=70000.0}]}

        // Find the youngest and oldest employees in a list
        Employee youngestEmployee = findYoungestEmployee(employees);
        Employee oldestEmployee = findOldestEmployee(employees);
        System.out.println("Youngest Employee: " + youngestEmployee); // Expected Output: Employee{id=1, name='John', salary=50000.0}
        System.out.println("Oldest Employee: " + oldestEmployee); // Expected Output: Employee{id=2, name='Jane', salary=60000.0}

        // Check if a list contains only unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean allUnique = areAllElementsUnique(numbers);
        System.out.println("All Elements Unique: " + allUnique); // Expected Output: true

        // Find the longest word in a sentence using Streams
        String sentence = "The quick brown fox jumpss over the lazy dog";
        String longestWord = findLongestWord(sentence);
        System.out.println("Longest Word: " + longestWord); // Expected Output: "jumps"

        // Convert a list to a Set using Streams
        List<String> stringList = Arrays.asList("apple", "banana", "apple", "orange");
        Set<String> stringSet = convertListToSet(stringList);
        System.out.println("Converted Set: " + stringSet); // Expected Output: [apple, banana, orange]

        // Extract only vowels from a string using Streams
        String inputString = "hello world";
        String vowels = extractVowels(inputString);
        System.out.println("Vowels: " + vowels); // Expected Output: "eoo"

        // Reverse a list using Streams
        List<Integer> reversedList = reverseList(numbers);
        System.out.println("Reversed List: " + reversedList); // Expected Output: [5, 4, 3, 2, 1]

        // Implement a Stream pipeline to filter, transform, and collect data
        List<String> names = Arrays.asList("John", "Jane", "Doe");
        List<String> transformedNames = filterTransformCollect(names);
        System.out.println("Transformed Names: " + transformedNames); // Expected Output: [JOHN, JANE]
    }

    public static Map<Integer, Employee> convertListToMap(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    public static double calculateTotalSalary(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }

    public static Department findDepartmentWithHighestEmployees(List<Department> departments) {
        return departments.stream()
                .max(Comparator.comparingInt(department -> department.getEmployees().size()))
                .orElse(null);
    }

    public static Employee findYoungestEmployee(List<Employee> employees) {
       return employees.stream()
               .min(Comparator.comparingInt(Employee::getId))
               .orElse(null);
    }

    public static Employee findOldestEmployee(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparingInt(Employee::getId))
                .orElse(null);
    }

    public static boolean areAllElementsUnique(List<Integer> list) {
        return list.stream()
                .distinct()
                .count() == list.size();
    }

    public static String findLongestWord(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }

    public static Set<String> convertListToSet(List<String> list) {
        return new HashSet<>(list);
    }

    public static String extractVowels(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> "aeiouAEIOU".indexOf(c) >= 0)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static List<Integer> reverseList(List<Integer> list) {
        return list.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(),collected -> {
                    Collections.reverse(collected);
                    return collected;
                }));
    }

    public static List<String> filterTransformCollect(List<String> list) {
            return list.stream()
                    .filter(name -> name.length() > 3) // Filter names with length greater than 3
                    .map(String::toUpperCase) // Transform names to uppercase
                    .collect(Collectors.toList()); // Collect the result into a list
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

    static class Department {
        private String name;
        private List<Employee> employees;

        public Department(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
        }

        public String getName() {
            return name;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        @Override
        public String toString() {
            return "Department{name='" + name + "', employees=" + employees + "}";
        }
    }
}
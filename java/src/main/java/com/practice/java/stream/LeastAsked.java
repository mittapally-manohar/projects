package com.practice.java.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LeastAsked {

    public static void main(String[] args) {
        // Sample inputs and expected outputs

        // 1. Calculate the factorial of a number using Streams
        int number = 5;
        System.out.println("Factorial of " + number + ": " + factorial(number)); // Expected output: 120

        // 2. Find if a given string is a palindrome using Streams
        String str = "radar";
        System.out.println("Is \"" + str + "\" a palindrome? " + isPalindrome(str)); // Expected output: true

        // 3. Generate Fibonacci series using Streams
        int limit = 10;
        System.out.println("Fibonacci series up to " + limit + ": " + generateFibonacci(limit)); // Expected output: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]

        // 4. Implement a custom collector in Streams
        List<String> names = List.of("Alice", "Bob", "Charlie");
        System.out.println("Custom collected result: " + customCollector(names)); // Expected output: Custom collected result

        // 5. Use reduce() to concatenate a list of strings
        List<String> strings = List.of("Hello", "World", "Java");
        System.out.println("Concatenated string: " + concatenateStrings(strings)); // Expected output: HelloWorldJava

        // 6. Parallel processing using parallelStream()
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Processed in parallel: " + parallelProcessing(numbers)); // Expected output: Processed in parallel

        // 7. Find the Kth largest element in a list using Streams
        List<Integer> elements = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int k = 3;
        System.out.println("The " + k + "rd largest element: " + findKthLargest(elements, k)); // Expected output: 5

        // 8. Convert a Map to a List using Streams
        Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);
        System.out.println("Converted list: " + mapToList(map)); // Expected output: [1, 2, 3]

        // 9. Extract all unique words from a given paragraph using Streams
        String paragraph = "This is a test. This test is only a test.";
        System.out.println("Unique words: " + extractUniqueWords(paragraph)); // Expected output: [This, is, a, test, only]

        // 10. Find the common elements between multiple lists using Streams
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> list2 = List.of(3, 4, 5, 6);
        List<Integer> list3 = List.of(4, 5, 6, 7);
        System.out.println("Common elements: " + findCommonElements(list1, list2, list3)); // Expected output: [4]
    }

    public static int factorial(int number) {
        // Implement logic here
        return 0;
    }

    public static boolean isPalindrome(String str) {
        // Implement logic here
        return false;
    }

    public static List<Integer> generateFibonacci(int limit) {
        // Implement logic here
        return List.of();
    }

    public static String customCollector(List<String> names) {
        // Implement logic here
        return "";
    }

    public static String concatenateStrings(List<String> strings) {
        // Implement logic here
        return "";
    }

    public static List<Integer> parallelProcessing(List<Integer> numbers) {
        // Implement logic here
        return List.of();
    }

    public static int findKthLargest(List<Integer> elements, int k) {
        // Implement logic here
        return 0;
    }

    public static List<Integer> mapToList(Map<String, Integer> map) {
        // Implement logic here
        return List.of();
    }

    public static List<String> extractUniqueWords(String paragraph) {
        // Implement logic here
        return List.of();
    }

    public static List<Integer> findCommonElements(List<Integer>... lists) {
        // Implement logic here
        return List.of();
    }
}
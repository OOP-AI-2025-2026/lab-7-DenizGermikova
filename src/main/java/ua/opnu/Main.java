package ua.opnu;

import java.util.Arrays;
import java.util.Random;
import java.util.function.*;

public class Main {

    @SuppressWarnings("unused") // args не используется, но IDE не ругается
    public static void main(String[] args) {

        // === Завдання 1: Простi числа ===
        System.out.println("=== Завдання 1: Простi числа ===");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 17, 18, 19, 20};

        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        int[] primes = filter(numbers, isPrime);
        System.out.println("Прості числа: " + Arrays.toString(primes));

        // === Завдання 2: Фільтрація студентів ===
        System.out.println("\n=== Завдання 2: Фільтрація студентів ===");
        Student[] students = {
                new Student("Іван Петренко", "AI-241", new int[]{75, 80, 90}),
                new Student("Олена Коваль", "AI-241", new int[]{50, 70, 65}),
                new Student("Денис Іванов", "AI-242", new int[]{95, 88, 93}),
                new Student("Марія Сидоренко", "AI-242", new int[]{59, 60, 70})
        };

        Predicate<Student> noDebts = s -> !s.hasDebt();
        Student[] goodStudents = filterStudents(students, noDebts);

        System.out.println("Студенти без заборгованостей:");
        for (Student s : goodStudents) System.out.println(s);

        // === Завдання 3: Фільтрація за двома умовами ===
        System.out.println("\n=== Завдання 3: Фільтрація за двома умовами ===");
        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> greaterThanFive = n -> n > 5;

        int[] filtered = filterTwoPredicates(numbers2, isEven, greaterThanFive);
        System.out.println("Числа, які парні і більші за 5: " + Arrays.toString(filtered));

        // === Завдання 4: Використання Consumer ===
        System.out.println("\n=== Завдання 4: Використання Consumer ===");
        Consumer<Student> printFullName = s -> System.out.println(s.getName());
        forEach(students, printFullName);

        // === Завдання 5: Predicate + Consumer ===
        System.out.println("\n=== Завдання 5: Predicate + Consumer ===");
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> even = n -> n % 2 == 0;
        Consumer<Integer> print = n -> System.out.println("Парне число: " + n);
        doIf(nums, even, print);

        Predicate<Integer> greaterThanFive2 = n -> n > 5;
        Consumer<Integer> square = n -> System.out.println(n + "^2 = " + (n * n));

        System.out.println("\nЧисла > 5:");
        doIf(nums, greaterThanFive2, square);

        // === Завдання 6: Function 2^n ===
        System.out.println("\n=== Завдання 6: Function 2^n ===");
        int[] nums2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Function<Integer, Integer> pow2 = n -> (int) Math.pow(2, n);
        int[] result = processArray(nums2, pow2);
        System.out.println("Результат 2^n: " + Arrays.toString(result));

        // === Завдання 7: stringify() ===
        System.out.println("\n=== Завдання 7: stringify() ===");
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Function<Integer, String> numberToWord = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п’ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев’ять";
            default -> "невідомо";
        };

        String[] words = stringify(digits, numberToWord);
        System.out.println("Числа словами: " + Arrays.toString(words));

        // === Додаткове завдання 8: Supplier ===
        System.out.println("\n=== Завдання 8 (додаткове): Supplier випадкових чисел ===");
        Random random = new Random();

        Supplier<Integer> randomNumber = () -> random.nextInt(100) + 1;
        System.out.print("10 випадкових чисел: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(randomNumber.get() + " ");
        }
        System.out.println();
    }

    // === Допоміжні методи ===

    private static int[] filter(int[] input, Predicate<Integer> predicate) {
        int[] result = new int[input.length];
        int count = 0;
        for (int val : input) if (predicate.test(val)) result[count++] = val;
        return Arrays.copyOf(result, count);
    }

    private static Student[] filterStudents(Student[] students, Predicate<Student> predicate) {
        Student[] result = new Student[students.length];
        int count = 0;
        for (Student s : students) if (predicate.test(s)) result[count++] = s;
        return Arrays.copyOf(result, count);
    }

    private static int[] filterTwoPredicates(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        int[] result = new int[input.length];
        int count = 0;
        for (int val : input) if (p1.test(val) && p2.test(val)) result[count++] = val;
        return Arrays.copyOf(result, count);
    }

    private static void forEach(Student[] students, Consumer<Student> action) {
        for (Student s : students) action.accept(s);
    }

    private static void doIf(int[] numbers, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int n : numbers) if (condition.test(n)) action.accept(n);
    }

    private static int[] processArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) result[i] = function.apply(input[i]);
        return result;
    }

    private static String[] stringify(int[] input, Function<Integer, String> converter) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) result[i] = converter.apply(input[i]);
        return result;
    }
}

package ru.ushakova.number6;

import ru.ushakova.main.Main;

import java.util.*;
import java.util.Arrays;

public class QueueDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 6: ПОСТРОЕНИЕ ОЧЕРЕДИ ===");

        Queues queueProcessor = new Queues();
        boolean continueDemo = true;

        while (continueDemo) {
            printQueueMenu();
            int choice = Main.getValidInt(scanner, "", 0, 4);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    demonstrateWithNumbers();
                    break;
                case 2:
                    demonstrateWithStrings();
                    break;
                case 3:
                    demonstrateWithCharacters();
                    break;
                case 4:
                    customInput(scanner, queueProcessor);
                    break;
                case 0:
                    continueDemo = false;
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }

            if (continueDemo && choice != 0) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
    }

    private static void printQueueMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПОСТРОЕНИЕ ОЧЕРЕДИ");
        System.out.println("=".repeat(50));
        System.out.println("1. Демонстрация с числами");
        System.out.println("2. Демонстрация со строками");
        System.out.println("3. Демонстрация с символами");
        System.out.println("4. Пользовательский ввод");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void demonstrateWithNumbers() {
        System.out.println("\n--- Демонстрация с числами ---");

        Queues queueProcessor = new Queues();
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        System.out.println("Исходный список: " + numbers);
        System.out.print("Результирующая очередь: ");
        queueProcessor.buildQueue(numbers);
    }

    private static void demonstrateWithStrings() {
        System.out.println("\n--- Демонстрация со строками ---");

        Queues queueProcessor = new Queues();
        List<String> strings = Arrays.asList("A", "B", "C", "D");

        System.out.println("Исходный список: " + strings);
        System.out.print("Результирующая очередь: ");
        queueProcessor.buildQueue(strings);
    }

    private static void demonstrateWithCharacters() {
        System.out.println("\n--- Демонстрация с символами ---");

        Queues queueProcessor = new Queues();
        List<Character> chars = Arrays.asList('x', 'y', 'z');

        System.out.println("Исходный список: " + chars);
        System.out.print("Результирующая очередь: ");
        queueProcessor.buildQueue(chars);
    }

    private static void customInput(Scanner scanner, Queues queueProcessor) {
        System.out.println("\n--- Пользовательский ввод ---");

        System.out.println("Введите элементы списка через пробел:");
        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Ввод пуст. Используем пример по умолчанию.");
            input = "1 2 3";
        }

        String[] elements = input.split("\\s+");
        List<String> list = Arrays.asList(elements);

        System.out.println("Исходный список: " + list);
        System.out.print("Результирующая очередь: ");
        queueProcessor.buildQueue(list);
    }
}
package ru.ushakova.main;

import java.util.Scanner;
import java.util.InputMismatchException;

import ru.ushakova.number1.FractionDemo;
import ru.ushakova.number2.CatDemo;
import ru.ushakova.number3.ListProcessorDemo;
import ru.ushakova.number4.FileReaderDemo;
import ru.ushakova.number5.TextAnalyzerDemo;
import ru.ushakova.number6.QueueDemo;
import ru.ushakova.number71.StreamDemo;
import ru.ushakova.number72.FileStreamDemo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №5 ===");
        System.out.println("Шаблоны ООП и коллекции");
        System.out.println("Выполнил: " + System.getProperty("user.name"));

        while (continueRunning) {
            printMenu();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch(choice) {
                    case 1:
                        FractionDemo.demo();
                        break;
                    case 2:
                        CatDemo.demo();
                        break;
                    case 3:
                        ListProcessorDemo.demo();
                        break;
                    case 4:
                        FileReaderDemo.demo();
                        break;
                    case 5:
                        TextAnalyzerDemo.demo();
                        break;
                    case 6:
                        QueueDemo.demo();
                        break;
                    case 7:
                        StreamDemo.demo();
                        break;
                    case 8:
                        FileStreamDemo.demo();
                        break;
                    case 0:
                        continueRunning = false;
                        System.out.println("Выход из программы...");
                        break;
                    default:
                        System.out.println("Неверный выбор! Попробуйте снова.");
                }

                if (continueRunning && choice != 0) {
                    System.out.println("\nНажмите Enter для продолжения...");
                    scanner.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите число от 0 до 7.");
                scanner.next(); // очистка неверного ввода
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
        System.out.println("Программа завершена. До свидания!");
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ГЛАВНОЕ МЕНЮ - ЛАБОРАТОРНАЯ РАБОТА №5");
        System.out.println("=".repeat(60));
        System.out.println("1. Задание 1 - Дроби с кэшированием (Паттерн Proxy)");
        System.out.println("2. Задание 2 - Подсчет мяуканий (Паттерн Proxy)");
        System.out.println("3. Задание 3 - Вставка в список (Дженерики)");
        System.out.println("4. Задание 4 - Обработка данных абитуриентов (Файлы, Map)");
        System.out.println("5. Задание 5 - Анализ текста");
        System.out.println("6. Задание 6 - Построение очереди");
        System.out.println("7. Задание 7 - Обработка точек стримом1");
        System.out.println("8. Задание 8 - Обработка точек стримом2");
        System.out.println("0. Выход");
        System.out.println("=".repeat(60));
        System.out.print("Выберите задание (0-8): ");
    }

    // Остальные вспомогательные методы остаются без изменений
    public static int getValidInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                int value = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Ошибка! Введите число от %d до %d.\n", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите целое число: ");
                scanner.next(); // очистка неверного ввода
            }
        }
    }

    public static double getValidDouble(Scanner scanner, String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // очистка буфера
                if (value >= min) {
                    return value;
                } else {
                    System.out.printf("Ошибка! Введите число не менее %.2f.\n", min);
                }
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите число: ");
                scanner.next(); // очистка неверного ввода
            }
        }
    }

    public static String getValidString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Ошибка! Ввод не может быть пустым.");
            }
        }
    }

    public static void demoAll() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦИЯ ВСЕХ ЗАДАНИЙ");
        System.out.println("=".repeat(60));

        System.out.println("\n>>> ЗАДАНИЕ 1: Дроби с кэшированием <<<");
        FractionDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 2: Подсчет мяуканий <<<");
        CatDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 3: Вставка в список <<<");
        ListProcessorDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 4: Обработка данных абитуриентов <<<");
        FileReaderDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 5: Анализ текста <<<");
        TextAnalyzerDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 6: Построение очереди <<<");
        QueueDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 7: Обработка точек стримом1 <<<");
        StreamDemo.demo();

        System.out.println("\n>>> ЗАДАНИЕ 8: Обработка точек стримом2 <<<");
        FileStreamDemo.demo();


        System.out.println("\n" + "=".repeat(60));
        System.out.println("ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА");
        System.out.println("=".repeat(60));
    }
}

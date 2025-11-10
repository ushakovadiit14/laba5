package ru.ushakova.number5;

import ru.ushakova.main.Main;

import java.util.*;
import java.io.*;

public class TextAnalyzerDemo {
    private static TextAnalyzer analyzer = new TextAnalyzer();
    private static boolean isFileLoaded = false;

    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 5: АНАЛИЗ РУССКОГО ТЕКСТА (SET) ===");

        boolean continueDemo = true;

        while (continueDemo) {
            printTextMenu();
            int choice = Main.getValidInt(scanner, "", 0, 3);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadDataFromFile(scanner);
                    break;
                case 2:
                    showMissingLetters();
                    break;
                case 3:
                    showFullStatistics();
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

    private static void printTextMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("АНАЛИЗ РУССКОГО ТЕКСТА");
        System.out.println("=".repeat(50));
        System.out.println("1. Загрузить и проанализировать текст");
        System.out.println("2. Показать отсутствующие буквы");
        System.out.println("3. Полная статистика");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void loadDataFromFile(Scanner scanner) {
        System.out.println("\n--- Загрузка и анализ текста ---");

        String filename = "text.txt";

        File file = new File(filename);

        analyzer.analyzeText(filename);
        isFileLoaded = true;
    }

    private static void showMissingLetters() {
        System.out.println("\n--- Отсутствующие буквы ---");

        if (!isFileLoaded) {
            System.out.println("Данные не загружены. Сначала загрузите данные (опция 1).");
            return;
        }

        String filename = "text.txt";
        analyzer.showMissingLettersOnly(filename);
    }

    private static void showFullStatistics() {
        System.out.println("\n--- Полная статистика ---");

        if (!isFileLoaded) {
            System.out.println("Данные не загружены. Сначала загрузите данные (опция 1).");
            return;
        }

        String filename = "russian_text.txt";
        analyzer.printFullStatistics(filename);
    }
}
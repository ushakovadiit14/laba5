package ru.ushakova.number72;

import ru.ushakova.main.Main;

import java.util.*;
import java.io.*;

public class FileStreamDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 7: ОБРАБОТКА ФАЙЛА С ИМЕНАМИ И НОМЕРАМИ ===");

        FileStreamProcessor processor = new FileStreamProcessor();
        boolean continueDemo = true;

        while (continueDemo) {
            printFileStreamMenu();
            int choice = Main.getValidInt(scanner, "", 0, 2);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    demonstrateWithDefaultFile(processor);
                    break;
                case 2:
                    showFileContent(scanner);
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

    private static void printFileStreamMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБРАБОТКА ФАЙЛА С ИМЕНАМИ И НОМЕРАМИ");
        System.out.println("=".repeat(50));
        System.out.println("1. Демонстрация с файлом по умолчанию");
        System.out.println("2. Показать содержимое файла");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void demonstrateWithDefaultFile(FileStreamProcessor processor) {
        System.out.println("\n--- Демонстрация с файлом по умолчанию ---");

        String filename = "names_data.txt";
        System.out.println("Используется файл: " + filename);

        try {
            Map<Integer, List<String>> result = processor.processFile(filename);

            System.out.println("\nРезультат обработки:");
            System.out.println(formatResult(result));

            // Показываем статистику
            showProcessingStats(result);

        } catch (Exception e) {
            System.out.println("❌ Ошибка при обработке файла: " + e.getMessage());
        }
    }

    private static void showFileContent(Scanner scanner) {
        System.out.println("\n--- Просмотр содержимого файла ---");

        String filename = "names_data.txt";
        System.out.println("Используем файл по умолчанию: " + filename);

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("❌ Файл не найден: " + filename);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("\nСодержимое файла '" + filename + "':");
            System.out.println("-".repeat(40));

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ". " + line);
                lineNumber++;
            }

            System.out.println("-".repeat(40));

        } catch (IOException e) {
            System.out.println("❌ Ошибка чтения файла: " + e.getMessage());
        }
    }

    // Вспомогательный метод для форматирования результата
    private static String formatResult(Map<Integer, List<String>> result) {
        if (result.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        List<Integer> sortedKeys = new ArrayList<>(result.keySet());
        Collections.sort(sortedKeys);

        for (int i = 0; i < sortedKeys.size(); i++) {
            Integer key = sortedKeys.get(i);
            List<String> names = result.get(key);
            Collections.sort(names); // сортируем имена для красоты

            sb.append(key).append(":[");
            for (int j = 0; j < names.size(); j++) {
                sb.append(names.get(j));
                if (j < names.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (i < sortedKeys.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    // Показать статистику обработки
    private static void showProcessingStats(Map<Integer, List<String>> result) {
        int totalGroups = result.size();
        int totalPeople = result.values().stream().mapToInt(List::size).sum();

        System.out.println("\n📊 Статистика обработки:");
        System.out.println("• Количество групп: " + totalGroups);
        System.out.println("• Всего людей: " + totalPeople);

        for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
            System.out.println("• Номер " + entry.getKey() + ": " + entry.getValue().size() + " человек(а)");
        }
    }
}
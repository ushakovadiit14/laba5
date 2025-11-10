package ru.ushakova.number71;

import java.util.*;

public class StreamDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 7: ОБРАБОТКА ТОЧЕК СТРИМОМ ===");

        StreamProcessor streamProcessor = new StreamProcessor();
        boolean continueDemo = true;

        while (continueDemo) {
            printStreamMenu();
            int choice = getValidInt(scanner, "", 0, 4);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    demonstrateWithNumbers();
                    break;
                case 2:
                    demonstrateWithMixed();
                    break;
                case 3:
                    demonstrateWithDuplicates();
                    break;
                case 4:
                    customInput(scanner, streamProcessor);
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

    private static void printStreamMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБРАБОТКА ТОЧЕК СТРИМОМ");
        System.out.println("=".repeat(50));
        System.out.println("1. Демонстрация с числами");
        System.out.println("2. Демонстрация со смешанными значениями");
        System.out.println("3. Демонстрация с дубликатами");
        System.out.println("4. Пользовательский ввод");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void demonstrateWithNumbers() {
        System.out.println("\n--- Демонстрация с числами ---");

        StreamProcessor processor = new StreamProcessor();
        List<Point> points = Arrays.asList(
                new Point(1, 2),
                new Point(3, -4),
                new Point(5, 6),
                new Point(0, 0),
                new Point(-2, -3)
        );

        System.out.println("Исходные точки: " + points);
        Polyline result = processor.processPoints(points);
        System.out.println("Результирующая ломаная: " + result);
    }

    private static void demonstrateWithMixed() {
        System.out.println("\n--- Демонстрация со смешанными значениями ---");

        StreamProcessor processor = new StreamProcessor();
        List<Point> points = Arrays.asList(
                new Point(2.5, -1.5),
                new Point(1.0, 3.0),
                new Point(-1.0, -2.0),
                new Point(0.0, 0.0),
                new Point(2.5, 4.0)
        );

        System.out.println("Исходные точки: " + points);
        Polyline result = processor.processPoints(points);
        System.out.println("Результирующая ломаная: " + result);
    }

    private static void demonstrateWithDuplicates() {
        System.out.println("\n--- Демонстрация с дубликатами ---");

        StreamProcessor processor = new StreamProcessor();
        List<Point> points = Arrays.asList(
                new Point(1, 2),
                new Point(3, -4),
                new Point(1, 2), // дубликат
                new Point(5, 6),
                new Point(3, -4), // дубликат
                new Point(-2, -3),
                new Point(0, 0),
                new Point(5, 7)
        );

        System.out.println("Исходные точки: " + points);
        Polyline result = processor.processPoints(points);
        System.out.println("Результирующая ломаная: " + result);
    }

    private static void customInput(Scanner scanner, StreamProcessor processor) {
        System.out.println("\n--- Пользовательский ввод ---");
        System.out.println("Введите точки в формате: x1 y1, x2 y2, x3 y3...");
        System.out.println("Пример: 1 2, 3 -4, 5 6");
        System.out.print("> ");

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Ввод пуст. Используем пример по умолчанию.");
            input = "1 2, 3 -4, 5 6";
        }

        try {
            List<Point> points = parsePoints(input);
            System.out.println("Исходные точки: " + points);
            Polyline result = processor.processPoints(points);
            System.out.println("Результирующая ломаная: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка в формате ввода. Используйте формат: x1 y1, x2 y2, ...");
        }
    }

    private static List<Point> parsePoints(String input) {
        List<Point> points = new ArrayList<>();
        String[] pointStrings = input.split(",");

        for (String pointStr : pointStrings) {
            String[] coords = pointStr.trim().split("\\s+");
            if (coords.length == 2) {
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                points.add(new Point(x, y));
            }
        }

        return points;
    }

    private static int getValidInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Введите число от %d до %d: ", min, max);
                }
            } catch (Exception e) {
                System.out.printf("Введите корректное число от %d до %d: ", min, max);
                scanner.next();
            }
        }
    }
}
package ru.ushakova.number3;

import ru.ushakova.main.Main;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class ListProcessorDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 3: ВСТАВКА В СПИСОК ===");

        ListProcessor processor = new ListProcessor();
        boolean continueDemo = true;

        while (continueDemo) {
            printListMenu();
            int choice = Main.getValidInt(scanner, "", 0, 6);

            switch (choice) {
                case 1:
                    showCurrentList(processor);
                    break;
                case 2:
                    addElementsInteractive(scanner, processor);
                    break;
                case 3:
                    performInsertOperation(scanner, processor);
                    break;
                case 4:
                    automaticDemo(processor);
                    break;
                case 5:
                    processor.clearList();
                    break;
                case 6:
                    searchElement(scanner, processor);
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

    private static void printListMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("РАБОТА СО СПИСКАМИ");
        System.out.println("=".repeat(50));
        System.out.println("1. Показать текущий список");
        System.out.println("2. Добавить элементы в список");
        System.out.println("3. Вставить элементы после первого вхождения E");
        System.out.println("4. Автоматическая демонстрация");
        System.out.println("5. Очистить список");
        System.out.println("6. Поиск элемента в списке");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void showCurrentList(ListProcessor processor) {
        System.out.println("\nТекущий список: " + processor);
        System.out.println("Размер списка: " + processor.getSize());
    }

    private static void addElementsInteractive(Scanner scanner, ListProcessor processor) {
        System.out.println("\n--- Добавление элементов ---");
        System.out.println("1. Добавить один элемент");
        System.out.println("2. Добавить несколько элементов");
        System.out.print("Выберите вариант: ");

        int option = Main.getValidInt(scanner, "", 1, 2);
        scanner.nextLine();

        if (option == 1) {
            addSingleElement(scanner, processor);
        } else {
            addMultipleElements(scanner, processor);
        }
    }

    private static void addSingleElement(Scanner scanner, ListProcessor processor) {
        System.out.print("Введите целое число: ");
        int element = scanner.nextInt();
        processor.addElement(element);
    }

    private static void addMultipleElements(Scanner scanner, ListProcessor processor) {
        System.out.print("Введите элементы через пробел: ");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] elements = input.split("\\s+");

        List<Integer> numbers = new java.util.ArrayList<>();
        for (String elem : elements) {
            try {
                numbers.add(Integer.parseInt(elem));
            } catch (NumberFormatException e) {
                System.out.println("Пропущен нечисловой элемент: " + elem);
            }
        }

        if (!numbers.isEmpty()) {
            processor.addAllElements(numbers);
        }
    }

    private static void performInsertOperation(Scanner scanner, ListProcessor processor) {
        System.out.println("\n--- Вставка элементов после первого вхождения E ---");

        if (processor.getSize() == 0) {
            System.out.println("Список пуст! Сначала добавьте элементы.");
            return;
        }

        System.out.println("Текущий список: " + processor);
        System.out.print("Введите элемент E для поиска: ");
        int elementE = scanner.nextInt();

        System.out.println("\n1. Вставить весь текущий список L");
        System.out.println("2. Вставить произвольные элементы");
        System.out.print("Выберите вариант вставки: ");

        int option = Main.getValidInt(scanner, "", 1, 2);

        if (option == 1) {
            insertCurrentList(processor, elementE);
        } else {
            insertCustomElements(scanner, processor, elementE);
        }
    }

    private static void insertCurrentList(ListProcessor processor, int elementE) {
        System.out.println("\nВыполняем: вставить список L после первого вхождения " + elementE);
        System.out.println("До: " + processor);
        processor.insertListAfterFirstOccurrence(elementE);
        System.out.println("После: " + processor);
    }

    private static void insertCustomElements(Scanner scanner, ListProcessor processor, int elementE) {
        System.out.print("Введите элементы для вставки через пробел: ");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] elements = input.split("\\s+");

        List<Integer> insertList = new java.util.ArrayList<>();
        for (String elem : elements) {
            try {
                insertList.add(Integer.parseInt(elem));
            } catch (NumberFormatException e) {
                System.out.println("Пропущен нечисловой элемент: " + elem);
            }
        }

        if (!insertList.isEmpty()) {
            System.out.println("\nВыполняем: вставить " + insertList + " после первого вхождения " + elementE);
            System.out.println("До: " + processor);
            processor.insertAfterFirstOccurrence(elementE, insertList);
            System.out.println("После: " + processor);
        }
    }

    private static void automaticDemo(ListProcessor processor) {
        System.out.println("\n--- АВТОМАТИЧЕСКАЯ ДЕМОНСТРАЦИЯ ---");

        demo1(processor);
        demo2(processor);
        demo3(processor);
        demo4(processor);
    }

    private static void demo1(ListProcessor processor) {
        System.out.println("\nДемонстрация 1: Базовый случай");
        processor.clearList();
        processor.addAllElements(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Исходный список L: " + processor);
        System.out.println("Вставляем список L после первого вхождения элемента 3");
        processor.insertListAfterFirstOccurrence(3);
        System.out.println("Результат: " + processor);
    }

    private static void demo2(ListProcessor processor) {
        System.out.println("\nДемонстрация 2: Элемент не найден");
        processor.clearList();
        processor.addAllElements(Arrays.asList(10, 20, 30));
        System.out.println("Исходный список L: " + processor);
        System.out.println("Пытаемся вставить после элемента 99 (которого нет в списке)");
        processor.insertListAfterFirstOccurrence(99);
        System.out.println("Результат: " + processor);
    }

    private static void demo3(ListProcessor processor) {
        System.out.println("\nДемонстрация 3: Вставка в начало");
        processor.clearList();
        processor.addAllElements(Arrays.asList(5, 6, 7));
        System.out.println("Исходный список L: " + processor);
        System.out.println("Вставляем список L после первого вхождения элемента 5");
        processor.insertListAfterFirstOccurrence(5);
        System.out.println("Результат: " + processor);
    }

    private static void demo4(ListProcessor processor) {
        System.out.println("\nДемонстрация 4: Вставка произвольных элементов");
        processor.clearList();
        processor.addAllElements(Arrays.asList(1, 2, 2, 3, 2, 4));
        System.out.println("Исходный список L: " + processor);
        System.out.println("Вставляем [99, 100] после первого вхождения элемента 2");
        processor.insertAfterFirstOccurrence(2, Arrays.asList(99, 100));
        System.out.println("Результат: " + processor);
    }

    private static void searchElement(Scanner scanner, ListProcessor processor) {
        System.out.println("\n--- Поиск элемента ---");
        System.out.print("Введите элемент для поиска: ");
        int element = scanner.nextInt();

        boolean found = processor.containsElement(element);
        if (found) {
            System.out.println("Элемент " + element + " найден в списке");
        } else {
            System.out.println("Элемент " + element + " не найден в списке");
        }
    }
}
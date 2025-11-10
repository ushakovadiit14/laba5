package ru.ushakova.number4;

import ru.ushakova.main.Main;

import java.util.*;
import java.io.*;

public class FileReaderDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 4: ОБРАБОТКА ДАННЫХ АБИТУРИЕНТОВ ===");

        ApplicantProcessor processor = new ApplicantProcessor();
        boolean continueDemo = true;

        while (continueDemo) {
            printFileMenu();
            int choice = Main.getValidInt(scanner, "", 0, 3);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadDataFromFile(scanner, processor);
                    break;
                case 2:
                    showAllApplicants(processor);
                    break;
                case 3:
                    showAdmittedApplicants(processor);
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

    private static void printFileMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ОБРАБОТКА ДАННЫХ АБИТУРИЕНТОВ");
        System.out.println("=".repeat(50));
        System.out.println("1. Загрузить данные из файла");
        System.out.println("2. Показать всех абитуриентов");
        System.out.println("3. Показать допущенных абитуриентов");
        System.out.println("0. Вернуться в главное меню");
        System.out.println("=".repeat(50));
        System.out.print("Выберите действие: ");
    }

    private static void loadDataFromFile(Scanner scanner, ApplicantProcessor processor) {
        System.out.println("\n--- Загрузка данных из файла ---");

        String filename = "applicants.txt";

        File file = new File(filename);

        processor.readFromFile(filename);
    }

    private static void showAllApplicants(ApplicantProcessor processor) {
        System.out.println("\n--- Все абитуриенты ---");
        Collection<Applicant> applicants = processor.getAllApplicants();

        if (applicants.isEmpty()) {
            System.out.println("Нет данных об абитуриентах. Сначала загрузите данные из файла.");
            return;
        }

        int index = 1;
        for (Applicant applicant : applicants) {
            String status = applicant.isAdmitted() ? "✓ ДОПУЩЕН" : "✗ НЕ ДОПУЩЕН";
            System.out.println(index + ". " + applicant + " - " + status);
            index++;
        }
    }

    private static void showAdmittedApplicants(ApplicantProcessor processor) {
        System.out.println("\n--- Абитуриенты, допущенные к экзаменам в первом потоке ---");
        List<Applicant> admitted = processor.getAdmittedApplicants();

        if (admitted.isEmpty()) {
            System.out.println("Нет допущенных абитуриентов.");
            return;
        }

        for (Applicant applicant : admitted) {
            System.out.println(applicant.getFullName());
        }
    }
}
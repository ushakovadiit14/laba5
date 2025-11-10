package ru.ushakova.number2;

import ru.ushakova.main.Main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CatDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 2: ПОДСЧЕТ МЯУКАНИЙ (ПАТТЕРН PROXY) ===");

        try {
            // Создаем оригинального кота
            System.out.println("\n1. Создаем кота:");
            Cat originalCat = new Cat("Барсик");
            System.out.println("Создан: " + originalCat);

            // Создаем прокси для подсчета мяуканий
            System.out.println("\n2. Создаем прокси для подсчета мяуканий:");
            CountingCatProxy countingCat = new CountingCatProxy(originalCat);

            // Демонстрация работы до вызова метода
            System.out.println("\n3. Демонстрация мяукания до вызова метода:");
            countingCat.meow();
            countingCat.meow();
            System.out.println("Текущее количество мяуканий: " + countingCat.getMeowCount());

            // Сбрасываем счетчик
            countingCat.resetCount();
            System.out.println("Счетчик сброшен.");

            // Вызов метода meowsCare (как в задании)
            System.out.println("\n4. Вызываем метод ru.ushakova.main.number2.Funs.meowsCare():");
            System.out.println("25 ru.ushakova.main.number2.Meowable m = ... // создаем кота");
            System.out.println("26 ru.ushakova.main.number2.Funs.meowsCare(m);");

            Funs.meowsCare(countingCat);

            // Вывод результата (как в задании)
            System.out.println("\n5. Результат после работы метода:");
            System.out.println("27 System.out.println(...) // вывод: кот мяукал " +
                    countingCat.getMeowCount() + " раз");
            System.out.println("кот мяукал " + countingCat.getMeowCount() + " раз");

            // Дополнительная демонстрация с несколькими котами
            System.out.println("\n6. Демонстрация с несколькими котами:");

            List<Meowable> cats = new ArrayList<>();
            cats.add(new CountingCatProxy(new Cat("Мурзик")));
            cats.add(new CountingCatProxy(new Cat("Васька")));
            cats.add(new CountingCatProxy(new Cat("Рыжик")));

            Funs.meowAll(cats);

            // Подсчет общего количества мяуканий
            int totalMeows = 0;
            for (Meowable cat : cats) {
                if (cat instanceof CountingCatProxy) {
                    totalMeows += ((CountingCatProxy) cat).getMeowCount();
                }
            }
            System.out.println("Всего мяуканий: " + totalMeows);

            // Пользовательский ввод
            System.out.println("\n7. Пользовательский ввод:");
            System.out.print("Введите имя кота: ");
            String catName = scanner.nextLine();

            System.out.print("Сколько раз должен мяукнуть кот в методе? ");
            int meowTimes = Main.getValidInt(scanner, "", 1, 10);

            Cat userCat = new Cat(catName);
            CountingCatProxy userCountingCat = new CountingCatProxy(userCat);

            // Модифицируем метод для пользовательского количества мяуканий
            System.out.println("\nВызываем метод с вашим котом:");
            for (int i = 0; i < meowTimes; i++) {
                userCountingCat.meow();
            }

            System.out.println("Ваш кот мяукал " + userCountingCat.getMeowCount() + " раз");

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
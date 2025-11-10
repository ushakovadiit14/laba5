package ru.ushakova.number1;

import java.util.Scanner;

public class FractionDemo {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== ЗАДАНИЕ 1: ДРОБИ С ПАТТЕРНОМ PROXY ===");

        try {
            // Демонстрация работы с кэшированием
            System.out.println("\n1. Создание дроби с кэшированием:");
            Fraction cachedFraction = new CachedFraction(3, 4);
            System.out.println("Дробь: " + cachedFraction);

            System.out.println("\n2. Первое вычисление (должно вычислить):");
            double value1 = cachedFraction.getValue();

            System.out.println("\n3. Второе вычисление (должно взять из кэша):");
            double value2 = cachedFraction.getValue();

            System.out.println("\n4. Значения совпадают: " + (value1 == value2));

            // Изменение значения
            System.out.println("\n5. Изменение числителя (должно сбросить кэш):");
            cachedFraction.setNumerator(5);
            System.out.println("Дробь после изменения: " + cachedFraction);

            System.out.println("\n6. Вычисление после изменения (должно вычислить заново):");
            cachedFraction.getValue();

            // Сравнение дробей
            System.out.println("\n7. Сравнение дробей:");
            Fraction frac1 = new CachedFraction(2, 4);
            Fraction frac2 = new CachedFraction(1, 2);
            System.out.println(frac1 + " equals " + frac2 + "? " + frac1.equals(frac2));

            // Пользовательский ввод
            System.out.println("\n8. Пользовательский ввод:");
            System.out.print("Введите числитель: ");
            int num = scanner.nextInt();
            System.out.print("Введите знаменатель: ");
            int den = scanner.nextInt();

            try {
                Fraction userFraction = new CachedFraction(num, den);
                System.out.println("Ваша дробь: " + userFraction);

                System.out.println("\nПервое вычисление:");
                userFraction.getValue();

                System.out.println("\nВторое вычисление (из кэша):");
                userFraction.getValue();

                // Упрощение
                System.out.println("\nУпрощение дроби:");
                ((CachedFraction) userFraction).simplify();
                System.out.println("После упрощения: " + userFraction);
                System.out.println("Вычисление после упрощения:");
                userFraction.getValue();

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}

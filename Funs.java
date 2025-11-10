package ru.ushakova.number2;

import java.util.List;

public class Funs {

    // Метод, который принимает мяукающий объект и вызывает мяуканье несколько раз
    public static void meowsCare(Meowable meowable) {
        System.out.println("Начало работы метода meowsCare...");

        // Имитация какой-то работы с котом
        for (int i = 0; i < 5; i++) {
            meowable.meow();
            try {
                Thread.sleep(100); // небольшая пауза для реалистичности
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Метод meowsCare завершил работу.");
    }

    // Дополнительный метод для работы с набором мяукающих объектов
    public static void meowAll(List<Meowable> meowables) {
        System.out.println("Все коты мяукают:");
        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }
}
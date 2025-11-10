package ru.ushakova.number72;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileStreamProcessor {

    public Map<Integer, List<String>> processFile(String filename) {
        // Создаем файл, если он не существует (аналогично вашему примеру)
        File file = new File(filename);

        return readAndProcessFile(filename);
    }

    private Map<Integer, List<String>> readAndProcessFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines()
                    // Разбиваем строки на имя и номер
                    .map(line -> line.split(":"))
                    // Фильтруем строки с корректным форматом (должны быть 2 части)
                    .filter(parts -> parts.length == 2)
                    // Убираем людей без номеров (пустой номер или не число)
                    .filter(parts -> !parts[1].trim().isEmpty() && isInteger(parts[1].trim()))
                    // Преобразуем: нормализуем имя и парсим номер
                    .map(parts -> new NameNumber(
                            normalizeName(parts[0].trim()),
                            Integer.parseInt(parts[1].trim())
                    ))
                    // Группируем по номеру
                    .collect(Collectors.groupingBy(
                            NameNumber::getNumber,
                            Collectors.mapping(NameNumber::getName, Collectors.toList())
                    ));

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Вспомогательный класс для хранения имени и номера
    private static class NameNumber {
        private final String name;
        private final int number;

        public NameNumber(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() { return name; }
        public int getNumber() { return number; }
    }

    // Приводим имя к нижнему регистру, но с первой буквой в верхнем регистре
    private String normalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    // Проверяем, является ли строка целым числом
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
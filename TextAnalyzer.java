package ru.ushakova.number5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TextAnalyzer {
    private Set<Character> textLetters;
    private Set<Character> missingLetters;
    private boolean isDataLoaded = false;

    public TextAnalyzer() {
        this.textLetters = new HashSet<>();
        this.missingLetters = new HashSet<>();
    }

    public void analyzeText(String filename) {
        try {
            loadData(filename);
            printResults();

        } catch (IOException e) {
            System.out.println("❌ Ошибка анализа текста: " + e.getMessage());
        }
    }

    public void showMissingLettersOnly(String filename) {
        if (!isDataLoaded) {
            try {
                loadData(filename);
            } catch (IOException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
                return;
            }
        }

        System.out.println("Количество отсутствующих букв: " + missingLetters.size());
        System.out.println("Отсутствующие буквы: " + missingLetters);
    }

    public void printFullStatistics(String filename) {
        if (!isDataLoaded) {
            try {
                loadData(filename);
            } catch (IOException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
                return;
            }
        }

        Set<Character> allLetters = getAllRussianLetters();

        System.out.println("=== ПОЛНАЯ СТАТИСТИКА ===");
        System.out.println("Всего букв в русском алфавите: " + allLetters.size());
        System.out.println("Встречается в тексте: " + textLetters.size());
        System.out.println("Отсутствует в тексте: " + missingLetters.size());
        System.out.println("Присутствующие буквы: " + new TreeSet<>(textLetters));
        System.out.println("Отсутствующие буквы: " + missingLetters);
    }

    private void loadData(String filename) throws IOException {
        textLetters = getCharactersFromFile(filename);
        missingLetters = findMissingLetters(textLetters);
        isDataLoaded = true;
    }

    private Set<Character> getCharactersFromFile(String filename) throws IOException {
        Set<Character> elements = new HashSet<>();

        try (FileReader fileReader = new FileReader(filename, StandardCharsets.UTF_8)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready()) {
                stringBuilder.append((char) fileReader.read());
            }

            String text = stringBuilder.toString().toLowerCase();

            for (char c : text.toCharArray()) {
                if (isRussianLetter(c)) {
                    elements.add(c);
                }
            }
        }

        return elements;
    }

    private Set<Character> findMissingLetters(Set<Character> textLetters) {
        String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Set<Character> alphabet = new HashSet<>();

        for (char c : russianAlphabet.toCharArray()) {
            alphabet.add(c);
        }

        Set<Character> missing = new TreeSet<>(alphabet);
        missing.removeAll(textLetters);

        return missing;
    }

    private boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    private void printResults() {
        System.out.println("\n=== РЕЗУЛЬТАТЫ АНАЛИЗА ===");
        System.out.println("Количество отсутствующих букв: " + missingLetters.size());

        if (missingLetters.isEmpty()) {
            System.out.println("✅ В тексте встречаются ВСЕ буквы русского алфавита!");
        } else {
            System.out.println("Отсутствующие буквы: " + missingLetters);
        }
    }

    private Set<Character> getAllRussianLetters() {
        String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Set<Character> alphabet = new TreeSet<>();
        for (char c : russianAlphabet.toCharArray()) {
            alphabet.add(c);
        }
        return alphabet;
    }
}
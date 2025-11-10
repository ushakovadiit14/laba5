package ru.ushakova.number3;

import java.util.ArrayList;
import java.util.List;

public class ListProcessor<T> {
    private List<T> list;

    public ListProcessor() {
        this.list = new ArrayList<>();
    }

    public ListProcessor(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }
        this.list = new ArrayList<>(list);
    }

    public void insertAfterFirstOccurrence(T element, List<T> elementsToInsert) {
        if (element == null || elementsToInsert == null || elementsToInsert.isEmpty()) {
            System.out.println("Ошибка: элемент для поиска или список для вставки не могут быть null/пустыми");
            return;
        }

        int index = list.indexOf(element);
        if (index == -1) {
            System.out.println("Элемент " + element + " не найден в списке. Вставка не выполнена.");
            return;
        }

        list.addAll(index + 1, elementsToInsert);
        System.out.println("Успешно вставлено " + elementsToInsert.size() +
                " элементов после первого вхождения " + element);
    }

    public void insertListAfterFirstOccurrence(T element) {
        insertAfterFirstOccurrence(element, new ArrayList<>(this.list));
    }

    public static <T> List<T> insertAfterFirstOccurrenceStatic(List<T> originalList, T element) {
        if (originalList == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        List<T> result = new ArrayList<>(originalList);
        int index = result.indexOf(element);
        if (index != -1) {
            List<T> copyToInsert = new ArrayList<>(originalList);
            result.addAll(index + 1, copyToInsert);
        }
        return result;
    }

    public void addElement(T element) {
        if (element != null) {
            list.add(element);
            System.out.println("Добавлен элемент: " + element);
        }
    }

    public void addAllElements(List<T> elements) {
        if (elements != null && !elements.isEmpty()) {
            list.addAll(elements);
            System.out.println("Добавлено " + elements.size() + " элементов: " + elements);
        }
    }

    public List<T> getList() {
        return new ArrayList<>(list);
    }

    public void clearList() {
        list.clear();
        System.out.println("Список очищен");
    }

    public boolean containsElement(T element) {
        return list.contains(element);
    }

    public int indexOfElement(T element) {
        return list.indexOf(element);
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public List<T> getUnmodifiableList() {
        return List.copyOf(list);
    }
}
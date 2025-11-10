package ru.ushakova.number6;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Queues {

    public <T> void buildQueue(List<T> list) {
        Queue<T> queue = new LinkedList<>(list);
        for (int i = list.size()-1; i >= 0; i--) {
            queue.add(list.get(i));
        }
        showQueue(queue);
    }

    private static <T> void showQueue(Queue<T> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println(); // добавляем перевод строки
    }
}
package ru.ushakova.number71;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProcessor {

    public Polyline processPoints(List<Point> points) {
        return points.stream()
                // Убираем точки с одинаковыми X,Y
                .distinct()
                // Сортируем по X
                .sorted(Comparator.comparingDouble(Point::getX))
                // Отрицательные Y делаем положительными
                .map(p -> new Point(p.getX(), Math.abs(p.getY())))
                // Собираем в ломаную
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Polyline::new
                ));
    }
}
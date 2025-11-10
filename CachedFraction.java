package ru.ushakova.number1;

public class CachedFraction implements Fraction {
    private final SimpleFraction realFraction;
    private Double cachedValue = null;

    public CachedFraction(int numerator, int denominator) {
        this.realFraction = new SimpleFraction(numerator, denominator);
    }

    public CachedFraction() {
        this.realFraction = new SimpleFraction();
    }

    @Override
    public int getDenominator() {
        return realFraction.getDenominator();
    }

    @Override
    public int getNumerator() {
        return realFraction.getNumerator();
    }

    @Override
    public void setDenominator(int denominator) {
        System.out.println("[Кэш " + (isCacheEmpty() ? "пуст" : "не пуст") + "]");
        realFraction.setDenominator(denominator);
        invalidateCache();
    }

    @Override
    public void setNumerator(int numerator) {
        System.out.println("[Кэш " + (isCacheEmpty() ? "пуст" : "не пуст") + "]");
        realFraction.setNumerator(numerator);
        invalidateCache();
    }

    @Override
    public double getValue() {
        System.out.println("[Кэш " + (isCacheEmpty() ? "пуст" : "не пуст") + "]");

        if (cachedValue == null) {
            System.out.println("[Кэш пуст] -> ВЫПОЛНЯЕТСЯ ВЫЧИСЛЕНИЕ");
            cachedValue = realFraction.getValue();
        } else {
            System.out.println("[Кэш не пуст] -> ИСПОЛЬЗУЕМ ЗНАЧЕНИЕ ИЗ КЭША: " + cachedValue);
        }
        return cachedValue;
    }

    @Override
    public String toString() {
        return realFraction.toString();
    }

    private boolean isCacheEmpty() {
        return cachedValue == null;
    }

    private void invalidateCache() {
        System.out.println("[Сброс кэша]");
        cachedValue = null;
    }

    // Дополнительный метод для упрощения
    public void simplify() {
        realFraction.simplify();
        invalidateCache();
    }
}

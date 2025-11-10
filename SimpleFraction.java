package ru.ushakova.number1;

import java.util.Objects;

public class SimpleFraction implements Fraction {
    private int numerator;
    private int denominator;

    public SimpleFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0.");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.denominator = denominator;
        this.numerator = numerator;
    }

    public SimpleFraction() {
        this(0, 1);
    }

    @Override
    public double getValue() {
        double value = (double) numerator / denominator;
        System.out.println("Вычисление значения: " + numerator + "/" + denominator + " = " + value);
        return value;
    }

    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        if (denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -denominator;
        } else {
            this.denominator = denominator;
        }
    }

    @Override
    public int getNumerator() {
        return numerator;
    }

    @Override
    public int getDenominator() {
        return denominator;
    }

    // Упрощение дроби
    public void simplify() {
        int gcd = findGCD(Math.abs(numerator), Math.abs(denominator));
        if (gcd > 1) {
            numerator /= gcd;
            denominator /= gcd;
        }
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        SimpleFraction other = (SimpleFraction) obj;

        // Приводим к упрощенному виду для сравнения
        SimpleFraction f1 = new SimpleFraction(this.numerator, this.denominator);
        SimpleFraction f2 = new SimpleFraction(other.numerator, other.denominator);
        f1.simplify();
        f2.simplify();

        return f1.numerator == f2.numerator && f1.denominator == f2.denominator;
    }

    @Override
    public int hashCode() {
        SimpleFraction simplified = new SimpleFraction(numerator, denominator);
        simplified.simplify();
        return Objects.hash(simplified.numerator, simplified.denominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

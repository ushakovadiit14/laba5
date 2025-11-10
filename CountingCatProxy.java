package ru.ushakova.number2;

public class CountingCatProxy implements Meowable {
    private final Cat realCat;
    private int meowCount;

    public CountingCatProxy(Cat cat) {
        this.realCat = cat;
        this.meowCount = 0;
    }

    @Override
    public void meow() {
        realCat.meow();
        meowCount++;
    }

    public int getMeowCount() {
        return meowCount;
    }

    public void resetCount() {
        meowCount = 0;
    }

    @Override
    public String toString() {
        return realCat.toString();
    }
}
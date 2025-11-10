package ru.ushakova.number2;

public class Cat implements Meowable {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу");
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }
}
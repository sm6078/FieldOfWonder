package org.javaacadmey.wonder_field.player;

public class Player {
    private final String name;
    private final String city;

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void shoutLetter() {

    }

    public void sayWord() {

    }

    public void makeMove() {
        System.out.printf("Ход игрока %s город %s%n", name, city);
        System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");

    }
}

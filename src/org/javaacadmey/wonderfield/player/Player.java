package org.javaacadmey.wonderfield.player;

import static org.javaacadmey.wonderfield.process.Game.scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private final String name;
    private final String city;

    private int points;

    private final Pattern patkirletter = Pattern.compile("[а-яА-Я]*");

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PlayerAnswer makeMove() {
        while (true) {
            System.out.print("Если хотите букву нажмите 'б' "
                    + "и enter, если хотите слово нажмите 'c' и enter: ");
            String userInput = scanner.nextLine();
            if (userInput.equals("с")) {
                return getWordFromUser();
            } else if (userInput.equals("б")) {
                return getLetterFromUser();

            } else {
                System.out.println("Вы ввели некорректные значения.");
            }
        }
    }

    private PlayerAnswer getLetterFromUser() {
        while (true) {
            System.out.print("введите букву: ");
            String userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Ожидаем  получить букву");
            } else {
                Matcher matkirletter = patkirletter.matcher(userInput);
                if (matkirletter.matches()) {
                    System.out.println("Игрок " + name + " буква: " + userInput);
                    return new PlayerAnswer(false, userInput.toUpperCase());
                } else {
                    System.out.println("Ожидаем букву на русском языке");
                }
            }
        }
    }

    private PlayerAnswer getWordFromUser() {
        while (true) {
            System.out.print("введите слово: ");
            String userInput = scanner.nextLine();
            if (userInput.length() == 1) {
                System.out.println("Ожидаем  получить слово");
            } else {
                Matcher matkirletter = patkirletter.matcher(userInput);
                if (matkirletter.matches()) {
                    System.out.println("Игрок " + name + " cлово: " + userInput);
                    return new PlayerAnswer(true, userInput.toUpperCase());
                } else {
                    System.out.println("Ожидаем слово на русском языке");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Player{"
                + "Имя='" + name + '\''
                + ", город='" + city + '\''
                + ", количество очков=" + points
                + '}';
    }
}

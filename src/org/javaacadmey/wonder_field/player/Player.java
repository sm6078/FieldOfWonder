package org.javaacadmey.wonder_field.player;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.javaacadmey.wonder_field.Game.scanner;

public class Player {
    private final String name;
    private final String city;

    private Pattern patkirletter = Pattern.compile("[а-яА-Я]*");

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void shoutLetter() {

    }

    public void sayWord() {

    }

    public PlayerAnswer makeMove() {
        System.out.printf("Ход игрока %s город %s%n", name, city);
        while (true) {
            System.out.print("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter: ");
            String userInput = scanner.nextLine();
            if (userInput.equals("с")) {
                System.out.println("Вы выбрали слово");
                return getWordFromUser();
            }
                else if (userInput.equals("б")) {
                System.out.println("Вы выбрали букву");
                return getLetterFromUser();

            } else {
                System.out.println("Вы ввели некорректные значения.");
            }
        }
    }

    public PlayerAnswer getLetterFromUser() {
        while(true) {
            System.out.print("введите букву: ");
            String userInput = scanner.nextLine();
            if (userInput.length() != 1) {
                System.out.println("Ожидаем  получить букву");
            } else {
                Matcher matkirletter = patkirletter.matcher(userInput);
                if (matkirletter.matches()==true)
                {
                    System.out.println("Кирилица");
                    System.out.println("Вы ввели букву: " + userInput);
                    return new PlayerAnswer(false, userInput);
                } else {
                    System.out.println("Ожидаем букву на русском языке");
                }
            }
        }
    }

    public PlayerAnswer getWordFromUser() {
        while(true) {
            System.out.print("введите слово: ");
            String userInput = scanner.nextLine();
            if (userInput.length() == 1) {
                System.out.println("Ожидаем  получить слово");
            } else {
                Matcher matkirletter = patkirletter.matcher(userInput);
                if (matkirletter.matches() == true)
                {
                    System.out.println("Кирилица");
                    System.out.println("Вы ввели слово: " + userInput);
                    return new PlayerAnswer(true, userInput);
                } else {
                    System.out.println("Ожидаем слово на русском языке");
                }
            }
        }
    }
}

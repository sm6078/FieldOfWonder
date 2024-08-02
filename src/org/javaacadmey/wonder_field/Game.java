package org.javaacadmey.wonder_field;

import java.util.Scanner;

public class Game {

    //количество игроков
    public final int NUMBER_OF_PLAYERS = 3;

    //всего количество раундов
    public final int TOTAL_ROUNDS = 4;

    //групповых раундов - 3,
    public final int COUNT_GROUP_ROUNDS = 3;

    //индекс финального раунда - 3
    public final int INDEX_FINAL_ROUND = 3;

    public static Scanner scanner = new Scanner(System.in);
    private String[][] questionsAnswers = new String[TOTAL_ROUNDS][2];

    private Tableau tableau;

    public void init() throws InterruptedException {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        //getFromUserData();
        fillQuestionsAnswers();
        printInputDate();
        scanner.close();
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        Thread.sleep(5000);
        System.out.print("\n".repeat(50));
    }

    public void getFromUserData() {
        for (int i = 1; i <= TOTAL_ROUNDS; i++) {
            System.out.print("Введите вопрос #" + i + ": ");
            questionsAnswers[i-1][0] = scanner.nextLine();
            System.out.print("Введите ответ #" + i + ": ");
            questionsAnswers[i-1][1] = scanner.nextLine().toUpperCase();
        }
    }

    public void printInputDate() {
        for (int i = 0; i < questionsAnswers.length; i++) {
            for (int j = 0; j < questionsAnswers[0].length; j++) {
                System.out.print(questionsAnswers[i][j] + " ");
            }
            System.out.println();
        }

    }
    public void fillQuestionsAnswers() {
        questionsAnswers = new String[][]{
                {"Вопрос 1", "ОТВЕТ1"},
                {"Вопрос 2", "ОТВЕТ2"},
                {"Вопрос 3", "ОТВЕТ3"},
                {"Вопрос 4", "ОТВЕТ4"}};
    }
}

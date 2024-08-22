package org.javaacadmey.wonderfield.process;

import java.util.Scanner;
import org.javaacadmey.wonderfield.drum.Drum;
import org.javaacadmey.wonderfield.player.Player;
import org.javaacadmey.wonderfield.player.PlayerAnswer;

public class Game {

    //количество игроков
    private static final int NUMBER_OF_PLAYERS = 3;

    //всего количество раундов
    private static final int TOTAL_ROUNDS = 4;

    //групповых раундов - 3,
    public static final int COUNT_GROUP_ROUNDS = 3;

    //индекс финального раунда - 3
    public static final int INDEX_FINAL_ROUND = 3;

    public static Scanner scanner = new Scanner(System.in);

    private String[][] questionsAnswers = new String[TOTAL_ROUNDS][2];

    private Player[] players = new Player[NUMBER_OF_PLAYERS];

    private Player[] winners = new Player[NUMBER_OF_PLAYERS];

    private Tableau tableau;

    private Yakubovich yakubovich;

    public Game(Yakubovich yakubovich) {
        this.yakubovich = yakubovich;
    }

    public void init() throws InterruptedException {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. "
                + "Вам нужно ввести вопросы и ответы для игры:");
        getFromUserQuestionsAnswers();
        //fillQuestionsAnswers();
        //printInputQuestionsAnswers();
        System.out.println("Подготовка к игре. Вам нужно указать данные игроков:");
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        Thread.sleep(5000);
        System.out.print("\n".repeat(50));
        yakubovich.start();
        play();
        scanner.close();
        yakubovich.finish();
    }

    private void play() {
        playQualifying();
        playFinal();
    }

    private void playQualifying() {
        for (int i = 0; i < INDEX_FINAL_ROUND; i++) {
            getFromUserPlayers();
            yakubovich.invite(getNamesForRound(players), i + 1);
            yakubovich.askQuestion(questionsAnswers[i][0]);
            tableau = new Tableau(questionsAnswers[i][1]);
            winners[i] = process(players, i);
        }
    }

    private Player process(Player[] players, int numberRound) {
        Player finalist = null;
        endProcess:
        for (int i = 0; i < players.length; i++) {
            boolean result = true;
            while (result) {
                System.out.printf("Ход игрока %s город %s%n",
                        players[i].getName(), players[i].getCity());
                int resultSpin = Drum.spin(players[i]);
                if (resultSpin == -1) {
                    break;
                }
                result = getResultMovie(players[i], resultSpin);
                if (!tableau.isUnknownLetters()) {
                    yakubovich.announcesWinner(players[i], numberRound);
                    finalist = players[i];
                    break endProcess;
                }
            }
            i = i == players.length - 1 ? -1 : i;
        }
        if (finalist == null) {
            throw new RuntimeException("Произошла непредвиденная ошибка. "
                    + "Продолжение игра невозможно");
        }
        return finalist;
    }

    private boolean getResultMovie(Player player, int resultSpin) {
        PlayerAnswer playerAnswer = player.makeMove();
        boolean result = yakubovich.checksAnswer(playerAnswer, tableau);
        if (result) {
            if (playerAnswer.isWholeWord()) {
                System.out.println(playerAnswer.getAnswer() + "! Абсолютно верно");
            } else {
                System.out.println("Есть такая буква! Откройте ее.");
                scoringPoint(resultSpin, playerAnswer.getAnswer(), player);
            }
        } else {
            if (playerAnswer.isWholeWord()) {
                System.out.println("Неверно! Следующий игрок!");
            } else {
                System.out.println("Нет такой буквы! Следующий игрок, крутите барабан!");
            }
        }
        tableau.showAllLetter();
        return result;
    }

    private void scoringPoint(int sector, String letter, Player player) {
        if (sector == -2) {
            player.setPoints(player.getPoints() * 2);
        } else {
            String str = tableau.getCorrectAnswer();
            player.setPoints(player.getPoints()
                    + sector
                    * (str.length() - str.replaceAll(letter, "").length()));
        }
    }

    private void playFinal() {
        yakubovich.invite(getNamesForRound(winners), INDEX_FINAL_ROUND + 1);
        yakubovich.askQuestion(questionsAnswers[INDEX_FINAL_ROUND][0]);
        tableau = new Tableau(questionsAnswers[INDEX_FINAL_ROUND][1]);
        process(winners, INDEX_FINAL_ROUND);
    }

    private String[] getNamesForRound(Player[] players) {
        String[] names = new String[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            names[i] = players[i].getName();
        }
        return names;
    }

    public void getFromUserQuestionsAnswers() {
        for (int i = 1; i <= TOTAL_ROUNDS; i++) {
            System.out.print("Введите вопрос #" + i + ": ");
            questionsAnswers[i - 1][0] = scanner.nextLine();
            System.out.print("Введите ответ #" + i + ": ");
            questionsAnswers[i - 1][1] = scanner.nextLine().toUpperCase();
        }
    }

    public void getFromUserPlayers() {
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            System.out.println("Игрок " + i + " представтесь");
            System.out.print("Ваше имя: ");
            String namePlayer = scanner.nextLine();
            System.out.print("Из какого вы города: ");
            String cityPlayer = scanner.nextLine();
            players[i - 1] = new Player(namePlayer, cityPlayer);
        }
    }

    public void printInputQuestionsAnswers() {
        for (int i = 0; i < questionsAnswers.length; i++) {
            for (int j = 0; j < questionsAnswers[0].length; j++) {
                System.out.print(questionsAnswers[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillQuestionsAnswers() {
        questionsAnswers = new String[][]{
                {"Вопрос 1", "ЯБЛОКО"},
                {"Вопрос 2", "ГРУША"},
                {"Вопрос 3", "АПЕЛЬСИН"},
                {"Вопрос 4", "МОЛОКО"}};
    }
}

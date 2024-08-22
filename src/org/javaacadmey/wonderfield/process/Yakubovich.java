package org.javaacadmey.wonderfield.process;

import static org.javaacadmey.wonderfield.process.Game.COUNT_GROUP_ROUNDS;
import static org.javaacadmey.wonderfield.process.Game.INDEX_FINAL_ROUND;

import org.javaacadmey.wonderfield.player.Player;
import org.javaacadmey.wonderfield.player.PlayerAnswer;

public class Yakubovich {

    public void start() {
        System.out.println("Здравствуйте, уважаемые дамы и господа! "
                + "Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void finish() {
        System.out.println("Мы прощаемся с вами ровно на одну неделю! "
                + "Здоровья вам, до встречи!");
    }

    public void invite(String[] names, int numberRound) {
        if (numberRound <= COUNT_GROUP_ROUNDS) {
            System.out.printf("Приглашаем %d тройку игроков к игре: %s%n",
                    numberRound, formattingNames(names));
        } else {
            System.out.println("Финальный раунд");
            System.out.printf("Приглашаю победителей групповых этапов: %s%n",
                    formattingNames(names));
        }
    }

    private String formattingNames(String[] names) {
        return String.join(", ", names);

    }

    public void askQuestion(String questions) {
        System.out.println("Внимание вопрос: ");
        System.out.println(questions);
    }

    public boolean checksAnswer(PlayerAnswer playerAnswer, Tableau tableau) {
        if (playerAnswer.isWholeWord()) {
            if (tableau.getCorrectAnswer().equals(playerAnswer.getAnswer())) {
                tableau.openWord();
                return true;
            }
        } else {
            if (tableau.getCorrectAnswer().contains(playerAnswer.getAnswer())) {
                if (checkRepeatLetter(playerAnswer.getAnswer(), tableau)) {
                    System.out.println("Такая буква уже есть. Переход хода.");
                    return false;
                }
                tableau.openLetter(playerAnswer.getAnswer());
                return true;
            }
        }
        return false;
    }

    private boolean checkRepeatLetter(String letter, Tableau tableau) {
        for (String str : tableau.getWord()) {
            if (str != null && str.equals(letter)) {
                return true;
            }
        }
        return false;
    }

    public void announcesWinner(Player player, int numberRound) {
        if (numberRound < COUNT_GROUP_ROUNDS) {
            System.out.printf("Молодец! %s из %s проходит в финал!%n",
                    player.getName(), player.getCity());
        } else if (numberRound == INDEX_FINAL_ROUND) {
            System.out.printf(" И перед нами победитель Капитал шоу поле чудес! "
                    + "Это %s из города %s%n", player.getName(), player.getCity());
            System.out.println("Количество очков, набранных игроком в во время игры: "
                    + player.getPoints());
        } else {
            System.out.println("Супер игра");
        }
    }
}

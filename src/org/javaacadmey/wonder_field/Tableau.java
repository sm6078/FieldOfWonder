package org.javaacadmey.wonder_field;

public class Tableau {
    private final String correctAnswer;
    private final String[] word;

    public Tableau(String correctAnswer) {
        this.correctAnswer = correctAnswer.toUpperCase();
        this.word = new String[correctAnswer.length()];
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getWord() {
        return word;
    }

    public void showAllLetter() {
        for (String s : word) {
            System.out.print(s == null ? "_" : s.toUpperCase());
        }
    }

    public void openLetter(String letter) {
        String let = letter.toUpperCase();
        String[] answer = correctAnswer.split("");
        for (int i = 0; i < answer.length; i++) {
            if (answer[i].equals(let)) {
                word[i] = let;
            }
        }
    }

    public void openWord() {
        for (int i = 0; i < word.length; i++) {
            word[i] = correctAnswer.substring(i, i + 1);
        }
    }

    private boolean isUnknownLetters() {
        for (String s : word) {
            if (s.equals("_")) {
                return true;
            }
        }
        return false;
    }
}

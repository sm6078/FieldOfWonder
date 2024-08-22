package org.javaacadmey.wonderfield.process;

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
        System.out.print("Табло -> ");
        for (String s : word) {
            System.out.print(s == null ? "_" : s.toUpperCase());
        }
        System.out.println();
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

    public boolean isUnknownLetters() {
        for (String s : word) {
            if (s == null) {
                return true;
            }
        }
        return false;
    }
}

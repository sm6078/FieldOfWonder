package org.javaacadmey.wonder_field;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        //Game game = new Game();
        //game.init();

        Tableau tableau = new Tableau("Король");

        System.out.println(tableau.getCorrectAnswer());

        for (String s: tableau.getWord()) {
            System.out.print(s);
        }
        System.out.println();
        tableau.showAllLetter();
        tableau.openLetter("о");

        System.out.println();
        tableau.showAllLetter();

        tableau.openWord();

        System.out.println();
        tableau.showAllLetter();
    }
}

package org.javaacadmey.wonderfield;

import org.javaacadmey.wonderfield.process.Game;
import org.javaacadmey.wonderfield.process.Yakubovich;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(new Yakubovich());
        game.init();
    }
}

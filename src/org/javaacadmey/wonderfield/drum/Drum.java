package org.javaacadmey.wonderfield.drum;

import java.util.Random;
import org.javaacadmey.wonderfield.player.Player;

public enum Drum {
    POINT,
    DOUBLING,
    SKIPPING;

    public static final int POSSIBLE_POINTS_OPTIONS = 12;
    private static final Random random = new Random();

    public static int spin(Player player) {
        int countSector = Drum.values().length - 1;
        int changedSector = random.nextInt(POSSIBLE_POINTS_OPTIONS + countSector - 1);
        if (changedSector > countSector || Drum.values()[changedSector].equals(Drum.POINT)) {
            int point = (random.nextInt(POSSIBLE_POINTS_OPTIONS - 1) + 1) * 100;
            System.out.println("Сектор " + point + " на барабане");
            return point;
        } else if (Drum.values()[changedSector].equals(Drum.DOUBLING)) {
            System.out.println("Сектор X2. Все ваши текущие очки удваиваются");
            return -2;
        } else {
            System.out.println("Сектор пропуск хода, ход переходит к следующему игроку");
            return -1;
        }
    }
}

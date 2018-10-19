package ru.job4j.bomberman;

/**
 * The entry point of this application. Emulates behaviour of one bomberman and one monster.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 09/10/2018
 */
public final class StartGame {
    public static void main(String[] args) {
        Board board = new Board(Constants.BOARD_LENGTH, Constants.BOARD_WIDTH);
        Thread hero = new Thread(new HeroAction(board));
        Thread monster = new Thread(new HeroAction(board));
        hero.setName("Bomberman");
        monster.setName("Monster");
        hero.start();
        monster.start();
        try {
            hero.join();
            monster.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
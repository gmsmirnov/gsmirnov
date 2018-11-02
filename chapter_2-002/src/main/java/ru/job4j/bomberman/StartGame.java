package ru.job4j.bomberman;

/**
 * The entry point of this application. Emulates behaviour of one bomberman and 3 monsters.
 * In the beginning on board located 10 blocks.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 09/10/2018
 */
public final class StartGame {
    public static void main(String[] args) {
        Board board = new Board(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, 10, 3);
        Thread hero = new Thread(new HeroAction(board));
        Thread[] monsters = new Thread[3];
        for (int i = 0; i < 3; i++) {
            monsters[i] = new Thread(new HeroAction(board));
            monsters[i].setName(String.format("Monster %d", i));
            monsters[i].start();
        }
        hero.setName("Bomberman");
        hero.start();
        try {
            hero.join();
            for (int i = 0; i < 3; i++) {
                monsters[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            board.releaseBlocks();
        }
    }
}
package ru.job4j.bomberman;

/**
 * The entry point of this application. Emulates behaviour of one bomberman and one monster.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class StartGame {
    public static void main(String[] args) {
        Board.init();
        Thread hero = new Thread(new HeroAction());
        Thread monster = new Thread(new HeroAction());
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
package ru.job4j.bomberman;

/**
 * The character's action. Creates a new thread in which this character moves.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class HeroAction implements Runnable {
    /**
     * Character's actions.
     */
    @Override
    public void run() {
        Hero hero = new Hero((int) (Math.random() * Constants.BOARD_LENGTH), (int) (Math.random() * Constants.BOARD_WIDTH));
        Board.locate(hero);
        while (!Thread.interrupted()) {
            Step step = new Step();
            while (!Board.checkLimits(hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY())) {
                step = new Step();
            }
            Board.move(hero.getX(), hero.getY(), hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY());
            hero = new Hero(hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY());
            try {
                Thread.sleep(Constants.TURN_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
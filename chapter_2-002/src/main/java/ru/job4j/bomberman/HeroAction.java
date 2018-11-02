package ru.job4j.bomberman;

/**
 * The character's action. Creates a new thread in which this character moves.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 09/10/2018
 */
public final class HeroAction implements Runnable {
    /**
     * The board on which located hero.
     */
    private final Board board;

    /**
     * Binds the board for hero movement.
     *
     * @param board - the specified board.
     */
    public HeroAction(Board board) {
        this.board = board;
    }

    /**
     * Character's actions. Character moves on the specified board.
     */
    @Override
    public void run() {
        Hero hero = new Hero((int) (Math.random() * Constants.BOARD_HEIGHT), (int) (Math.random() * Constants.BOARD_WIDTH));
        while (!this.board.locateHero(hero)) {
            try {
                Thread.sleep(Constants.LOCATION_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!Thread.currentThread().isInterrupted()) {
            Step step = new Step();
            while (!this.board.checkLimits(hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY())) {
                step = new Step();
            }
            this.board.move(hero.getX(), hero.getY(), hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY());
            hero = new Hero(hero.getX() + step.getDeltaX(), hero.getY() + step.getDeltaY());
            try {
                Thread.sleep(Constants.TURN_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        this.board.clear(hero.getX(), hero.getY());
    }
}
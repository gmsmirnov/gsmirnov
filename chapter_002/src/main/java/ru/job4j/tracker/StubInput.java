package ru.job4j.tracker;

/**
 * Provides emulation of users answers through input interface.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.3
 * @since 12/02/2018
 */
public class StubInput implements Input {
    /**
     * The array of emulates users answers.
     */
    private String[] answers;

    /**
     * Current position in array of emulated users answers.
     */
    private int position = 0;

    /**
     * StubInput constructor.
     *
     * @param answers - the array of emulated users answers.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Emulate users answer. Every time needs user answer, StubInput gets next answer from array.
     *
     * @param request - a message to user.
     * @return - next users answer from answers array.
     */
    @Override
    public String ask(String request) {
        return answers[position++];
    }

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @param range   - the range of allowable points.
     * @return - the point that user was selected.
     * @throws MenuOutException - wrong menu point.
     */
    @Override
    public int ask(String request, int[] range) throws MenuOutException {
        int result = -1;
        int answer = Integer.parseInt(answers[position++]);
        for (int value : range) {
            if (value == answer) {
                result = answer;
                break;
            }
        }
        if (result == -1) {
            throw new MenuOutException("Wrong point, please, select correct menu point.");
        }
        return result;
    }
}
package ru.job4j.monitore;

/**
 * The description of model. It contains a string and can append a number to itself.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/11/2018
 */
public class Model {
    /**
     * The string data;
     */
    private String data;

    /**
     * The flag, which shows if the model is busy by a thread or not.
     */
    private boolean busy;

    /**
     * The constructor.
     *
     * @param data - initial string.
     */
    public Model(String data) {
        this.data = data;
        this.busy = false;
    }

    /**
     * Modifies the data by appending the specified parameter.
     *
     * @param number - the specified parameter, which appends to the data string.
     */
    public void modify(int number) {
        this.data = String.format("%s%d", this.data, number);
    }

    /**
     * Gets the data.
     *
     * @return - the data of this model.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Checks is this model is busy by a thread.
     *
     * @return true if model is busy.
     */
    public boolean isBusy() {
        return this.busy;
    }

    /**
     * Sets the busy flag.
     *
     * @param busy - true - busy, false - not busy.
     */
    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
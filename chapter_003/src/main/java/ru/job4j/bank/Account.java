package ru.job4j.bank;

/**
 * Describes user bank account.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/04/2018
 */
public class Account {
    /**
     * Bank account requisites.
     */
    private final String requisites;

    /**
     * Account balance.
     */
    private double value;

    /**
     * User bank account constructor.
     *
     * @param requisites - the bank account requisites.
     * @param value - the account balance.
     */
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * User bank account creation with zero balance.
     *
     * @param requisites - the bank account requisites.
     */
    public Account(String requisites) {
        this.requisites = requisites;
        this.value = 0.0;
    }

    /**
     * Gets account requisites.
     *
     * @return - account requisites.
     */
    public String getRequisites() {
        return this.requisites;
    }

    /**
     * Gets account balance.
     *
     * @return - amount of money on the account.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Trandfers money between two accounts.
     *
     * @param dstAccount - destination account.
     * @param amount - amount of money.
     * @return - true if transfer was successful.
     */
    public boolean transfer(Account dstAccount, double amount) {
        boolean result = false;
        if (amount > 0 && this.value >= amount && dstAccount != null) {
            result = true;
            this.value -= amount;
            dstAccount.value += amount;
        }
        return result;
    }

    /**
     * Accounts comparison.
     *
     * @param otherAccount - other user account.
     * @return - true if requisites are equals.
     */
    @Override
    public boolean equals(Object otherAccount) {
        boolean result;
        if (this == otherAccount) {
            result = true;
        } else if (otherAccount == null || getClass() != otherAccount.getClass()) {
            result = false;
        } else {
            Account account = (Account) otherAccount;
            result = this.requisites != null ? this.requisites.equals(account.requisites) : account.requisites == null;
        }
        return result;
    }

    /**
     * Generates account hash code.
     *
     * @return - hash code.
     */
    @Override
    public int hashCode() {
        return 31 + this.requisites.hashCode();
    }

    /**
     * Presents account in string view.
     *
     * @return - account in string view.
     */
    @Override
    public String toString() {
        return String.format("Account{requisites='%s', value=%f}", this.requisites, this.value);
    }
}

package ru.job4j.bank;

import java.util.*;

/**
 * Emulate bank working.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/04/2018
 */
public class Bank {
    /**
     * User accounts.
     */
    private final Map<User, List<Account>> userAccounts = new HashMap<User, List<Account>>();

    /**
     * Add new user into bank.
     *
     * @param user - new user.
     */
    public void addUser(User user) {
        if (user != null) {
            this.userAccounts.putIfAbsent(user, new ArrayList<Account>());
        }
    }

    /**
     * Removes user from the bank (if exists).
     *
     * @param user - the user to remove.
     */
    public void deleteUser(User user) {
        this.userAccounts.remove(user);
    }

    /**
     * Add new account to user.
     *
     * @param passport - user passport.
     * @param account - new users bank account.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = this.findUser(passport);
        if (user != null && account != null && !this.userAccounts.get(user).contains(account)) {
            this.userAccounts.get(user).add(account);
        }
    }

    /**
     * Delete bank account from user accounts list.
     *
     * @param passport - user passport.
     * @param account - user bank account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = this.findUser(passport);
        if (user != null && this.userAccounts.get(user).contains(account)) {
            this.userAccounts.get(user).remove(account);
        }
    }

    /**
     * Finds user in hash map by passport.
     *
     * @param passport - passport.
     * @return - user if exists or null.
     */
    private User findUser(String passport) {
        User fUser = null;
        Set<User> users = this.userAccounts.keySet();
        for (User user : users) {
            if (user.getPassport().equals(passport)) {
                fUser = user;
            }
        }
        return fUser;
    }

    /**
     * Finds account in user list of accounts.
     *
     * @param passport - passport.
     * @return - account if exists or null.
     */
    private Account findAccount(String passport, String requisites) {
        Account fAccount = null;
        Optional<List<Account>> accounts = Optional.ofNullable(this.getUserAccounts(passport));
        if (accounts.isPresent()) {
            for (Account account : accounts.get()) {
                if (account.getRequisites().equals(requisites)) {
                    fAccount = account;
                }
            }
        }
        return fAccount;
    }

    /**
     * Gets user from hash map.
     *
     * @return - the set of users.
     */
    public Set<User> getUsers() {
        return this.userAccounts.keySet();
    }

    /**
     * Gets list of user accounts.
     *
     * @param passport - the user passport.
     * @return - the list of user accounts to which passport is matched, null otherwise.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> userAccounts = null;
        Optional<User> user = Optional.ofNullable(this.findUser(passport));
        if (user.isPresent()) {
            userAccounts = this.userAccounts.get(user.get());
        }
        return userAccounts;
    }

    /**
     * Returns user account if it is in the user account list.
     *
     * @param passport - the user password.
     * @param account - the account which needs to check.
     * @return - user account if it is in the user account list, null otherwise.
     */
    private Account getActualAccount(String passport, Account account) {
        Account resAccount = null;
        Optional<List<Account>> userAccounts = Optional.ofNullable(this.getUserAccounts(passport));
        int index = -1;
        if (userAccounts.isPresent()) {
            index = userAccounts.get().indexOf(account);
        }
        if (index >= 0) {
            resAccount = this.getUserAccounts(passport).get(index);
        }
        return resAccount;
    }

    /**
     * Transfers money from source account to destination account.
     *
     * @param srcPassport - source user passport.
     * @param srcRequisites - source user account requisites.
     * @param dstPassport - destination user account.
     * @param dstRequisites - destination user account requisites.
     * @param amount - the amount of money to transfer.
     * @return - true if transfer success, false other.
     */
    public boolean transfer(String srcPassport, String srcRequisites, String dstPassport, String dstRequisites, double amount) {
        boolean result = false;
        Optional<User> srcUser = Optional.ofNullable(this.findUser(srcPassport));
        Optional<User> dstUser = Optional.ofNullable(this.findUser(dstPassport));
        Optional<Account> srcAccount = Optional.ofNullable(this.findAccount(srcPassport, srcRequisites));
        Optional<Account> dstAccount = Optional.ofNullable(this.findAccount(dstPassport, dstRequisites));
        if (srcUser.isPresent() && srcAccount.isPresent() && dstUser.isPresent() && dstAccount.isPresent()) {
            result = this.userAccounts.get(srcUser.get()).contains(srcAccount.get()) && this.userAccounts.get(dstUser.get()).contains(dstAccount.get())
                    && this.getActualAccount(srcPassport, srcAccount.get()).transfer(this.getActualAccount(dstPassport, dstAccount.get()), amount);
        }
        return result;
    }
}
package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests bank.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/04/2018
 */
public class BankTest {
    @Test
    public void whenAddAccountThenUserHasThatAccount() {
        Bank bank = new Bank();
        User user = new User("Petrovich", "5000 643197");
        Account account = new Account("Sberbank", 100000.0);
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        ArrayList<Account> accounts = (ArrayList<Account>) bank.getUserAccounts(user.getPassport());
        Account result = accounts.get(accounts.indexOf(account));
        assertThat(result, is(account));
    }

    @Test
    public void whenTransferToAnotherAccountOfAnotherUserThanAnotherAccountHasMoney() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        User user2 = new User("Timosha", "5001 631978");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user2.getPassport(), account2);
        bank.transfer(user1.getPassport(), account1.getRequisites(), user2.getPassport(), account2.getRequisites(), 10000);
        double result = bank.getUserAccounts(user2.getPassport()).get(bank.getUserAccounts(user2.getPassport()).indexOf(account2)).getValue();
        assertThat(result, is(10000.0));
    }

    @Test
    public void whenTransferToAnotherWrongAccount() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        User user2 = new User("Timosha", "5001 631978");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB", 0.0);
        Account account3 = new Account("Levober", 0.0);
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user1.getPassport(), account3);
        bank.addAccountToUser(user2.getPassport(), account2);
        boolean result = bank.transfer(user1.getPassport(), account1.getRequisites(), user2.getPassport(), account3.getRequisites(), 10000);
        assertThat(result, is(false));
    }

    @Test
    public void whenAddAccountWithSameRequisitesThenNothingHappens() {
        Bank bank = new Bank();
        User petrovich = new User("Petrovich", "5000 643197");
        bank.addUser(petrovich);
        Account petrovichSberbank = new Account("Sberbank", 10000.0);
        bank.addAccountToUser(petrovich.getPassport(), petrovichSberbank);
        Account petrovichSberbank2 = new Account("Sberbank", 15000.0);
        bank.addAccountToUser(petrovich.getPassport(), petrovichSberbank2);
        ArrayList<Account> expected = new ArrayList<Account>();
        expected.add(petrovichSberbank);
        assertThat(bank.getUserAccounts("5000 643197"), is(expected));
    }

    @Test
    public void whenTransferToNullAccountThanFalse() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        User user2 = new User("Timosha", "5001 631978");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB", 0.0);
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user2.getPassport(), account2);
        boolean result = bank.transfer(user1.getPassport(), account1.getRequisites(), user2.getPassport(), null, 10000);
        assertThat(result, is(false));
    }

    @Test
    public void whenTransferToNullUserThanFalse() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB", 0.0);
        bank.addUser(user1);
        bank.addAccountToUser(user1.getPassport(), account1);
        boolean result = bank.transfer(user1.getPassport(), account1.getRequisites(), null, account2.getRequisites(), 10000);
        assertThat(result, is(false));
    }

    @Test
    public void whenTransferToAnotherAccountThanTrue() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB", 0.0);
        bank.addUser(user1);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user1.getPassport(), account2);
        boolean result = bank.transfer(user1.getPassport(), account1.getRequisites(), user1.getPassport(), account2.getRequisites(), 10000);
        System.out.println(account2.getValue());
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteAccountThenUserHasNotThatAccount() {
        Bank bank = new Bank();
        User user = new User("Petrovich", "5000 643197");
        Account account1 = new Account("Sberbank", 100000.0);
        Account account2 = new Account("VTB", 5000.0);
        Account account3 = new Account("BNP Paribas", 10000000.0);
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        bank.addAccountToUser(user.getPassport(), account3);
        ArrayList<Account> expected = new ArrayList<Account>();
        expected.add(account1);
        expected.add(account2);
        bank.deleteAccountFromUser(user.getPassport(), account3);
        ArrayList<Account> result = (ArrayList<Account>) bank.getUserAccounts(user.getPassport());
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserThenMapHasNotThatUser() {
        Bank bank = new Bank();
        User user1 = new User("Petrovich", "5000 643197");
        User user2 = new User("Timosha", "5001 631978");
        User user3 = new User("Ivanovich", "5008 080406");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        Set<User> expected = new HashSet<User>();
        expected.add(user1);
        expected.add(user3);
        bank.deleteUser(user2);
        Set<User> result = bank.getUsers();
        assertThat(result, is(expected));
    }
}
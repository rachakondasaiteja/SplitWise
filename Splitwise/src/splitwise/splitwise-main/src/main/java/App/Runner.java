package App;

import controllers.UserController;
import dtos.Transaction;
import models.*;
import repositories.ExpenseRepository;
import repositories.GroupRepository;
import repositories.UserExpenseRepository;
import repositories.UserRepository;
import services.UserService;
import strategies.HeapSettleUpStrategy;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        ExpenseRepository expenseRepository = new ExpenseRepository();
        GroupRepository groupRepository = new GroupRepository();
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository();

        // create the users
        User abhi = new User("Abhi", "1234", "1234");
        User raghu = new User("Raghu", "1234", "1234");
        User deepak = new User("Deepak", "1234", "1234");
        User murali = new User("Murali", "1234", "1234");
        User sanjay = new User("Sanjay", "1234", "1234");

        ArrayList<User> goaGuys = new ArrayList<>();
        goaGuys.add(abhi);
        goaGuys.add(raghu);
        goaGuys.add(deepak);
        goaGuys.add(murali);
        goaGuys.add(sanjay);


        // create the group
        Group goaGroup = new Group("Goa Trip");

        goaGroup.setUsers(goaGuys);

        // create the expense
        Expense expense = new Expense("Dinner", 6000, ExpenseType.NORMAL);

        // who paid and who had to pay
        UserExpense uE1 = new UserExpense(abhi, expense, 1000, UserExpenseType.HAD_TO_PAY);
        UserExpense uE2 = new UserExpense(raghu, expense, 1000, UserExpenseType.HAD_TO_PAY);
        UserExpense uE3 = new UserExpense(deepak, expense, 1000, UserExpenseType.HAD_TO_PAY);
        UserExpense uE4 = new UserExpense(murali, expense, 1000, UserExpenseType.HAD_TO_PAY);
        UserExpense uE5 = new UserExpense(sanjay, expense, 2000, UserExpenseType.HAD_TO_PAY);

        UserExpense uE6 = new UserExpense(abhi, expense, 6000, UserExpenseType.PAID_BY);

        // Add this data manually to the repositories
        userExpenseRepository.addUserExpense(uE1);
        userExpenseRepository.addUserExpense(uE2);
        userExpenseRepository.addUserExpense(uE3);
        userExpenseRepository.addUserExpense(uE4);
        userExpenseRepository.addUserExpense(uE5);
        userExpenseRepository.addUserExpense(uE6);

        userRepository.addUser(abhi);
        userRepository.addUser(raghu);
        userRepository.addUser(deepak);
        userRepository.addUser(murali);
        userRepository.addUser(sanjay);

        expenseRepository.addExpense(expense);

        goaGroup.getExpenses().add(expense);
        groupRepository.addGroup(goaGroup);

        UserController userController = new UserController(new UserService(
                groupRepository, userExpenseRepository, new HeapSettleUpStrategy()
        ));

        List<Transaction> transactions = userController.settleUser("abhi", "Goa Trip");

        System.out.println(transactions);

        // raghu, deepak, murali -> 1k to abhi
        // sanjay -> 2k to murali


    }
}

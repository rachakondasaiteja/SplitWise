package repositories;

import models.UserExpense;

import java.util.ArrayList;
import java.util.List;

public class UserExpenseRepository {

    private List<UserExpense> userExpenseList;

    public UserExpenseRepository() {
        userExpenseList = new ArrayList<>();
    }

    public void addUserExpense(UserExpense userExpense){
        userExpenseList.add(userExpense);
    }

    public List<UserExpense> findUserExpenseByExpense(String description) {

        ArrayList<UserExpense> userExpenses = new ArrayList<>();
        for(UserExpense userExpense: userExpenseList){

            // description as the expense id.
            if(description.equalsIgnoreCase(userExpense.getExpense().getDescription())){
                userExpenses.add(userExpense);
            }
        }
        return userExpenses;
    }
}

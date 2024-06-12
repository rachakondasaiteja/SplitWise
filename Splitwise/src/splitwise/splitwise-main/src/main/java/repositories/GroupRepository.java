package repositories;

import models.Expense;
import models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private List<Group> groups;

    public GroupRepository() {
        groups = new ArrayList<>();
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public List<Expense> findExpensesByGroup(String groupName) {

        for(Group group: groups){
            if(groupName.equalsIgnoreCase(group.getName())){
                return group.getExpenses();
            }
        }
        return new ArrayList<>();
    }
}

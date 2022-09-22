package entity;

import java.util.ArrayList;
import java.util.List;

public class UserListData {
    private List<UserData> listOfUser;

    public UserListData() {
        this.listOfUser = new ArrayList<>(); ;
    }

    public List<UserData> getListOfUser() {
        return listOfUser;
    }

}

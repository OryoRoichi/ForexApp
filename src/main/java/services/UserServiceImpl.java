package services;

import entity.MenuOperation;
import entity.UserData;
import entity.UserListData;


public class UserServiceImpl implements UserService {

    private UserListData userListData;

    public UserServiceImpl() {
        userListData = new UserListData();
    }

    @Override
    public UserData registration(String login, String password) {
        for (int i = 0; i < userListData.getListOfUser().size(); i++) {
            if (userListData.getListOfUser().get(i).getLogin().equals(login) &&
                    userListData.getListOfUser().get(i).getPassword().equals(password)) {
                return null;
            }
        }
        UserData newUser = new UserData(login, password);
        userListData.getListOfUser().add(newUser);
        return newUser;
    }

    @Override
    public UserData authorization(String login, String password) {
        for (int i = 0; i < userListData.getListOfUser().size(); i++) {
            if (userListData.getListOfUser().get(i).getLogin().equals(login) &&
                    userListData.getListOfUser().get(i).getPassword().equals(password)) {
                return userListData.getListOfUser().get(i);
            }
        }
        return null;
    }

    @Override
    public void logout(MenuOperation menu) {
        menu.enterMessage();
    }

}

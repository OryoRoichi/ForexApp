package services;

import entity.MenuOperation;
import entity.UserData;
import entity.UserListData;

import java.util.Optional;


public class UserServiceImpl implements UserService {

    private UserListData userListData;

    public UserServiceImpl() {
        userListData = new UserListData();
    }

    @Override
    public Optional<UserData> registration(String login, String password) {
        for (int i = 0; i < userListData.getListOfUser().size(); i++) {
            if (userListData.getListOfUser().get(i).getLogin().equals(login) &&
                    userListData.getListOfUser().get(i).getPassword().equals(password)) {
                return Optional.empty();
            }
        }
        UserData newUser = new UserData(login, password);
        userListData.getListOfUser().add(newUser);
        return Optional.of(newUser);
    }

    @Override
    public Optional<UserData> authorization(String login, String password) {
        for (int i = 0; i < userListData.getListOfUser().size(); i++) {
            if (userListData.getListOfUser().get(i).getLogin().equals(login) &&
                    userListData.getListOfUser().get(i).getPassword().equals(password)) {
                return Optional.of(userListData.getListOfUser().get(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public void logout(MenuOperation menu) {
        menu.enterMessage();
    }

}

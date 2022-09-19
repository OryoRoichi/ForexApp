package services;

import entity.MenuOperation;
import entity.UserData;

import static entity.UserData.listOfUser;

public class UserServiceImpl implements UserService {
    private MenuOperation menu;

    public UserServiceImpl() {

        this.menu = new MenuOperation();
    }

    @Override
    public UserData registration(String login, String password) {
        for (int i = 0; i < listOfUser.size(); i++) {
            if (listOfUser.get(i).getLogin() == login && listOfUser.get(i).getPassword() == password) {
                return null;
            }
        }
        return new UserData(login, password);
    }

    @Override
    public UserData authorization(String login, String password) {
        for (int i = 0; i < listOfUser.size(); i++) {
            if (listOfUser.get(i).getLogin() == login && listOfUser.get(i).getPassword() == password) {
                return listOfUser.get(i);
            }
        }
        return null;
    }

    @Override
    public void logout() {
        menu.enterMessage();
    }

}

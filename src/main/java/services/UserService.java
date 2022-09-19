package services;

import entity.UserData;

public interface UserService {
    UserData registration(String login, String password);
    UserData authorization(String login, String password);
    void logout();
}

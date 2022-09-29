package services;

import entity.MenuOperation;
import entity.UserData;

import java.util.Optional;

public interface UserService {
    Optional<UserData> registration(String login, String password);
    Optional<UserData> authorization(String login, String password);
    void logout(MenuOperation menu);
}

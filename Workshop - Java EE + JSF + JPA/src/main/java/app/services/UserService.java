package app.services;

import app.domain.models.servicesModels.UserServiceModel;

public interface UserService {

    void saveUser(UserServiceModel user);
    UserServiceModel findUserByUsernameAndPassword(String username,String password);
}

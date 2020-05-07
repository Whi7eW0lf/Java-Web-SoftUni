package app.services;

import app.domain.entities.User;
import app.domain.models.servicesModels.UserServiceModel;
import app.repositories.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserServiceModel user) {
        this.userRepository.save(this.modelMapper.map(user, User.class));
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {

        User user = this.userRepository.findByUsernameAndPassword(username,password);
        return this.modelMapper.map(user,UserServiceModel.class);
    }
}

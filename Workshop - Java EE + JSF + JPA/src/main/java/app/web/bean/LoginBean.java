package app.web.bean;

import app.domain.models.bindingModels.UserLoginBindingModel;
import app.domain.models.servicesModels.UserServiceModel;
import app.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private UserService userService;
    private ModelMapper modelMapper;
    private UserLoginBindingModel user;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new UserLoginBindingModel();
    }

    public void login() {

        String username = this.user.getUsername();
        String password = DigestUtils.sha256Hex(this.user.getPassword());

        UserServiceModel user = this.userService.findUserByUsernameAndPassword(username, password);


        if (user==null){
            redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("username",user.getUsername());

        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}

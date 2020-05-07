package app.web.bean;

import app.services.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DeleteBean extends BaseBean {

    private JobApplicationService service;

    public DeleteBean() {
    }

    @Inject
    public DeleteBean(JobApplicationService service) {
        this.service = service;
    }

    public void deleteJob(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.service.removeJob(id);
        this.redirect("/home");
    }
}

package app.web.bean;

import app.domain.entities.Sector;
import app.domain.models.bindingModels.JobApplicationCreateBindingModel;
import app.domain.models.servicesModels.JobApplicationServiceModel;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean{

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private JobApplicationCreateBindingModel jobApplication;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.jobApplication = new JobApplicationCreateBindingModel();
    }

    public void createJob(){
        JobApplicationServiceModel model = this.modelMapper.map(this.jobApplication,JobApplicationServiceModel.class);

        Sector sector;

        try {
            sector = Sector.valueOf(this.jobApplication.getSector());
        }catch (Exception ex){
            this.redirect("/add-job");
            return;
        }

        model.setSector(sector);

        this.jobApplicationService.saveJobApplication(model);
        this.redirect("/home");
    }

    public JobApplicationCreateBindingModel getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplicationCreateBindingModel jobApplication) {
        this.jobApplication = jobApplication;
    }
}

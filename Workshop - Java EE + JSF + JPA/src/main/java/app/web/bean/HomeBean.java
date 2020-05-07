package app.web.bean;

import app.domain.models.servicesModels.JobApplicationServiceModel;
import app.domain.models.viewModel.JobViewModel;
import app.services.JobApplicationService;
import jdk.jfr.Name;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<JobViewModel> jobs;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    @Inject
    public HomeBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public HomeBean() {
    }

    @PostConstruct
    public void init(){

        this.setJobs(this.jobApplicationService
                .getAll()
                .stream()
                .map(j->this.modelMapper.map(j,JobViewModel.class))
                .collect(Collectors.toList()));

        this.getJobs().forEach(e->e.setSector(e.getSector().toLowerCase()));
    }

    public List<JobViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobViewModel> jobs) {
        this.jobs = jobs;
    }
}

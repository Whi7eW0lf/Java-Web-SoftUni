package app.web.bean;

import app.domain.models.viewModel.JobViewModel;
import app.repositories.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobDetailsBean {

    private JobViewModel jobViewModel;
    private ModelMapper modelMapper;
    private JobApplicationRepository jobApplicationRepository;

    @Inject
    public JobDetailsBean(ModelMapper modelMapper, JobApplicationRepository jobApplicationRepository) {
        this.modelMapper = modelMapper;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobDetailsBean() {
    }

    public JobViewModel getJobById(String id) {
        System.out.println(id);
        return this.jobViewModel = this.modelMapper.map(this.jobApplicationRepository.findById(id), JobViewModel.class);
    }
}

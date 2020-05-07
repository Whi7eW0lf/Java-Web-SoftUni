package app.services;

import app.domain.entities.JobApplication;
import app.domain.models.servicesModels.JobApplicationServiceModel;
import app.repositories.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService{

    private final JobApplicationRepository repository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveJobApplication(JobApplicationServiceModel jobApplication) {
        this.repository.save(this.modelMapper.map(jobApplication, JobApplication.class));
    }

    @Override
    public JobApplicationServiceModel getJobApplicationById(String id) {
        return this.modelMapper.map(this.repository.findById(id),JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> getAll() {
        return this.repository.findAll()
                .stream()
                .map(j->this.modelMapper.map(j,JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeJob(String id) {
        this.repository.deleteJobById(id);
    }
}

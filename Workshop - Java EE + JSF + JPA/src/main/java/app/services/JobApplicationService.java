package app.services;

import app.domain.models.servicesModels.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    void saveJobApplication(JobApplicationServiceModel jobApplication);

    JobApplicationServiceModel getJobApplicationById(String id);

    List<JobApplicationServiceModel> getAll();

    void removeJob(String id);
}

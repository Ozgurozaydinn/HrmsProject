package javacamp.hrms.business.abstracts;

import java.util.List;

import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.entities.concretes.JobTitle;
import javacamp.hrms.entities.concretes.DTOs.JobTitleForAddDto;

public interface JobTitleService {
	DataResult<List<JobTitle>> getAll();
	Result add(JobTitleForAddDto jobTitle);
}

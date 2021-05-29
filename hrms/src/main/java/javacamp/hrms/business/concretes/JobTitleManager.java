package javacamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javacamp.hrms.business.abstracts.JobTitleService;
import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.core.utilities.results.SuccessDataResult;
import javacamp.hrms.core.utilities.results.SuccessResult;
import javacamp.hrms.dataAccess.abstracts.JobTitleDao;
import javacamp.hrms.entities.concretes.JobTitle;
import javacamp.hrms.entities.concretes.DTOs.JobTitleForAddDto;

@Service
public class JobTitleManager implements JobTitleService{
	
	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(),"Data listelendi.") ;
	}

	@Override
	public Result add(JobTitleForAddDto jobTitle) {
		JobTitle jobTitleForAdd = new JobTitle(jobTitle.getTitle());
		jobTitleDao.save(jobTitleForAdd);
		
		return new SuccessResult("İş pozisyonu eklendi");
	}

}

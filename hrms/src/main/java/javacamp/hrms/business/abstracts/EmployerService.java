package javacamp.hrms.business.abstracts;

import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.entities.concretes.Employer;
import javacamp.hrms.entities.concretes.DTOs.EmployerForRegisterDto;

import java.util.List;
public interface EmployerService {
	DataResult<List<Employer>> getAll();
	Result register(EmployerForRegisterDto employer);
}

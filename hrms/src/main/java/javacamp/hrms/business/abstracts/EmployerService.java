package javacamp.hrms.business.abstracts;

import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.entities.concretes.Employer;

import java.util.List;
public interface EmployerService {
	DataResult<List<Employer>> getAll();
}

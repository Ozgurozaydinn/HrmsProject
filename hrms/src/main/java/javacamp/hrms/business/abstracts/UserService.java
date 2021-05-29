package javacamp.hrms.business.abstracts;

import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.entities.concretes.User;
import javacamp.hrms.entities.concretes.DTOs.CandidateForRegisterDto;
import javacamp.hrms.entities.concretes.DTOs.EmployerForRegisterDto;


public interface UserService {
	Result isEmailUsing(CandidateForRegisterDto candidate);
	Result isEmailUsing(EmployerForRegisterDto employer);
	Result add(User user);
}

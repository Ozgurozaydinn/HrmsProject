package javacamp.hrms.business.abstracts;
import java.util.List;

import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.entities.concretes.Candidate;
import javacamp.hrms.entities.concretes.DTOs.CandidateForRegisterDto;

public interface CandidateService {
	DataResult<List<Candidate>> getAll();
	Result register(CandidateForRegisterDto candidate);
}

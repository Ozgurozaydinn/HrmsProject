package javacamp.hrms.core.Mernis;

import org.springframework.beans.factory.annotation.Autowired;


import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.core.utilities.results.SuccessResult;
import javacamp.hrms.entities.concretes.DTOs.CandidateForRegisterDto;
import lombok.Data;
@Data
public class MernisManager{

	private CandidateForRegisterDto candidate;
	
	@Autowired
	public MernisManager(CandidateForRegisterDto candidate) {
		this.candidate = candidate;
	}

	public Result isReal(CandidateForRegisterDto candidate) {
		return new SuccessResult("Kullanıcı doğrulama başarılı." + candidate.getFirstName());
	}

}

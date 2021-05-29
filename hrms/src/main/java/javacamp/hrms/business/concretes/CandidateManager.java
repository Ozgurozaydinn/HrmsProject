package javacamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javacamp.hrms.business.abstracts.CandidateService;
import javacamp.hrms.business.abstracts.UserService;
import javacamp.hrms.core.Mernis.MernisManager;
import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.ErrorResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.core.utilities.results.SuccessDataResult;
import javacamp.hrms.core.utilities.results.SuccessResult;
import javacamp.hrms.dataAccess.abstracts.CandidateDao;
import javacamp.hrms.entities.concretes.Candidate;
import javacamp.hrms.entities.concretes.User;
import javacamp.hrms.entities.concretes.DTOs.CandidateForRegisterDto;
@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private UserService userService;
	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserService userService) {
		super();
		this.candidateDao = candidateDao;
		this.userService = userService;
	}


	@Override
	public DataResult<List<Candidate>> getAll() {	
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Data listelendi");
	}


	@Override
	public Result register(CandidateForRegisterDto candidate) {
		if(isEverythingOk(candidate) != null) return isEverythingOk(candidate);
		return new SuccessResult("Kayıt başarıyla yapıldı");
	}
	
	public Result isEverythingOk(CandidateForRegisterDto candidate) {
		
		if(isAllFieldsFilled(candidate) != null) return isAllFieldsFilled(candidate);
		if(isPersonReal(candidate) != null) return isPersonReal(candidate);
		if(hasEmailBeenUsedBefore(candidate) != null) return hasEmailBeenUsedBefore(candidate);
		if(hasIdentificationNumberBeenUsedBefore(candidate) != null) return hasIdentificationNumberBeenUsedBefore(candidate);
		if(isEmailVerified(candidate) != null) return isEmailVerified(candidate);	
		
		User userForRegister = new User(candidate.getEmail(), candidate.getPassword());
		userService.add(userForRegister);
		Candidate candidateForRegister = new Candidate(userForRegister.getUserId(), candidate.getFirstName(), candidate.getLastName(), candidate.getIdentificationNumber(), candidate.getBirthDate());
		this.candidateDao.save(candidateForRegister);
		return null;
	}
	
	public Result isAllFieldsFilled(CandidateForRegisterDto candidate) {
		if(candidate.getFirstName() == null ||
			candidate.getLastName() == null ||
			candidate.getIdentificationNumber() == null ||
			candidate.getBirthDate() == null ||
			candidate.getEmail() == null ||
			candidate.getPassword() == null) {
			return new ErrorResult("Lütfen bütün alanları doldurunuz!");
		}
		return null;
	}
	
	public Result isPersonReal(CandidateForRegisterDto candidate) {
		MernisManager manager = new MernisManager(candidate);
		if(manager.isReal(candidate) == null) return new ErrorResult("Kullanıcı doğrulanmadı");
		return null; 
	}
	
	public Result hasEmailBeenUsedBefore(CandidateForRegisterDto candidate) {
		if(userService.isEmailUsing(candidate) != null) return new ErrorResult("Girdiğiniz eposta adresi başka bir hesaba bağlı!");
		return null;
	}
	
	public Result hasIdentificationNumberBeenUsedBefore(CandidateForRegisterDto candidate) {
		if(candidateDao.findByIdentificationNumber(candidate.getIdentificationNumber()) != null) return new ErrorResult("TC Kimlik numarası başka bir hesapta kullanılmış");
		return null;
	}
	
	public Result isEmailVerified(CandidateForRegisterDto candidate) {
		return null;
	}
	
	
	
}

package javacamp.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javacamp.hrms.business.abstracts.EmployerService;
import javacamp.hrms.business.abstracts.UserService;
import javacamp.hrms.core.utilities.results.DataResult;
import javacamp.hrms.core.utilities.results.ErrorResult;
import javacamp.hrms.core.utilities.results.Result;
import javacamp.hrms.core.utilities.results.SuccessDataResult;
import javacamp.hrms.core.utilities.results.SuccessResult;
import javacamp.hrms.dataAccess.abstracts.EmployerDao;
import javacamp.hrms.entities.concretes.Employer;
import javacamp.hrms.entities.concretes.User;
import javacamp.hrms.entities.concretes.DTOs.EmployerForRegisterDto;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private UserService userService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService) {
		super();
		this.employerDao = employerDao;
		this.userService = userService;
	}


	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(employerDao.findAll());
	}


	@Override
	public Result register(EmployerForRegisterDto employer) {
		if(isEverythingOk(employer) != null) return isEverythingOk(employer);
		
		User userForRegister = new User(employer.getEmail(), employer.getPassword());
		userService.add(userForRegister);
		Employer employerForRegister = new Employer(userForRegister.getUserId(), employer.getCompanyName(), employer.getWebAddress(), employer.getPhoneNumber());
		this.employerDao.save(employerForRegister);
		return new SuccessResult("Kayıt başarıyla yapıldı");
	}
	
	public Result isEverythingOk(EmployerForRegisterDto employer) {
		
		if(isAllFieldsFilled(employer) != null) return isAllFieldsFilled(employer);
		if(hasEmailBeenUsedBefore(employer) != null) return hasEmailBeenUsedBefore(employer);
		if(isEmailandWebsiteDomainSame(employer) != null) return isEmailandWebsiteDomainSame(employer);
		return null;
	}
	
	public Result isAllFieldsFilled(EmployerForRegisterDto employer) {
		if(employer.getCompanyName() == null ||
			employer.getWebAddress() == null ||
			employer.getEmail() == null ||
			employer.getPassword() == null ||
			employer.getPasswordRepeat() == null ||
			employer.getPhoneNumber() == null) {
			return new ErrorResult("Lütfen bütün alanları doldurunuz!");
		}
		
		return null;
	}
	
	public Result hasEmailBeenUsedBefore(EmployerForRegisterDto employer) {
		if(userService.isEmailUsing(employer) != null) return new ErrorResult("Girdiğiniz eposta adresi başka bir hesaba bağlı!");
		return null;
	}
	
	public Result isEmailandWebsiteDomainSame(EmployerForRegisterDto employer) {
		String email = employer.getEmail().trim();
		String[] emailSplit = email.split("@");
		if(emailSplit[1].toString() != employer.getWebAddress().toString()) {
			return new ErrorResult("E-posta adresinizin domaini web siteniz ile aynı olmalıdır.");
		}
		
		return null;
	}

}

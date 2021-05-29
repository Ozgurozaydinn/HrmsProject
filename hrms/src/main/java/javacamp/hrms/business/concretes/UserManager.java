package javacamp.hrms.business.concretes;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javacamp.hrms.business.abstracts.UserService;
import javacamp.hrms.core.utilities.results.*;

import javacamp.hrms.dataAccess.abstracts.UserDao;
import javacamp.hrms.entities.concretes.User;
import javacamp.hrms.entities.concretes.DTOs.CandidateForRegisterDto;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Result isEmailUsing(CandidateForRegisterDto candidate) {
		if(userDao.findByEmail(candidate.getEmail()) != null) return new ErrorResult();
		return null;
	}


	@Override
	public Result add(User user) {
		userDao.save(user);
		return new SuccessResult();
		
	}

}

package javacamp.hrms.entities.concretes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerForRegisterDto {
	
	private String companyName;
	private String webAddress;
	private String Email;
	private String phoneNumber;
	private String password;
	private String passwordRepeat;
}

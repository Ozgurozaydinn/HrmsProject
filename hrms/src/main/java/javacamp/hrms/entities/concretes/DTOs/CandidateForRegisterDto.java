package javacamp.hrms.entities.concretes.DTOs;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateForRegisterDto {
	private String firstName;
	private String lastName;
	private String identificationNumber;
	private Date birthDate;
	private String email;
	private String password;
}

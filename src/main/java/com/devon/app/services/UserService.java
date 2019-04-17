package com.devon.app.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devon.app.models.User;
import com.devon.app.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	public User getUserByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public User registerUser(User user) {
		
		// hash the user's password
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		
		
		// store that hash on the user object
		user.setPassword(hash);
		// save to the db
		
		return this.uRepo.save(user);
	}
	
	public boolean authenticateUser(String email, String password) {
		// make sure email is in the db
		User user = this.uRepo.findByEmail(email);
		// if so, make sure that the password is good
		if(user == null)
			return false;
		
		if(BCrypt.checkpw(password, user.getPassword())) {
			return true;
		}
		return false;
	}
}

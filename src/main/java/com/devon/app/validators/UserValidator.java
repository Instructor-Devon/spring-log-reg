package com.devon.app.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devon.app.models.User;
import com.devon.app.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
    
	@Autowired
	private UserRepository uRepo;
 
    @Override
    public boolean supports(Class<?> clazz) {
    	// we are tying UserValidator to our User class (com.devon.app.models.User)
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            // 3
            errors.rejectValue("confirmPassword", "Match");
        }
        
        // we also want to make sure email is unique
        if(this.uRepo.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Unique");
        }
        
    }
}

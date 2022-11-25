package com.switchfully.digibooky.security;

import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        EmailPassword emailPassword = getEmailPassword(authorization);
        User user = userRepository.getUser(emailPassword.getEmail());
        if (user == null || !doesPasswordMatch(user, emailPassword.getPassword())) {
            logger.error("Incorrect login try: " + emailPassword.getEmail() + emailPassword.getPassword());
            throw new IncorrectLogInInformationException("Given combination of email and password is not correct");
        }
        if (!canHaveAccessTo(user, feature)) {
            logger.error("User: " + emailPassword.getEmail() + " ,does not have access to: " + feature);
            throw new IncorrectLogInInformationException("User does not have access.");
        }
    }

    private boolean doesPasswordMatch(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean canHaveAccessTo(User user, Feature feature) {
        return user.getRole().containsFeature(feature);
    }

    private EmailPassword getEmailPassword(String authorization) {
        String decodedEmailAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodedEmailAndPassword.substring(0, decodedEmailAndPassword.indexOf(":"));
        String password = decodedEmailAndPassword.substring(decodedEmailAndPassword.indexOf(":") + 1);
        return new EmailPassword(email, password);
    }

    public String getUserIdByAuthorizationString(String authorization) {
        EmailPassword emailPassword = getEmailPassword(authorization);
        User user = userRepository.getUser(emailPassword.getEmail());
        return user.getUserId();
    }
}
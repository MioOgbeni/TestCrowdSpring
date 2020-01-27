package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.RoleType;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImp implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Qualifier("userDetailsServiceImp")
    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImp.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    @Override
    public UserImp getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    @Override
    public boolean isCurrentUserById(String id) {
        return this.getCurrentUser().getId().equals(id);
    }

    @Override
    public boolean isCurrentUserAdmin() {
        UserImp user = this.getCurrentUser();
        return user.getRoleType() == RoleType.ADMIN;
    }

    @Override
    public boolean isCurrentUserReporter() {
        UserImp user = this.getCurrentUser();
        return user.getRoleType() == RoleType.REPORTER || user.getRoleType() == RoleType.ADMIN;
    }

    @Override
    public boolean isCurrentUserTester() {
        UserImp user = this.getCurrentUser();
        return user.getRoleType() == RoleType.TESTER || user.getRoleType() == RoleType.ADMIN;
    }

}

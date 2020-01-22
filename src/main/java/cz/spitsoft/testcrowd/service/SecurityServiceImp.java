package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.Role;
import cz.spitsoft.testcrowd.model.RoleType;
import cz.spitsoft.testcrowd.model.UserImp;
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

import java.util.Set;
import java.util.UUID;

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
    public String getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserImp user = userService.findByUsername(username);
        return user.getId();
    }

    @Override
    public RoleType getCurrentUserRoleType() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserImp user = userService.findByUsername(username);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            switch (role.getName()) {
                case ADMIN:
                    return RoleType.ADMIN;
                case COMPANY:
                    return RoleType.COMPANY;
                case USER:
                    return RoleType.USER;
            }
        }
        return null;
    }
}

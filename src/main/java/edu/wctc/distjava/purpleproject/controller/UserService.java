package edu.wctc.distjava.purpleproject.controller;

import edu.wctc.distjava.purpleproject.domain.Department;
import edu.wctc.distjava.purpleproject.domain.DepartmentEAO;
import edu.wctc.distjava.purpleproject.domain.Employee;
import edu.wctc.distjava.purpleproject.domain.EmployeeEAO;
import edu.wctc.distjava.purpleproject.domain.User;
import edu.wctc.distjava.purpleproject.domain.UserEAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * This class is a JSF Managed Bean. However, it looks different from a 
 * normal JSF bean because it does not use the @ManagedBean annotation.
 * Instead it uses the @Named annotation, which is a feature of a new JEE
 * technology called CDI. Think of CDI as having both ManagedBean capabilities
 * plus others, including dependency injection.
 * 
 * Additionally, I chose to name this bean a "Service" bean because it
 * fulfills that role. This is a personal choice, but an appropriate one.
 * 
 * Finally, notice the @EJB annotation. This is one way to inject an 
 * Enterprise Java Bean (EJB). However, it requires a java application server
 * that is a full-stack server like Glassfish. This won't work on servers 
 * that are just servlet containers or those that don't support EJBs. Another
 * way to inject objects is to use CDI via the @Inject annotation. Again, you
 * need a jee6 certified server that supports CDI. I like to use @EJB for EJBs 
 * and @Inject for other POJOs. (Only CDI allows injection of POJOs.) Were we
 * to use Spring, we would have all of these capabilities without needing a
 * JEE 6 certified server -- one of the big advantages of using Spring. And
 * yet if youd do use Spring on a certified server, Spring is smart of enough
 * to defer to the server for resource management.
 * 
 * @author     Jim Lombardo
 * @version    1.01
 */
@Named(value = "userSrv")
@RequestScoped
public class UserService {
    @Inject
    private UserEAO userEAO;

    private String authority;

    /**
     * Creates a new instance of EmployeeService
     */
    public UserService() {
    }
 
    public void saveNew(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(true);
        
        if(!userEAO.isUsernameInUse(username)) {
            userEAO.addNewUser(user,authority);
        }
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    

 }
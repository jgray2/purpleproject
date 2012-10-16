package edu.wctc.distjava.purpleproject.controller;

import edu.wctc.distjava.purpleproject.domain.Employee;
import edu.wctc.distjava.purpleproject.domain.EmployeeEAO;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
 * Enterprise Java Bean (EJB). However, CDI also allows the use of the
 * @Inject annotation instead. I like to use @EJB for EJBs and @Inject for
 * other POJOs. (Only CDI allows injection of POJOs.)
 * 
 * @author     Jim Lombardo
 * @version    1.00
 */
@Named(value = "employeeSrv")
@RequestScoped
public class EmployeeService {
    @EJB
    private EmployeeEAO empEAO;
    
    // Don't need this unless using @PostConstruct to cache
//    private List<Employee> employeeList;

    /**
     * Creates a new instance of EmployeeService
     */
    public EmployeeService() {
    }
    
    // Don't need this unless we want to cache
//    @PostConstruct
//    private void initEmployeeList() {
//        employeeList = empEAO.findAll();
//    }
    
    /**
     * Delegate to the employeeEAO to get the data. Notice that we have
     * a getter method and no matching property. Technically the property
     * isn't needed if all we want to do is get the list each method access.
     * But in the JSF view page, we reference the property. JSF automatically
     * looks for the matching getter method.
     * 
     * However, if we wanted to pre-fetch the employee list (assuming it
     * wouldn't change often) we could create a property and fill it from
     * within a separate method annotated with @PostConstruct (see above).
     * 
     * @return list of entity objects or empty list of non found.
     */
    public List<Employee> getEmployeeList() {
        return empEAO.findAll();
    }
}

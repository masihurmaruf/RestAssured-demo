package com.codingmechanic.common;

/**
 * Created by masihur on 3/19/17.
 */
public interface EndPoint {

    String GET_EMPLOYEE = "/employee/getEmployee";

    String GET_ALL="/employee/getAll";

    String GET_EMPLOYEE_QUERY_PARAM = "/employee/getEmployeeQuery";

    String GET_EMPLOYEE_PATH_PARAM = "/employee/getEmployee/{employeeId}";

    String POST_EMPLOYEE_PARAM = "/employee/addEmployee";

    String GOOGLE_API = "v1/volumes";
}

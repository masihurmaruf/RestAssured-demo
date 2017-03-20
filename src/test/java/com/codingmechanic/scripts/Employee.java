package com.codingmechanic.scripts;

import com.codingmechanic.bean.EmployeeBean;
import com.codingmechanic.common.EndPoint;
import com.codingmechanic.framework.RestAssuredConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by masihur on 3/19/17.
 */
public class Employee {
    @Test
    public void validateEmployee(){
        given()
            .get("http://localhost:8000/SprintRestServices/api/v1/employee/getEmployee")
        .then()
            .statusCode(200)
                .log().all();
    }
    @Test(groups = "default")
    public void validateEmployee2(){
        given()
            .get(EndPoint.GET_EMPLOYEE)
        .then()
            .statusCode(200)
                .log().all();
    }

    @Test(groups = {"default", "response"})
    public void validateQueryParameter(){
        RequestSpecification reqSpec = new RestAssuredConfiguration().getRequestSpec();
        reqSpec.queryParam("employeeId", 200).log().all();
        given()
            .spec(reqSpec).get(EndPoint.GET_EMPLOYEE_QUERY_PARAM)
        .then()
            .statusCode(200)
                .log().all();
        // Getting response
        Response response = given().spec(reqSpec).get(EndPoint.GET_EMPLOYEE_QUERY_PARAM);

        // Inline assertion
        // 1. Hard Assertion
        response.then().body("firstName",equalTo("Fluent"))
                       .body("lastName", equalTo("Wait"))
                       .body("address", equalTo("New York"));
        // 2. Soft Assertion
        response.then().body("firstName",equalTo("Fluent"),
                             "lastName", equalTo("Wait"),
                             "address", equalTo("New York"));

        // Path Validation
        // 1. Hard Validation
        Assert.assertEquals(response.path("firstName"), "Fluent");
        Assert.assertEquals(response.path("lastName"), "Wait");
        Assert.assertEquals(response.path("address"), "New York");
        // 2. Soft Validation
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("firstName"), "Fluent");
        softAssert.assertEquals(response.path("lastName"), "Wait");
        softAssert.assertEquals(response.path("address"), "New York");
        softAssert.assertAll();

        // Java Object Mapping Validation
        EmployeeBean employeeBean = response.as(EmployeeBean.class);
        //  1. Hard Assertion
        Assert.assertEquals(employeeBean.getFirstName(), "Fluent");
        Assert.assertEquals(employeeBean.getLastName(), "Wait");
        Assert.assertEquals(employeeBean.getAddress(), "New York");
        // 2. Soft Validation
        SoftAssert newSoftAssert = new SoftAssert();
        newSoftAssert.assertEquals(employeeBean.getFirstName(), "Fluent");
        newSoftAssert.assertEquals(employeeBean.getLastName(), "Wait");
        newSoftAssert.assertEquals(employeeBean.getAddress(), "New York");
        newSoftAssert.assertAll();

    }

    @Test(groups = {"default"})
    public void validatePathParameter(){
        RequestSpecification reqSpec = new RestAssuredConfiguration().getRequestSpec();
        reqSpec.pathParam("employeeId", 100).log().all();
        given()
                .spec(reqSpec).get(EndPoint.GET_EMPLOYEE_PATH_PARAM)
                .then()
                .statusCode(200)
                .log().all();
    }
}

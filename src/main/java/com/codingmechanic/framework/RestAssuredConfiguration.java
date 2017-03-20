package com.codingmechanic.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

/**
 * Created by masihur on 3/19/17.
 */
public class RestAssuredConfiguration {

    public void configure(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/SprintRestServices/api/v1";
    }

    public RequestSpecification getRequestSpec(){
        return RestAssured.given().contentType(ContentType.JSON);
    }
}

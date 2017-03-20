package com.codingmechanic.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

/**
 * Created by masihur on 3/19/17.
 */
public class GoogleApiRestAssuredConfiguration {

    @BeforeSuite(alwaysRun = true)
    public void configure(){
        RestAssured.baseURI = "https://www.googleapis.com/";
        RestAssured.basePath = "/books";
    }

    public RequestSpecification getRequestSpec(){
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification reqSpec, String endPoint, int status){
        Response response = reqSpec.get(endPoint);
        Assert.assertEquals(response.getStatusCode(), status);
        response.then().log().all();
        return response;
    }
}

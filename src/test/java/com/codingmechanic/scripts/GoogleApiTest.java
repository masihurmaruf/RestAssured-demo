package com.codingmechanic.scripts;

import com.codingmechanic.bean.Google;
import com.codingmechanic.bean.Item;
import com.codingmechanic.bean.VolumeInfo;
import com.codingmechanic.common.EndPoint;
import com.codingmechanic.framework.GoogleApiRestAssuredConfiguration;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by masihur on 3/19/17.
 */
public class GoogleApiTest {
    @Test(groups = {"demo", "google"})
    public void googleApiTest(){
        RequestSpecification requestSpecification = new GoogleApiRestAssuredConfiguration().getRequestSpec();
        requestSpecification.queryParam("q", "turing");
        Response response = new GoogleApiRestAssuredConfiguration()
                .getResponse(requestSpecification, EndPoint.GOOGLE_API, HttpStatus.SC_OK);

        Google google = response.as(Google.class, ObjectMapperType.GSON);

        SoftAssert softAssert = new SoftAssert();

        List<Item> items = google.getItems();
        for(Item item: items){
            System.out.println(item.toString());
            softAssert.assertTrue(!item.getId().equalsIgnoreCase(""), "ID IS BLANK");
            VolumeInfo volInfo = item.getVolumeInfo();
            softAssert.assertTrue(!volInfo.getTitle().equalsIgnoreCase(""), "TITLE IS BLANKE");
        }
        softAssert.assertAll();
    }
}

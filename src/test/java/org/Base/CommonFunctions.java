package org.Base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class CommonFunctions {
    public static RequestSpecification getRequestSpecification(){
        return RestAssured.given().contentType("application/json");
    }
}

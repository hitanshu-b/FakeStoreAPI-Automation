package org.Actions;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {

    public void assertStatusCode(Response response, int expectedStatusCode){
        assertEquals(response.statusCode(), expectedStatusCode, "StatusCode Mismatch");
    }

    public void assertResponseField(Response response, String jsonPath, Object expectedValue){
        assertEquals(response.jsonPath().get(jsonPath), expectedValue, "Field Mismatch");
    }

}

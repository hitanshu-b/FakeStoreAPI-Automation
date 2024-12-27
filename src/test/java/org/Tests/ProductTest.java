package org.Tests;

import io.restassured.response.Response;
import org.Actions.AssertActions;
import org.Base.CommonFunctions;
import org.Endpoints.APIConstants;
import org.Modules.PayloadManager;
import org.testng.annotations.Test;

public class ProductTest {
    AssertActions assertions = new AssertActions();

    @Test
    public void testGetAllProducts(){
        Response response = CommonFunctions.getRequestSpecification()
                .get(APIConstants.GET_PRODUCTS);
        assertions.assertStatusCode(response,200);
        System.out.println(response.body().asPrettyString());
    }

    @Test
    public void testCreateProductWithValidPayload(){
        String payload = PayloadManager.getProductPayload(0);
        Response response = CommonFunctions.getRequestSpecification()
                .body(payload)
                .post(APIConstants.CREATE_PRODUCT);
        assertions.assertStatusCode(response, 201);
        assertions.assertResponseField(response, "title", "Sample Product");
    }
}

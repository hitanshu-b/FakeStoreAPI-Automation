package org.Tests;

import io.restassured.response.Response;
import org.Actions.AssertActions;
import org.Base.CommonFunctions;
import org.Endpoints.APIConstants;
import org.Modules.PayloadManager;
import org.testng.Assert;
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

    @Test
    public void testCreateProductWithMissingTitle(){
        String payload = PayloadManager.getProductPayload(3);
        Response response = CommonFunctions.getRequestSpecification()
                .body(payload)
                .post(APIConstants.CREATE_PRODUCT);
        assertions.assertStatusCode(response,400);
    }

    @Test
    public void testCreateProductWithInvalidPrice(){
        String payload = PayloadManager.getProductPayload(4);
        Response response = CommonFunctions.getRequestSpecification()
                .body(payload)
                .post(APIConstants.CREATE_PRODUCT);
        assertions.assertStatusCode(response,400);
    }

    @Test
    public void testCreateProductWithMissingDetails(){
        String payload = PayloadManager.getProductPayload(7);
        Response response = CommonFunctions.getRequestSpecification()
                .body(payload)
                .post(APIConstants.CREATE_PRODUCT);
        assertions.assertStatusCode(response,400);
    }

    @Test
    public void testUpdateProduct(){
        String payload = PayloadManager.getProductPayload(1);
        int productId = 2;
        Response response = CommonFunctions.getRequestSpecification()
                .pathParam("id",productId)
                .body(payload)
                .put(APIConstants.UPDATE_PRODUCT);

        assertions.assertStatusCode(response,200);
        assertions.assertResponseField(response,"title","Premium Laptop");
//        assertions.assertResponseField(response,"price",1299.99);
        double actualPrice = response.jsonPath().getDouble("price");
        double expectedPrice = 1299.99;
        boolean isPriceValid = (actualPrice >= expectedPrice - 0.01) && (actualPrice <= expectedPrice + 0.01);

        Assert.assertTrue(isPriceValid, "Expected Price:"+ expectedPrice + "Actual Price:" + actualPrice);
    }

    @Test
    public void testDeleteProduct(){
        int productId = 3;
        Response response = CommonFunctions.getRequestSpecification()
                .pathParam("id",productId)
                .delete(APIConstants.DELETE_PRODUCT);
        assertions.assertStatusCode(response,200);
//        assertions.assertResponseField(response,"message","null");

        String message = response.jsonPath().getString("message");
        if(message != null){
            Assert.assertEquals(message,"null","null");
        }else{
            System.out.println("Message is null in the response");
        }

        Response getResponse = CommonFunctions.getRequestSpecification()
                .pathParam("id",productId)
                .get(APIConstants.GET_PRODUCTS_ID);
        assertions.assertStatusCode(getResponse,404);

    }
}

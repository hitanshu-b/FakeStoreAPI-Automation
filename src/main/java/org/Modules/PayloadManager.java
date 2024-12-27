package org.Modules;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.POJO.Product;
import org.Utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class PayloadManager {
//    public static String getProductPayload(){
//        return FileUtils.readFileAsString("src/test/resources/productsPayload.json");
//    }
        private static final Gson GSON = new Gson();

        public static List<Product> getAllProductsPayload(){
            String json = FileUtils.readFileAsString("src/test/resources/productsPayload.json");
            JsonArray jsonArray = GSON.fromJson(json, JsonArray.class);
            List<Product> products = new ArrayList<>();

            for(JsonElement element : jsonArray){
                products.add(GSON.fromJson(element, Product.class));
            }
            return products;
        }

    public static String getProductPayload(int index) {
        List<Product> allProducts = getAllProductsPayload();
        return GSON.toJson(allProducts.get(index));
    }
}

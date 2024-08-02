package com.workintech.ecommerce.fetchService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.ecommerce.dto.ImageRequestDto;
import com.workintech.ecommerce.dto.ProductRequestDto;
import com.workintech.ecommerce.entity.Enum_Category;
import com.workintech.ecommerce.entity.Enum_Gender;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataFetchService {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private final ProductService productService;

    @Autowired
    public DataFetchService(ProductService productService) {
        this.productService = productService;
    }


    public String fetchData(int offset, int limit) throws UnsupportedEncodingException {
        String encodedOffset = URLEncoder.encode(String.valueOf(offset), "UTF-8");
        String encodedCount = URLEncoder.encode(String.valueOf(limit), "UTF-8");

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://workintech-fe-ecommerce.onrender.com/products?offset=" + encodedOffset + "&limit=" + encodedCount)).build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                String responseBody = response.body();
                return responseBody;
            } else {
                System.err.println("HTTP request failed with status code: " + statusCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createAndSaveProducts(int offset, int limit) {
        try {
            String jsonResponse = fetchData(offset, limit);

            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                JsonNode productsNode = jsonNode.get("products");

                if (productsNode.isArray()) {
                    for (JsonNode productNode : productsNode) {
                        int id = productNode.get("id").asInt();
                        String name = productNode.get("name").asText();
                        String description = productNode.get("description").asText();
                        double price = productNode.get("price").asDouble();
                        int stockQuantity = productNode.get("stock").asInt();
                        int categoryResponse = productNode.get("category_id").asInt();
                        JsonNode imagesNode = productNode.get("images");
                        List<ImageRequestDto> images = new ArrayList<>();
                        for (JsonNode imageNode : imagesNode) {
                            String url = imageNode.get("url").asText();
                            images.add(new ImageRequestDto(url));
                        }

                        Enum_Gender gender = categoryResponse > 0 && categoryResponse < 9 ? Enum_Gender.KADIN : categoryResponse > 8 && categoryResponse < 15 ? Enum_Gender.ERKEK : Enum_Gender.UNISEX;

                        Enum_Category category = categoryResponse == 1 || categoryResponse == 14 ? Enum_Category.TSİRT : categoryResponse == 2 || categoryResponse == 9 ? Enum_Category.AYAKKABI : categoryResponse == 3 || categoryResponse == 10 ? Enum_Category.CEKET : categoryResponse == 4 ? Enum_Category.ELBİSE : categoryResponse == 5 ? Enum_Category.ETEK : categoryResponse == 6 || categoryResponse == 11 ? Enum_Category.GÖMLEK : categoryResponse == 7 || categoryResponse == 12 ? Enum_Category.KAZAK : categoryResponse == 8 || categoryResponse == 13 ? Enum_Category.PANTOLON : Enum_Category.TSİRT;

                        ProductRequestDto productRequestDto = new ProductRequestDto(name, description, price, stockQuantity, gender, category, images);

                        productService.createProduct(productRequestDto);
                    }
                }
            } else {
                System.out.println("Data fetch failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

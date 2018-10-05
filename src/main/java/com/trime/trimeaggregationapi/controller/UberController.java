package com.trime.trimeaggregationapi.controller;

import com.trime.trimeaggregationapi.provider.UberConfigurationProvider;
import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@RestController
public class UberController {

    @Autowired
    private UberConfigurationProvider uberConfigurationProvider;

    @GetMapping("/api/uber/products")
    public ResponseEntity<List<?>> getProducts(@RequestParam("latitude") float latitude,
                                               @RequestParam("longitude") float longitude) {

        RidesService service = uberConfigurationProvider.getRidesService();
        List<Product> products = null;

        List<PriceEstimate> prices = null;
        try {
            //37.79f, -122.39f
            Response<ProductsResponse> response = service.getProducts(latitude, longitude).execute();

            products = response.body().getProducts();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/api/uber/estimates/price")
    public ResponseEntity<List<?>> getPriceEstimates(@RequestParam("startLatitude") float startLatitude,
                                                     @RequestParam("startLongitude") float startLongitude,
                                                     @RequestParam("endLatitude") float endLatitude,
                                                     @RequestParam("endLongitude") float endLongitude) {

        RidesService service = uberConfigurationProvider.getRidesService();


        List<PriceEstimate> prices = null;
        try {
            PriceEstimatesResponse body
                    = service.getPriceEstimates(startLatitude, startLongitude, endLatitude, endLongitude).execute().body();

            prices = body.getPrices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @GetMapping("/api/uber/estimates/time")
    public ResponseEntity<List<?>> getPickupTimeEstimate(@RequestParam("startLatitude") float startLatitude,
                                                         @RequestParam("startLongitude") float startLongitude,
                                                         @RequestParam("productId") String productId) {

        RidesService service = uberConfigurationProvider.getRidesService();

        List<TimeEstimate> timeEstimateList = null;
        try {
            TimeEstimatesResponse timeEstimatesResponse = service.getPickupTimeEstimate(startLatitude,
                    startLongitude, productId).execute().body();

            timeEstimateList = timeEstimatesResponse.getTimes();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(timeEstimateList, HttpStatus.OK);
    }
}

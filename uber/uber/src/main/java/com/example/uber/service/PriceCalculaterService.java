package com.example.uber.service;

import com.example.uber.Enum.CarType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PriceCalculaterService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY = "787f72854b684e3490eee7e6be8f94c7"; // Replace with your OpenCage API key
    private static final String OSRM_API_URL = "http://router.project-osrm.org/route/v1/car/%s;%s?overview=false&alternatives=false";


    public Double getPrice(String pickupLocation, String dropLocation, CarType carType) {
        String pickupCoordinates = getCoordinates(pickupLocation);
        String dropCoordinates = getCoordinates(dropLocation);

        String[] pickupCoords = pickupCoordinates.split(",");
        String[] dropCoords = dropCoordinates.split(",");

        int pricePerKm = 0;
        if (carType == CarType.SEDAN) {
            pricePerKm = 5;
        } else if (carType == CarType.SUV) {
            pricePerKm = 6;
        } else if (carType == CarType.COUPE) {
            pricePerKm = 7;
        } else if (carType == CarType.HATCHBACK) {
            pricePerKm = 3;
        } else {
            pricePerKm = 9;
        }

        double distance = getDistanceFromOSRM(pickupCoords[0], pickupCoords[1], dropCoords[0], dropCoords[1]);

        return distance * pricePerKm + 5.89;
    }



    //coordinates calculate from address
    private String getCoordinates(String location) {
//          String url= "https://api.opencagedata.com/geocode/v1/json?q="+location+"&key="+API_KEY;
        String url = String.format("https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s", location, API_KEY);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getBody() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode firstResult = rootNode.path("results").get(0);
                double lat = firstResult.path("geometry").path("lat").asDouble();
                double lon = firstResult.path("geometry").path("lng").asDouble();
                return lon + "," + lat;
            } catch (Exception e) {
                e.printStackTrace();
                return "0.0,0.0";
            }
        }
        return "0.0,0.0";
    }



    //calculate distance based on coordinates
    private double getDistanceFromOSRM(String lon1, String lat1, String lon2, String lat2) {
        String url = String.format(OSRM_API_URL, lon1 + "," + lat1, lon2 + "," + lat2);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getBody() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode routesNode = rootNode.path("routes").get(0);
                double distanceInMeters = routesNode.path("distance").asDouble();
                return distanceInMeters / 1000.0;
            } catch (Exception e) {
                e.printStackTrace();
                return 0.0;
            }
        }
        return 0.0;
}

}


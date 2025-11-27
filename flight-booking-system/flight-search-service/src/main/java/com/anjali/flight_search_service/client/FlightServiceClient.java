package com.anjali.flight_search_service.client;

import com.anjali.flight_search_service.model.FlightResponse;
import com.anjali.flight_search_service.model.FlightSearchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "flight-service", url = "localhost:8080/v1/api/flights")
public interface FlightServiceClient {

    @PostMapping("/search")
    List<FlightResponse> searchFlights(FlightSearchRequest flightSearchRequest);

}

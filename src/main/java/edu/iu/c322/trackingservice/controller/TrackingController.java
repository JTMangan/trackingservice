package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Tracking;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private final WebClient orderService;

    public TrackingController(WebClient.Builder webClientBuilder){
        orderService = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @GetMapping("/{orderId}/{itemId}")
    public Mono<Order> findByOrderIdAndItemId(@PathVariable int orderId, int itemId){
        return orderService.get().uri("/orders/order/{orderId}/{itemId}", orderId, itemId)
                .retrieve()
                .bodyToMono(Order.class);
    }

//    @PutMapping("/{id}")
//    public void update(@PathVariable int id, @RequestBody String status){
//        repository.update(id, status);
//    }
}

package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Invoice;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Tracking;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private final WebClient invoiceService;

    public TrackingController(WebClient.Builder webClientBuilder){
        invoiceService = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    @GetMapping("/{orderId}/{itemId}")
    public Map<String, String> findByOrderId(@PathVariable int orderId){
        Map<String, String> response  = new HashMap<>();
        Invoice invoice = invoiceService.get().uri("/invoices/{orderId}", orderId).retrieve().bodyToMono(Invoice.class).block();
        if(invoice == null){
            throw new IllegalStateException("Invoice not found");
        }

        System.out.println(invoice.getInvoiceItem().getStatus());
        response.put("status", invoice.getInvoiceItem().getStatus());
        response.put("date", invoice.getInvoiceItem().getOn());

        return response;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody String status){
        invoiceService.get().uri("/invoices/{id}", id, status);
    }
}

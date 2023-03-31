package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.invoicingservice.model.Invoice;
import edu.iu.c322.invoicingservice.repository.InvoiceRepository;
import edu.iu.c322.trackingservice.model.Tracking;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository repository;

    public TrackingController(TrackingRepository repository){
        this.repository = repository;
    }
    @GetMapping("/{orderId}/{itemId}")
    public Tracking findByOrderIdAndItemId(@PathVariable int orderId, @PathVariable int itemId ){
        return repository.findByOrderIdAndItemId(orderId, itemId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody String status){
        repository.update(id, status);
    }
}

package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.invoicingservice.model.Invoice;
import edu.iu.c322.trackingservice.model.Tracking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackingRepository {

    private List<Tracking> trackings = new ArrayList<>();
    public Tracking findByOrderIdAndItemId(int orderId, int itemId){
        Tracking tracking = trackings.stream().filter(x -> x.getOrderId() == orderId).findAny().orElse(null);
        if(tracking != null){
            return tracking;
        } else {
            throw new IllegalStateException("order with this id does not exist in the system");
        }
    }

    public void update(int id, Tracking newTracking){
        Tracking tracking = trackings.stream().filter(x -> x.getOrderId() == id).findAny().orElse(null);
        if(tracking != null){
            tracking.setStatus(newTracking.getStatus());
            tracking.setDate(newTracking.getDate());
        } else {
            throw new IllegalStateException("order with this id does not exist in the system");
        }
    }
}

package edu.iu.c322.trackingservice.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private int order_id;
    private int customer_id;
    private float total;
    private Address shippingAddress;
    private List<Item> items;
    private Payment payment;

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int orderId) {
        this.order_id = orderId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void refundItem(int itemId, String reason) {
        Item item = items.stream().filter(x -> x.getItemId() == itemId).findAny().orElse(null);
        if (item != null) {
            System.out.println(item.getName() + " is being refunded for $" + (item.getPrice() * item.getQuantity()));
            System.out.println("Refund reason: " + reason);
            items.remove(item);
        } else {
            throw new IllegalStateException("item id is not valid");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order_id == order.order_id && customer_id == order.customer_id && Float.compare(order.total, total) == 0 && shippingAddress.equals(order.shippingAddress) && items.equals(order.items) && payment.equals(order.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, total, shippingAddress, items, payment);
    }
}

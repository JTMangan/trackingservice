package edu.iu.c322.trackingservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class Invoice {
    private float total;

    private InvoiceItem invoiceItem;

    private Payment payment;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Float.compare(invoice.total, total) == 0  && invoiceItem.equals(invoice.invoiceItem) && payment.equals(invoice.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, invoiceItem, payment);
    }
}

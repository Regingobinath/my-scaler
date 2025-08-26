package com.learn.parkinglot.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.learn.parkinglot.models.Invoice;

public class InvoiceRepositoryImpl implements InvoiceRepository{
    private Map<Long, Invoice> invoiceStore;
    private Long autoId;
    
    public InvoiceRepositoryImpl(){
        this.invoiceStore = new HashMap<>();
        this.autoId = 0l;
    }

    @Override
    public Invoice save(Invoice invoice) {
        invoice.setId(Optional.ofNullable(invoice.getId()).orElseGet(() -> ++ autoId));
        this.invoiceStore.put(invoice.getId(), invoice);
        return invoice;
    }
}

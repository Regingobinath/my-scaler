package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Invoice;

public interface InvoiceRepository {

    Invoice save(Invoice invoice);
}

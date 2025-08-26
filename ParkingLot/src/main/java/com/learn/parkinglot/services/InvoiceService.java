package com.learn.parkinglot.services;

import com.learn.parkinglot.exceptions.InvalidGateException;
import com.learn.parkinglot.exceptions.TicketNotFoundException;
import com.learn.parkinglot.models.Invoice;

public interface InvoiceService {
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException;
}

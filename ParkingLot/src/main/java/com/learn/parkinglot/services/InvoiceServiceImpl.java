package com.learn.parkinglot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.learn.parkinglot.exceptions.InvalidGateException;
import com.learn.parkinglot.exceptions.TicketNotFoundException;
import com.learn.parkinglot.models.*;
import com.learn.parkinglot.repositories.GateRepository;
import com.learn.parkinglot.repositories.InvoiceRepository;
import com.learn.parkinglot.repositories.TicketRepository;
import com.learn.parkinglot.strategies.pricing.PricingStrategy;
import com.learn.parkinglot.strategies.pricing.PricingStrategyFactory;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketRepository ticketRepository;
    private PricingStrategyFactory pricingStrategyFactory;

    private GateRepository gateRepository;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketRepository ticketRepository, PricingStrategyFactory pricingStrategyFactory, GateRepository gateRepository, InvoiceRepository invoiceRepository) {
        this.ticketRepository = ticketRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.gateRepository = gateRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException {
        Optional<Ticket> ticketOptional = ticketRepository.getTicketById(ticketId);

        if(ticketOptional.isEmpty()){
            throw new TicketNotFoundException("Ticket not found");
        }

        Ticket ticket = ticketOptional.get();
        Optional<Gate> optionalGate = this.gateRepository.findById(gateId);
        if(optionalGate.isEmpty()){
            throw new InvalidGateException("Invalid gate id");
        }

        Date exitTime = new Date();
        PricingStrategy pricingStrategy = pricingStrategyFactory.getPricingStrategy(exitTime);
        double costIncurredForParking = pricingStrategy.calculateAmount(ticket.getEntryTime(), exitTime, ticket.getVehicle().getVehicleType());

        double costIncurredForAdditionalServices = 0;
        for(AdditionalService additionalService: ticket.getAdditionalServices()){
            costIncurredForAdditionalServices += additionalService.getCost();
        }

        double totalCost = costIncurredForParking + costIncurredForAdditionalServices;
        Gate gate = optionalGate.get();
        Invoice invoice = new Invoice();
        invoice.setTicket(ticket);
        invoice.setGate(gate);
        invoice.setExitTime(exitTime);
        invoice.setAmount(totalCost);
        invoice = this.invoiceRepository.save(invoice);
        return invoice;
    }
    
}

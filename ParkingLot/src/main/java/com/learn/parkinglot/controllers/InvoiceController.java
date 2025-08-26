package com.learn.parkinglot.controllers;

import com.learn.parkinglot.dtos.GenerateInvoiceRequestDto;
import com.learn.parkinglot.dtos.GenerateInvoiceResponseDto;
import com.learn.parkinglot.dtos.ResponseStatus;
import com.learn.parkinglot.models.Invoice;
import com.learn.parkinglot.services.InvoiceService;
public class InvoiceController {

        private InvoiceService invoiceService;

        public InvoiceController(InvoiceService invoiceService) {
            this.invoiceService = invoiceService;
        }

        public GenerateInvoiceResponseDto createInvoice(GenerateInvoiceRequestDto requestDto){
            GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();
            try {
                Invoice invoice = invoiceService.createInvoice(requestDto.getTicketId(), requestDto.getGateId());
                responseDto.setStatus(ResponseStatus.SUCCESS);
                responseDto.setInvoice(invoice);
                return responseDto;
            } catch (Exception e){
                responseDto.setStatus(ResponseStatus.FAILURE);
                return responseDto;
            }
        }
}

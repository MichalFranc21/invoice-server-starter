package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> getAll();
    InvoiceDTO getInvoiceById(long id);
    List<InvoiceDTO> getPurchesedInvoices(String identificationNumber);
    List<InvoiceDTO> getSalesInvoices(String identificationNumber);
    void deleteInvoice(long id);
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);
}

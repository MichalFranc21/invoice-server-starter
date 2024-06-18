package cz.itnetwork.service;

import cz.itnetwork.dto.InoviceStatisticsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> getAll();
    InvoiceDTO getInvoiceById(long id);
    List<InvoiceDTO> getPurchesedInvoices(String identificationNumber);
    List<InvoiceDTO> getSalesInvoices(String identificationNumber);
    void deleteInvoice(long id);
    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);
    InoviceStatisticsDTO getStatistics();
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);
}

package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addPerson(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }
    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getAll();
    }

    @GetMapping("/identification/{id}/sales")
    public List<InvoiceDTO> getInvoicesBySeller(@PathVariable("id") String identificationNumber) {
        return invoiceService.getSalesInvoices(identificationNumber);
    }
    @GetMapping("/identification/{id}/purchesed")
    public List<InvoiceDTO> getInvoicesByBuyer(@PathVariable("id") String identificationNumber) {
        return invoiceService.getPurchesedInvoices(identificationNumber);
    }
    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable long id) {
        return invoiceService.getInvoiceById(id);
    }
    @DeleteMapping("/invoices/{id}")
    public void deleteInvoice(@PathVariable long id){
        invoiceService.deleteInvoice(id);
    }
    @PutMapping("/invoices/{id}")
    public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable long id) {
        return invoiceService.editInvoice(invoiceDTO, id);
    }

}

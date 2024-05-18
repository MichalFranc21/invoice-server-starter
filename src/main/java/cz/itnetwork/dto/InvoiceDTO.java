package cz.itnetwork.dto;

import cz.itnetwork.entity.PersonEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
@Data
public class InvoiceDTO {
    private int invoiceNumber;
    private Date issued;
    private Date dueDate;
    private String product;
    private Long price;
    private int vat;
    private String note;
    private PersonEntity buyer;
    private PersonEntity seller;
}

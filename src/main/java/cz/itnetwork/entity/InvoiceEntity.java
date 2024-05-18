package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int invoiceNumber;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date dueDate;
    @Column(nullable = false)
    private String product;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private int vat;
    private String note;
    @Column(nullable = false)
    private PersonEntity buyer;
    @Column(nullable = false)
    private PersonEntity seller;

}
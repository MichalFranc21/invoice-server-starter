package cz.itnetwork.entity.filter;

import cz.itnetwork.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceFilter {
    private Long buyerID;
    private Long sellerID;
    private String product;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer limit = 10;
}

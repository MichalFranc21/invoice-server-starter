package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Eclipse Adoptium)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceEntity toEntity(InvoiceDTO source) {
        if ( source == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        if ( source.getId() != null ) {
            invoiceEntity.setId( source.getId() );
        }
        invoiceEntity.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceEntity.setIssued( source.getIssued() );
        invoiceEntity.setDueDate( source.getDueDate() );
        invoiceEntity.setProduct( source.getProduct() );
        invoiceEntity.setPrice( source.getPrice() );
        invoiceEntity.setVat( source.getVat() );
        invoiceEntity.setBuyer( personMapper.toEntity( source.getBuyer() ) );
        invoiceEntity.setSeller( personMapper.toEntity( source.getSeller() ) );
        invoiceEntity.setNote( source.getNote() );

        return invoiceEntity;
    }

    @Override
    public InvoiceDTO toDTO(InvoiceEntity source) {
        if ( source == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setId( source.getId() );
        invoiceDTO.setInvoiceNumber( source.getInvoiceNumber() );
        invoiceDTO.setIssued( source.getIssued() );
        invoiceDTO.setDueDate( source.getDueDate() );
        invoiceDTO.setProduct( source.getProduct() );
        invoiceDTO.setPrice( source.getPrice() );
        invoiceDTO.setVat( source.getVat() );
        invoiceDTO.setNote( source.getNote() );
        invoiceDTO.setBuyer( personMapper.toDTO( source.getBuyer() ) );
        invoiceDTO.setSeller( personMapper.toDTO( source.getSeller() ) );

        return invoiceDTO;
    }

    @Override
    public void editInvoiceEntity(InvoiceDTO source, InvoiceEntity target) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            target.setId( source.getId() );
        }
        target.setInvoiceNumber( source.getInvoiceNumber() );
        target.setIssued( source.getIssued() );
        target.setDueDate( source.getDueDate() );
        target.setProduct( source.getProduct() );
        target.setPrice( source.getPrice() );
        target.setVat( source.getVat() );
        target.setBuyer( personMapper.toEntity( source.getBuyer() ) );
        target.setSeller( personMapper.toEntity( source.getSeller() ) );
        target.setNote( source.getNote() );
    }
}

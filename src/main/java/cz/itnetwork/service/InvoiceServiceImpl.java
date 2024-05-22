package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        Long buyer = entity.getBuyer().getId();
        PersonEntity setBuyer = personRepository.getReferenceById(buyer);
        entity.setBuyer(setBuyer);
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getPurchesedInvoices(String identificationNumber) {
        return getAll()
                .stream()
                .filter(i -> i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                .toList();
    }

    @Override
    public List<InvoiceDTO> getSalesInvoices(String identificationNumber) {
        return getAll()
                .stream()
                .filter(i -> i.getSeller().getIdentificationNumber().equals(identificationNumber))
                .toList();
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("Invoice not found with id " + id));
        return invoiceMapper.toDTO(invoiceEntity);
    }
}

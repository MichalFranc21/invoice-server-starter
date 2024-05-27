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
import java.util.ArrayList;
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

    @Override
    public void deleteInvoice(long id) {
    invoiceRepository.deleteById(id);
    }

    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id) {
        // Nastavíme ID faktury na základě přijatého argumentu
        invoiceDTO.setId(id);

        // Získáme fakturu z databáze podle ID
        InvoiceEntity entity = invoiceRepository.getReferenceById(id);

        // Aktualizujeme údaje faktury pomocí mapperu
        invoiceMapper.editInvoiceEntity(invoiceDTO, entity);

        // Nastavení prodávajícího a kupujícího do entity faktury
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));

        // Uložíme aktualizovanou fakturu do databáze
        InvoiceEntity saved = invoiceRepository.save(entity);

        // Převedeme uloženou fakturu na DTO a vrátíme
        return invoiceMapper.toDTO(saved);
    }
}

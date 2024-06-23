package cz.itnetwork.service;

import cz.itnetwork.dto.InoviceStatisticsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.InvoiceSpecification;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getPurchesedInvoices(String identificationNumber) {
        return getAll()
                .stream()
                .filter(i -> i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getSalesInvoices(String identificationNumber) {
        return getAll()
                .stream()
                .filter(i -> i.getSeller().getIdentificationNumber().equals(identificationNumber))
                .collect(Collectors.toList());
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
        invoiceDTO.setId(id);
        InvoiceEntity entity = invoiceRepository.getReferenceById(id);
        invoiceMapper.editInvoiceEntity(invoiceDTO, entity);
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        InvoiceEntity saved = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(saved);
    }

    @Override
    public InoviceStatisticsDTO getStatistics() {
        InoviceStatisticsDTO stats = new InoviceStatisticsDTO();
        stats.setCurrentYearSum(invoiceRepository.findCurrentYearSum());
        stats.setAllTimeSum(invoiceRepository.findAllTimeSum());
        stats.setInvoicesCount(invoiceRepository.findInvoicesCount());
        return stats;
    }

    @Override
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }
}
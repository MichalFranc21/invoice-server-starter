package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaSpecificationExecutor<InvoiceEntity>, JpaRepository<InvoiceEntity, Long> {

    @Query("SELECT SUM(i.price) FROM InvoiceEntity i WHERE YEAR(i.issued) = YEAR(CURRENT_DATE)")
    Long findCurrentYearSum();

    @Query("SELECT SUM(i.price) FROM InvoiceEntity i")
    Long findAllTimeSum();

    @Query("SELECT COUNT(i) FROM InvoiceEntity i")
    Long findInvoicesCount();
}


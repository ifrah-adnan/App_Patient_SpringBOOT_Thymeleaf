package ma.adnan.repository;

import ma.adnan.entite.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContains(String key, Pageable pageable);
}

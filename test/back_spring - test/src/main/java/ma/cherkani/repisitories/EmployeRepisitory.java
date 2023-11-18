package ma.cherkani.repisitories;

import ma.cherkani.entities.Employe;
import ma.cherkani.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepisitory extends JpaRepository<Employe,Long> {
    List<Employe>  findEmployeByChefId (Employe employe);

    List<Employe> findEmployeByServiceId (Long Id);
    List<Employe> findEmployeByService (Service service);
}

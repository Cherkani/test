package ma.cherkani.repisitories;

import ma.cherkani.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepisitory extends JpaRepository<Service,Long> {
}

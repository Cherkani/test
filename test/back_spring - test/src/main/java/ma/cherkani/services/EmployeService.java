package ma.cherkani.services;

import ma.cherkani.IDao.IDao;
import ma.cherkani.entities.Employe;
import ma.cherkani.entities.Service;
import ma.cherkani.repisitories.EmployeRepisitory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class EmployeService implements IDao<Employe> {
    @Autowired
    EmployeRepisitory repisitory;

    @Override
    public Employe create(Employe o) {
        return repisitory.save(o);
    }

    @Override
    public Employe update(Employe o) {
        return repisitory.save(o);
    }

    @Override
    public Boolean delete(Employe o) {
        try {
            repisitory.delete(o);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Employe findById(Long id) {
        return repisitory.findById(id).orElse(null);
    }

    @Override
    public List<Employe> findAll() {
        return repisitory.findAll();
    }

    ///
    public List<Employe> findEmployeByChefId(Employe employe) {
        return repisitory.findEmployeByChefId(employe);
    }
    public List<Employe> findEmployeByServiceId(Long id) {
        return repisitory.findEmployeByServiceId(id);
    }

    public List<Employe> findEmployeByService(Service service) {
        return repisitory.findEmployeByService(service);
    }

}


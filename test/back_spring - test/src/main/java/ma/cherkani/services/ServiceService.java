package ma.cherkani.services;

import ma.cherkani.IDao.IDao;
import ma.cherkani.entities.Service;
import ma.cherkani.repisitories.ServiceRepisitory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceService implements IDao<Service> {
    @Autowired
    ServiceRepisitory repisitory;

    @Override
    public Service create(Service o) {
        return repisitory.save(o);
    }

    @Override
    public Service update(Service o) {
        return repisitory.save(o);
    }

    @Override
    public Boolean delete(Service o) {
        try {
            repisitory.delete(o);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Service findById(Long id) {
        return repisitory.findById(id).orElse(null);
    }

    @Override
    public List<Service> findAll() {
        return repisitory.findAll();
    }
}


package ma.cherkani.IDao;

import java.util.List;

public interface IDao<T>{
    T create (T o);
    T update (T o);
    Boolean delete (T o);
    T findById (Long id);
    List<T> findAll();
}
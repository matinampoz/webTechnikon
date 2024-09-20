package ed.webtechnikon2.repositories;

import java.util.List;

/**
 *
 * @author matin
 */
public interface Repository<T, K> {
     //Optional<T> findById(K id);

    //List<T> findAll();

    T create(T t);
    
    //T findById(K k);

    //boolean deleteById(K id);
}

package ed.webtechnikon2.repositories;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author matin
 */
public interface Repository<T, K> {
     //Optional<T> findById(K id);

    List<T> findAll();

    T create(T t);
    
    Optional<T> findById(K k);

    boolean deleteById(K id);
}

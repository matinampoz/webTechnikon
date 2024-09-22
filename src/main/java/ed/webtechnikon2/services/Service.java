package ed.webtechnikon2.services;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author matin
 */
public interface Service<T, K> {

    K create(T t) throws Exception;

    //K update(T t);
    List<T> getAll();
    T findById(K k) throws Exception;
    boolean delete(K k) throws Exception;
}

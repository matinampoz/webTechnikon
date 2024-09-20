package ed.webtechnikon2.services;

import java.util.List;

/**
 *
 * @author matin
 */
public interface Service<T, K> {

    K create(T t) throws Exception;

    //K update(T t);
    //List<T> getAll();
    //T findById(K k);
    //boolean delete(K k);
}

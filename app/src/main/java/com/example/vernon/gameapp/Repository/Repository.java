package com.example.vernon.gameapp.Repository;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public interface Repository <E, ID>{

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}

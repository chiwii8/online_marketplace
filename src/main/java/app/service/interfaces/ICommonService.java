package app.service.interfaces;


import java.util.List;

public interface ICommonService<T> {
    T create();

    T findById(Long id);

    List<T> findAll();

    void delete(T entity);

    T save(T entity);
}

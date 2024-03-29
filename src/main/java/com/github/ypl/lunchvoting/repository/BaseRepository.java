package com.github.ypl.lunchvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import static com.github.ypl.lunchvoting.util.ValidationUtil.checkExisted;
import static com.github.ypl.lunchvoting.util.ValidationUtil.checkModification;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.id=:id")
    T get(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(int id);

    default void deleteExisted(int id) {
        checkModification(delete(id), id);
    }

    default T getExisted(int id) {
        return checkExisted(get(id), id);
    }
}

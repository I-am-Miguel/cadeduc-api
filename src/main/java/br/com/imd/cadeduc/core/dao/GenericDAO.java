package br.com.imd.cadeduc.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDAO<T> extends JpaRepository<T, Long> {

}

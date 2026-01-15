package com.spring.curso.persistence.crud;

import com.spring.curso.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {



}

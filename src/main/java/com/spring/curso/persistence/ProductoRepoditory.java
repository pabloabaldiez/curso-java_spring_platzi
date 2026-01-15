package com.spring.curso.persistence;

import com.spring.curso.persistence.crud.ProductoCrudRepository;
import com.spring.curso.persistence.entity.Producto;

import java.util.List;

public class ProductoRepoditory {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

}

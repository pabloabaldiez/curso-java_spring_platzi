package com.spring.curso.persistence;

import com.spring.curso.domain.Purchase;
import com.spring.curso.domain.repository.PurchaseRepository;
import com.spring.curso.persistence.crud.CompraCrudRepository;
import com.spring.curso.persistence.entity.Compra;
import com.spring.curso.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll()) ;
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);

       // Para garantizar que la información se guarde en cascada, asegúrate de que la entidad Compra
        // conozca sus productos y viceversa. Implementa el patrón de relaciones cascade
        // y mappedBy en las entidades correspondientes.
        compra.getProductos().forEach(producto ->producto.setCompra(compra));

        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}

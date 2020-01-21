package fr.miage.samba.backend.services;

import fr.miage.samba.backend.model.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAll();
}

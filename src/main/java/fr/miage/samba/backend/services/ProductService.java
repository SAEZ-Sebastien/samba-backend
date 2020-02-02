package fr.miage.samba.backend.services;

import fr.miage.samba.backend.model.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductDto> getAll();
    public Optional<ProductDto> getDetailsOfProduitById(String id);
    public ProductDto addProduct(ProductDto productDto);
    public void removeAll();
    public void remove(String id);
}

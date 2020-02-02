package fr.miage.samba.backend.services;

import fr.miage.samba.backend.dao.ProductDao;
import fr.miage.samba.backend.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductDto> getAll() {

        return productDao.findAll();
    }

    @Override
    public Optional<ProductDto> getDetailsOfProduitById(String id) {
        return this.productDao.findById(id);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productDao.save(productDto);
    }

    @Override
    public void removeAll() {
        productDao.deleteAll();
    }

    @Override
    public void remove(String id) {
        productDao.deleteById(id);
    }

}

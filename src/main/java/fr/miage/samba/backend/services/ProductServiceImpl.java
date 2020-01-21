package fr.miage.samba.backend.services;

import fr.miage.samba.backend.dao.ProductDao;
import fr.miage.samba.backend.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductDto> getAll() {

        return productDao.findAll();
    }
}

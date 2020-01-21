package fr.miage.samba.backend.web.controller;

import fr.miage.samba.backend.model.ProductDto;
import fr.miage.samba.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value ="/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //Get all
    @GetMapping()
    public List<ProductDto> getAllProducts(){
        return this.productService.getAll();
    }

    //Get Specified
    @GetMapping("/{id}")
    public ProductDto getProductDetails(@PathVariable Long id){
        return null;
    }

    //Get Sorted

    //Update product


    //Add product


    //delete product
}

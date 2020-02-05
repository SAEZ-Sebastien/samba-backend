package fr.miage.samba.backend.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import fr.miage.samba.backend.model.ProductDto;
import fr.miage.samba.backend.services.ProductService;
import fr.miage.samba.backend.web.exceptions.EmptyField;
import fr.miage.samba.backend.web.exceptions.IncorrectProductPrice;
import fr.miage.samba.backend.web.exceptions.ProductNotFound;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static fr.miage.samba.backend.security.SecurityConstants.HEADER_STRING;

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
    public ProductDto getProductDetails(HttpServletRequest request, @PathVariable String id){
        Optional<ProductDto> requestResult = productService.getDetailsOfProduitById(id);

        if(!requestResult.isPresent()){
            throw new ProductNotFound();
        }

        return productService.getDetailsOfProduitById(id).get();
    }

    //Get Specified
    @DeleteMapping("/{id}")
    public void removeProductById(@PathVariable String id){
        this.productService.remove(id);
    }

    //Ajouter un produit
    @PostMapping()
    public Object ajouterProduit( @Valid @RequestBody ProductDto product) {

        if(product.getDescription().isEmpty() || product.getTitle().isEmpty() || product.getSellerId().isEmpty()){
            throw new EmptyField();
        }
        if(product.getPrix() <= 0){
            throw new IncorrectProductPrice();
        }
        product.setId(ObjectId.get());
        ProductDto productAdded =  this.productService.addProduct(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();


        return ResponseEntity.created(location).body(product);
    }
}

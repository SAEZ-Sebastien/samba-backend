package fr.miage.samba.backend.web.controller;

import fr.miage.samba.backend.Helper.NotationHelper;
import fr.miage.samba.backend.model.ProductDto;
import fr.miage.samba.backend.Helper.TokenHelper;
import fr.miage.samba.backend.services.ProductService;
import fr.miage.samba.backend.services.UserService;
import fr.miage.samba.backend.web.exceptions.EmptyField;
import fr.miage.samba.backend.web.exceptions.IncorrectProductPrice;
import fr.miage.samba.backend.web.exceptions.ProductNotFound;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping(value ="/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

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

        return requestResult.get();
    }

    //Get Specified
    @DeleteMapping("/{id}")
    public void removeProductById(HttpServletRequest request, @PathVariable String id){

        Optional<ProductDto> requestResult = productService.getDetailsOfProduitById(id);

        if(!requestResult.isPresent()){
            throw new ProductNotFound();
        }

        String userId = TokenHelper.extractSubjectOf(request);
        String sellerId = requestResult.get().getSellerId();

        if(sellerId != null && !sellerId.trim().isEmpty() && sellerId.equals(userId)){
            this.productService.remove(id);
        }else{
            throw new AccessDeniedException("You aren't allow to delete this element");
        }
    }

    //Ajouter un produit
    @PostMapping()
    public Object ajouterProduit( HttpServletRequest request, @Valid @RequestBody ProductDto product) {

        if(product.getTitle().isEmpty()
                || product.getProductSpecifications() == null
                || product.getProductSpecifications().getScreenState() == null
                || product.getProductSpecifications().getShellState() == null
                || product.getProductSpecifications().getButtonsSpecifications() == null
                || product.getProductSpecifications().getCameraSpecifications() == null){
            throw new EmptyField();
        }
        if(product.getPrice() <= 0){
            throw new IncorrectProductPrice();
        }
        product.setId(ObjectId.get());

        String userId = TokenHelper.extractSubjectOf(request);

        if(userId.trim().isEmpty()){
            throw new EmptyField();
        }

        product.setSellerId(userId);
        product.setScore(NotationHelper.getGlobalScoreOf(product.getProductSpecifications()));

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

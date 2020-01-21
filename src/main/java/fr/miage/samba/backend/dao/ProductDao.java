package fr.miage.samba.backend.dao;

import fr.miage.samba.backend.model.ProductDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductDao extends MongoRepository<ProductDto, String>{
}

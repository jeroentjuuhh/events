package ch.wisv.events.service;

import ch.wisv.events.data.model.product.Product;
import ch.wisv.events.data.request.product.ProductRequest;

import java.util.List;

/**
 * Created by sven on 14/10/2016.
 */
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductByKey(String key);

    /**
     * Update Product using a ProductRequest
     *
     * @param productRequest ProductRequest containing the new product information
     */
    void updateProduct(ProductRequest productRequest);
}

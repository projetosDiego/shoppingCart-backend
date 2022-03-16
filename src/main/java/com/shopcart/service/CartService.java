package com.shopcart.service;

import com.shopcart.dto.CartItemsRequestDTO;
import com.shopcart.dto.CartItemsResponseDTO;
import com.shopcart.dto.ProductDTO;
import com.shopcart.entity.Cart;
import com.shopcart.entity.Product;
import com.shopcart.exception.ItemAlreadyAdded;
import com.shopcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    private CartService(final CartRepository cartRepository,
                        final CustomerService customerService,
                        final ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public CartItemsResponseDTO addItems(CartItemsRequestDTO item){
        Cart cartDb = cartRepository.getCartByCustomerIdAndProductId(item.getCustomerId(), item.getProductId());
        if (cartDb != null) {
            throw new ItemAlreadyAdded("This item was already added to the cart.");
        } else {
            Cart cart = new Cart();
            cart.setCustomer(customerService.findById(item.getCustomerId()).get());
            cart.setProduct(productService.getById(item.getProductId()).get());
            cart.setQuantity(item.getQuantity());
            cart.setTotalPrice(cart.getProduct().getPrice() * item.getQuantity());
            cartRepository.saveAndFlush(cart);
        }
        return createItemsResponseDTO(item.getCustomerId());
    }

    public CartItemsResponseDTO updateItemQuantity(Long customerId,
                                                   Long productId,
                                                   Integer quantity) {
        if (quantity.equals(0)) {
            throw new ItemAlreadyAdded("Item quantity should be greater than 0.");
        } else {
            Cart cartDb = cartRepository.getCartByCustomerIdAndProductId(customerId, productId);
            Product productDb = productService.getById(productId).get();

            cartDb.setQuantity(quantity);
            cartDb.setTotalPrice(productDb.getPrice()*quantity);

            cartRepository.saveAndFlush(cartDb);
        }
        return createItemsResponseDTO(customerId);
    }

    public CartItemsResponseDTO deleteItem(Long customerId, Long productId) {
        Cart cart = cartRepository.getCartByCustomerIdAndProductId(customerId, productId);
        cartRepository.deleteById(cart.getId());
        return createItemsResponseDTO(customerId);
    }

    private CartItemsResponseDTO createItemsResponseDTO(Long customerId){
        CartItemsResponseDTO response = new CartItemsResponseDTO();
        List<Cart> itemsDb = cartRepository.findAllByCustomerId(customerId);
        List<ProductDTO> products = new ArrayList<>();

        response.setCustomerId(customerId);
        for (Cart cartItem : itemsDb) {
            ProductDTO product = new ProductDTO();
            product.setId(cartItem.getProduct().getId());
            product.setTitle(cartItem.getProduct().getTitle());
            product.setDescription(cartItem.getProduct().getDescription());
            product.setCategory(cartItem.getProduct().getCategory());
            products.add(product);
        }
        response.setProducts(products);
        return response;
    }
}
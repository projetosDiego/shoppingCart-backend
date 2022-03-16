package com.shopcart.controll;

import com.shopcart.dto.CartItemsRequestDTO;
import com.shopcart.dto.CartItemsResponseDTO;
import com.shopcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartItemsResponseDTO> addItem(@RequestBody final CartItemsRequestDTO items) {
        return new ResponseEntity<>(cartService.addItems(items), HttpStatus.CREATED);
    }

    @PatchMapping(value = "{" + "customerId" + "}/{" + "productId" + "}/{" + "quantity" + "}")
    public ResponseEntity<CartItemsResponseDTO> updateItemQuantity(@PathVariable("customerId") final Long customerId,
                                                                   @PathVariable("productId") final Long productId,
                                                                   @PathVariable("quantity") final Integer quantity) {
        return new ResponseEntity<>(cartService.updateItemQuantity(customerId, productId, quantity), HttpStatus.OK);
    }

    @DeleteMapping(value = "{" + "customerId" + "}/{" + "productId" + "}")
    public ResponseEntity<CartItemsResponseDTO> deleteItem(@PathVariable("customerId") final Long customerId,
                                                           @PathVariable("productId") final Long productId) {
        return new ResponseEntity<>(cartService.deleteItem(customerId, productId), HttpStatus.OK);
    }
}
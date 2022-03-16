package com.shopcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartItemsResponseDTO {

    @JsonProperty(value = "customerId")
    private Long customerId;

    @JsonProperty(value = "products")
    private List<ProductDTO> products;
}
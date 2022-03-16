package com.shopcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemsRequestDTO {

    @JsonProperty(value = "customerId")
    private Long customerId;

    @JsonProperty(value = "productId")
    private Long productId;

    @JsonProperty(value = "quantity")
    private Integer quantity;
}
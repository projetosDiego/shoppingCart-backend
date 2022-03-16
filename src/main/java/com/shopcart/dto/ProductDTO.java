package com.shopcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "category")
    private String category;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "image")
    private String image;

    @JsonProperty(value = "price")
    private Double price;

    @JsonProperty(value = "title")
    private String title;
}
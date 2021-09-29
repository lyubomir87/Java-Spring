package com.softuni.springdataautomapping.domains.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thumbnail;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String title, BigDecimal price, Double size, String trailer, String thumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }
    @Pattern(regexp = "[A-Z]{1}[a-zA-z0-9.,!\\-_@'\\s]{3,100}"
            ,message = "Title should be between 3 and 100 letters long and starts with upper case!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
@DecimalMin(value = "0", message = "Price can not be negative!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
@Min(value = 0, message = "Size can not be negative!")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
    @Pattern(regexp = "[a-zA-Z0-9]{11}"
            ,message = "Invalid YouTube trailer!")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    @Pattern(regexp = "^https://.+|http://.+$",message = "Web address not valid!")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
@Length(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
@Past
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}

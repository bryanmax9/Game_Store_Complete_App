package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="tshirt")
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tshirt_id")
    private int tShirtId;

    @NotEmpty(message = "You must supply a size.")
    private String size;

    @NotEmpty(message = "You must supply a YouTube Video ID")
    @Column(name = "youtube_id")
    private String youtubeId;

    @NotEmpty(message = "You must supply a color.")
    private String color;

    @NotEmpty(message = "You must supply a description.")
    private String description;

    @NotNull(message = "You must supply a price.")
    private BigDecimal price;

    @Min(value = 1, message = "You must supply a Quantity.")
    private int quantity;

    public Tshirt(){

    }

    public Tshirt(int tShirtId, String size, String youtubeId, String color, String description, BigDecimal price, int quantity) {
        this.tShirtId = tShirtId;
        this.size = size;
        this.youtubeId = youtubeId;
        this.color = color;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tshirt)) return false;
        Tshirt tshirt = (Tshirt) o;
        return gettShirtId() == tshirt.gettShirtId() && getQuantity() == tshirt.getQuantity() && Objects.equals(getSize(), tshirt.getSize()) && Objects.equals(getYoutubeId(), tshirt.getYoutubeId()) && Objects.equals(getColor(), tshirt.getColor()) && Objects.equals(getDescription(), tshirt.getDescription()) && Objects.equals(getPrice(), tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gettShirtId(), getSize(), getYoutubeId(), getColor(), getDescription(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "tShirtId=" + tShirtId +
                ", size='" + size + '\'' +
                ", youtubeId='" + youtubeId + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "image_url")
    private String imageUrl;

    @NotEmpty(message = "You must supply a YouTube Video ID")
    @Column(name = "youtube_id")
    private String youtubeId;

    @NotEmpty(message = "You must supply a title.")
    private String title;

    @NotEmpty(message = "You must supply a esrb Rating.")
    @Column(name = "esrb_rating")
    private String esrbRating;

    @NotEmpty(message = "You must supply a description.")
    private String  description;

    @NotNull(message = "You must supply a price.")
    private BigDecimal price;

    @NotEmpty(message = "You must supply a studio.")
    private String studio;

    @Min(value = 1, message = "You must supply a Quantity.")
    private Integer quantity;


    public Game(){

    }

    public Game(Integer gameId, String imageUrl, String youtubeId, String title, String esrbRating, String description, BigDecimal price, String studio, Integer quantity) {
        this.gameId = gameId;
        this.imageUrl = imageUrl;
        this.youtubeId = youtubeId;
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.quantity = quantity;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(getGameId(), game.getGameId()) && Objects.equals(getImageUrl(), game.getImageUrl()) && Objects.equals(getYoutubeId(), game.getYoutubeId()) && Objects.equals(getTitle(), game.getTitle()) && Objects.equals(getEsrbRating(), game.getEsrbRating()) && Objects.equals(getDescription(), game.getDescription()) && Objects.equals(getPrice(), game.getPrice()) && Objects.equals(getStudio(), game.getStudio()) && Objects.equals(getQuantity(), game.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getImageUrl(), getYoutubeId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", imageUrl='" + imageUrl + '\'' +
                ", youtubeId='" + youtubeId + '\'' +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

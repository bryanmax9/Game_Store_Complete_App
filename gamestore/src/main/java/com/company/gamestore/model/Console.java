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
@Table(name="console")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id")
    private int consoleId;

    @NotEmpty(message = "You must supply a Model")
    private String model;

    @NotEmpty(message = "You must supply a YouTube Video ID")
    @Column(name = "youtube_id")
    private String youtubeId;

    @NotEmpty(message = "You must supply a manufacturer")
    private String manufacturer;

    @Column(name = "image_url")
    private String imageUrl;

    @NotEmpty(message = "You must supply a Memory Amount")
    @Column(name = "memory_amount")
    private String memoryAmount;

    @NotEmpty(message = "You must supply a Processor")
    private String processor;

    @NotNull(message = "You must supply a Price")
    private BigDecimal price;

    @Min(value = 1, message = "You must supply a Quantity.")
    private int quantity;

    public Console(){

    }

    public Console(int consoleId, String model, String youtubeId, String manufacturer, String imageUrl, String memoryAmount, String processor, BigDecimal price, int quantity) {
        this.consoleId = consoleId;
        this.model = model;
        this.youtubeId = youtubeId;
        this.manufacturer = manufacturer;
        this.imageUrl = imageUrl;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
        this.price = price;
        this.quantity = quantity;
    }

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
        if (!(o instanceof Console)) return false;
        Console console = (Console) o;
        return getConsoleId() == console.getConsoleId() && getQuantity() == console.getQuantity() && Objects.equals(getModel(), console.getModel()) && Objects.equals(getYoutubeId(), console.getYoutubeId()) && Objects.equals(getManufacturer(), console.getManufacturer()) && Objects.equals(getImageUrl(), console.getImageUrl()) && Objects.equals(getMemoryAmount(), console.getMemoryAmount()) && Objects.equals(getProcessor(), console.getProcessor()) && Objects.equals(getPrice(), console.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getYoutubeId(), getManufacturer(), getImageUrl(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Console{" +
                "consoleId=" + consoleId +
                ", model='" + model + '\'' +
                ", youtubeId='" + youtubeId + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

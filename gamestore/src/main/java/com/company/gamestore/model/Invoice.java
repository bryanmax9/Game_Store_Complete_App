package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer id;

    @Column(name = "imageUrl")
    private String imageUrl;

    @NotEmpty(message = "You must supply a Name.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "You must supply a Street.")
    @Column(name = "street")
    private String street;

    @NotEmpty(message = "You must supply a City.")
    @Column(name = "city")
    private String city;

    @NotEmpty(message = "You must supply a State.")
    @Column(name = "state")
    private String state;

    @NotEmpty(message = "You must supply a ZIP Code.")
    @Column(name = "zipcode")
    private String zipcode;

    @NotEmpty(message = "You must supply an Item Type.")
    @Column(name = "item_type")
    private String itemType;

    @NotNull(message = "You must supply an Item ID.")
    @Column(name = "item_id")
    private Integer itemId;


    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @NotNull(message = "You must supply a Quantity.")
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "processing_fee")
    private BigDecimal processingFee;

    @Column(name = "total")
    private BigDecimal total;

    public Invoice() {}

    public Invoice(Integer id, String imageUrl, String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, BigDecimal unitPrice, int quantity, BigDecimal subtotal, BigDecimal tax, BigDecimal processingFee, BigDecimal total) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return getQuantity() == invoice.getQuantity() && Objects.equals(getId(), invoice.getId()) && Objects.equals(getImageUrl(), invoice.getImageUrl()) && Objects.equals(getName(), invoice.getName()) && Objects.equals(getStreet(), invoice.getStreet()) && Objects.equals(getCity(), invoice.getCity()) && Objects.equals(getState(), invoice.getState()) && Objects.equals(getZipcode(), invoice.getZipcode()) && Objects.equals(getItemType(), invoice.getItemType()) && Objects.equals(getItemId(), invoice.getItemId()) && Objects.equals(getUnitPrice(), invoice.getUnitPrice()) && Objects.equals(getSubtotal(), invoice.getSubtotal()) && Objects.equals(getTax(), invoice.getTax()) && Objects.equals(getProcessingFee(), invoice.getProcessingFee()) && Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImageUrl(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}

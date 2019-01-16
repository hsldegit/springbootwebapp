package guru.springframework.domain.entity;

import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {


    private static final long serialVersionUID = 4924970590361531477L;


    private Integer id;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private String productId;

    @Version
    private  String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
package br.com.fundatec.CosItems.model;

import java.math.BigDecimal;

public class CartItemModel {
    private ProductModel productModel;
    private BigDecimal individualQuantity;

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public BigDecimal getIndividualQuantity() {
        return individualQuantity;
    }

    public void setIndividualQuantity(BigDecimal individualQuantity) {
        if (individualQuantity.compareTo(this.productModel.getQuantity()) > 0) {
            throw new IllegalArgumentException("A quantidade do produto no carrinho não pode exceder a quantidade disponível do produto");
        }
        this.individualQuantity = individualQuantity;
    }
    

    
}

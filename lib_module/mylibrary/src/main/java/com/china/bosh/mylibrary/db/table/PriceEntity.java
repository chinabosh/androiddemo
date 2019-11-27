package com.china.bosh.mylibrary.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author lzq
 * @date 2018/11/22
 */

@Entity
public class PriceEntity {
    @Id
    private Long id;
    private String itemType;
    private String itemTypeName;
    @Unique
    private String itemCode;
    private String itemName;
    private double unitPrice;
    private String unit;
    private String unitName;
    private double quantity = 0;
    private double amount = 0;
    @Generated(hash = 481354360)
    public PriceEntity(Long id, String itemType, String itemTypeName,
                       String itemCode, String itemName, double unitPrice, String unit,
                       String unitName, double quantity, double amount) {
        this.id = id;
        this.itemType = itemType;
        this.itemTypeName = itemTypeName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.unitName = unitName;
        this.quantity = quantity;
        this.amount = amount;
    }
    @Generated(hash = 1519330722)
    public PriceEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemType() {
        return this.itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getItemTypeName() {
        return this.itemTypeName;
    }
    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public double getUnitPrice() {
        return this.unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public double getQuantity() {
        return this.quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public double getAmount() {
        return this.amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}

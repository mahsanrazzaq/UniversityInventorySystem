package models;

import java.util.Objects;

//Use 'extends' to inherit from InventoryItem
public class Equipment extends InventoryItem {
 // Specific attributes unique to Equipment
 private String brand;
 private String assetId;
 private int warrantyMonths;

 /**
  * Constructor: Initializes attributes from both InventoryItem and Equipment.
  * Note: 'id', 'name', and 'isAvailable' are passed to the parent (super).
  */
 public Equipment(String id, String name, boolean isAvailable, String brand, String assetId, int warrantyMonths) {
     super(id, name, isAvailable); // Calls InventoryItem constructor
     this.brand = brand;
     this.assetId = assetId;
     this.warrantyMonths = warrantyMonths;
 }

 /**
  * Implementation of the abstract method from InventoryItem.
  */
 @Override
 public String getItemType() {
     return "Equipment";
 }

 // Getters and Setters for Equipment-specific fields
 public String getBrand() { return brand; }
 public void setBrand(String brand) { this.brand = brand; }

 public String getAssetId() { return assetId; }
 public void setAssetId(String assetId) { this.assetId = assetId; }

 public int getWarrantyMonths() { return warrantyMonths; }
 public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

 /**
  * Overridden toString() including parent and child attributes.
  */
 @Override
 public String toString() {
     return super.toString() + ", Category: " + getItemType() + 
            ", Brand: " + brand + 
            ", Warranty: " + warrantyMonths + " months";
 }

 /**
  * Equals method using assetId for comparison as requested.
  */
 @Override
 public boolean equals(Object obj) {
     if (this == obj) return true;
     if (obj == null || getClass() != obj.getClass()) return false;
     Equipment other = (Equipment) obj;
     return Objects.equals(assetId, other.assetId);
 }

 @Override
 public int hashCode() {
     return Objects.hash(assetId);
 }
}
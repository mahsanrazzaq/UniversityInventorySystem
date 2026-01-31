package models;

//Extending the abstract base class
public class Furniture extends InventoryItem {
 // Specific attributes for Furniture
 private String roomNumber;
 private String material;

 /**
  * Constructor: Initializes attributes from InventoryItem and Furniture.
  */
 public Furniture(String id, String name, boolean isAvailable, String roomNumber, String material) {
     super(id, name, isAvailable); // Pass shared data to the InventoryItem constructor
     this.roomNumber = roomNumber;
     this.material = material;
 }

 /**
  * Required implementation of the abstract method from InventoryItem.
  */
 @Override
 public String getItemType() {
     return "Furniture";
 }

 // Getters and Setters
 public String getRoomNumber() { return roomNumber; }
 public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

 public String getMaterial() { return material; }
 public void setMaterial(String material) { this.material = material; }

 /**
  * Overridden toString() to include inherited information and furniture details.
  */
 @Override
 public String toString() {
     // super.toString() handles the ID, Name, and Availability
     return super.toString() + ", Category: " + getItemType() + 
            ", Room: " + roomNumber + ", Material: " + material + "";
 }
}
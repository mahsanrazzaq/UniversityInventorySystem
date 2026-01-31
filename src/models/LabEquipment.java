package models;

//Extending the abstract base class
public class LabEquipment extends InventoryItem {
 // Specific attributes for Lab Equipment
 private String labName;
 private String calibrationDate;

 /**
  * Constructor: Initializes common attributes via super and lab-specific attributes locally.
  */
 public LabEquipment(String id, String name, boolean isAvailable, String labName, String calibrationDate) {
     super(id, name, isAvailable); // Pass shared data to the InventoryItem constructor
     this.labName = labName;
     this.calibrationDate = calibrationDate;
 }

 /**
  * Required implementation of the abstract method from InventoryItem.
  */
 @Override
 public String getItemType() {
     return "LabEquipment";
 }

 // Getters and Setters
 public String getLabName() { return labName; }
 public void setLabName(String labName) { this.labName = labName; }

 public String getCalibrationDate() { return calibrationDate; }
 public void setCalibrationDate(String calibrationDate) { this.calibrationDate = calibrationDate; }

 /**
  * Overridden toString() to provide a complete view of the lab equipment.
  */
 @Override
 public String toString() {
     // Combines base item info with specific lab details
     return super.toString() + ", Category: " + getItemType() + 
            ", Lab: " + labName + ", Last Calibration: " + calibrationDate + "";
 }
}
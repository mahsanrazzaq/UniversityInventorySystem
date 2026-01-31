package models;

public abstract class InventoryItem {
    private String id;
    private String name;
    private boolean isAvailable;

    // Constructor to initialize attributes
    public InventoryItem(String id, String name, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    // Abstract Method: Subclasses MUST provide their own implementation
    public abstract String getItemType();

    // Concrete Methods: Basic Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    // Concrete Method: toString() for displaying information
    @Override
    public String toString() {
        return "Asset Id: " + id + ", Name: " + name + ", Available: " + isAvailable + "";
    }
}
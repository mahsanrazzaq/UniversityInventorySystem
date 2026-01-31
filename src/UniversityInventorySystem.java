import models.*;
import managers.*;
import exceptions.*;
import java.util.*;

public class UniversityInventorySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static InventoryManager manager = new InventoryManager();
    private static InventoryReports reporter = new InventoryReports();
    
    // Lists to store data that we put in the system in runtime, same act like "database" but temporary
    private static ArrayList<InventoryItem> inventory = new ArrayList<>();
    private static ArrayList<StaffMember> staffList = new ArrayList<>();

    public static void main(String[] args) {
        int choice = 0;

        System.out.println("Welcome to the University Inventory System");

        // Menu handling using a while loop
        while (choice != 8) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add New Equipment");
            System.out.println("2. Register New Staff");
            System.out.println("3. Assign Equipment to Staff");
            System.out.println("4. Return Equipment");
            System.out.println("5. Search Inventory");
            System.out.println("6. Generate Reports");
            System.out.println("7. Calculate Maintenance Fee");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: addEquipment(); break;
                    case 2: registerStaff(); break;
                    case 3: assignProcess(); break;
                    case 4: returnProcess(); break;
                    case 5: searchProcess(); break;
                    case 6: reportProcess(); break;
                    case 7: calculateFeeProcess(); break;
                    case 8: System.out.println("Exiting system..."); break;
                    default: System.err.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Please enter a valid number.");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void addEquipment() {
        int subChoice = 0;
        boolean validSelection = false;

        // 1. Type Selection Loop
        do {
            System.out.println("\n--- Select Item Type to Add ---");
            System.out.println("1. Standard Equipment");
            System.out.println("2. Furniture");
            System.out.println("3. Lab Equipment");
            System.out.print("Choice: ");
            try {
                subChoice = Integer.parseInt(scanner.nextLine());
                
                if (subChoice >= 1 && subChoice <= 3) {
                    validSelection = true;
                } else {
                    System.err.println(">> Error: Invalid selection. Please choose 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.err.println(">> Error: Please enter a valid number.");
            }
        } while (!validSelection);

        // 2. Data Collection
        System.out.print("Enter Item Name: "); 
        String name = scanner.nextLine();
        System.out.print("Enter Item ID: "); 
        String id = scanner.nextLine();

        InventoryItem newItem = null;

        try {
            switch (subChoice) {
                case 1:
                    System.out.print("Enter Brand: "); 
                    String brand = scanner.nextLine();
                    System.out.print("Warranty Months: "); 
                    int warranty = Integer.parseInt(scanner.nextLine());
                    // Create temp object to check against inventory
                    newItem = new Equipment(id, name, true, brand, "ASSET-" + id, warranty);
                    break;
                case 2:
                    System.out.print("Enter Room Number: "); 
                    String room = scanner.nextLine();
                    System.out.print("Enter Material: "); 
                    String material = scanner.nextLine();
                    newItem = new Furniture(id, name, true, room, material);
                    break;
                case 3:
                    System.out.print("Enter Lab Name: "); 
                    String lab = scanner.nextLine();
                    System.out.print("Calibration Date: "); 
                    String date = scanner.nextLine();
                    newItem = new LabEquipment(id, name, true, lab, date);
                    break;
            }

            // 3. Duplicate Check using overridden equals() via contains()
            if (inventory.contains(newItem)) {
                // Throw a custom or standard exception if ID already exists
                throw new InventoryException("Duplicate Error: An item with ID '" + id + "' already exists in the system.");
            }

            inventory.add(newItem);
            System.out.println("Item successfully added.");

        } catch (InventoryException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    
    private static void registerStaff() {
        System.out.print("Enter Staff Name: "); 
        String name = scanner.nextLine();
        
        System.out.print("Enter Staff ID (int): "); 
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Email: "); 
        String email = scanner.nextLine();
        
        staffList.add(new StaffMember(id, name, email));
        System.out.println("Staff member registered.");
    }

    private static void assignProcess() {
        try {
            System.out.print("Enter Staff ID: "); 
            int sId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Equipment ID: "); 
            String eId = scanner.nextLine();

            // Find staff and equipment
            StaffMember staff = null;

            for (StaffMember s : staffList) {
                if (s.getStaffId() == sId) {
                    staff = s;
                    break; // stop once found
                }
            }

            InventoryItem item = null;

            for (InventoryItem i : inventory) {
                if (i.getId().equals(eId)) {
                    item = i;
                    break; // stop once found
                }
            }
            
           
            if (staff == null) throw new StaffMemberNotFoundException("Staff ID not found.");
            if (!(item instanceof Equipment)) throw new InventoryException("Item is not assignable equipment.");

            manager.assignEquipment(staff, (Equipment) item);

        } catch (InventoryException e) {
            // Graceful handling of custom exceptions
            System.err.println("Operation Failed: " + e.getMessage());
        }
    }

    private static void returnProcess() {
        
    	System.out.print("Enter Staff ID: "); 
        int sId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Asset ID: "); 
        String aId = scanner.nextLine();
        
        // Find staff and equipment
        StaffMember staff = null;

        for (StaffMember s : staffList) {
            if (s.getStaffId() == sId) {
                staff = s;
                break; // stop once found
            }
        }
        
        if (staff != null) {
            manager.returnEquipment(staff, aId);
            // Polymorphism: Finding the item in the base list to reset availability
            for (InventoryItem i : inventory) {
                if (i instanceof Equipment) {
                    Equipment eq = (Equipment) i; 
                    if (eq.getId().equals(aId)) {
                        i.setAvailable(true);
                    }
                }
            }
        }
    }

    private static void searchProcess() {
        System.out.print("Enter equipment name to search: ");
        String name = scanner.nextLine();
        manager.searchEquipment(name, inventory); // Demonstrates overloaded method
    }

    private static void reportProcess() {
        reporter.generateInventoryReport(inventory);
        reporter.findExpiredWarranties(inventory);
    }
    
    private static void calculateFeeProcess() {
        try {
            System.out.print("Enter Equipment ID: ");
            String eId = scanner.nextLine();
            
            InventoryItem item = null;

            for (InventoryItem i : inventory) {
                if (i.getId().equals(eId)) {
                    item = i;
                    break;
                }
            }

            if (item instanceof Equipment) {
                System.out.print("Enter Days Overdue: ");
                int days = Integer.parseInt(scanner.nextLine());
                
                // Call the method from the reporter instance
                double fee = manager.calculateMaintenanceFee((Equipment) item, days);
                
                System.out.println("The maintenance fee for " + item.getName() + " is: $" + fee);
            } else {
                System.err.println("Error: Item not found or is not a type of Equipment.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid number for days overdue.");
        }
    }

}

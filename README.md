# UniversityInventorySystem

This java application is designed to track campus assets standard structure.I have separated the project into models for data, managers for logic, and exceptions for custom error handling.

**Inheritance:** At the core is the InventoryItem Abstract Class. This acts as a blueprint. You will find out that Equipment, Furniture, and LabEquipment all extend this class, demonstrating inheritance.

**Polymorphism:** The system is based on a single Arraylist<InventoryItem> to store all objects. This demonstrates polymorphism, as the system can process different subclasses through a single list using overridden methods like <getItemType()>.

**Exceptions:** To ensure the system does not crash, the system is based on Custum Exceptions like AssignmentLimitExceededException. These are caught in try-catch blocks to provide user-friendly feedback instead of system errors.

**The system will perform the following features given below**
1- Add Equipment
2- Register a staff member
3- Assigned a new Equipment to staff member
4- Return an Inventory Item
5- Search for inventory Item
6- Generate Reports
7- Calculate Maintainance Fee

package exceptions;

//Thrown when a staff ID is not found in the system
public class StaffMemberNotFoundException extends InventoryException {
	private static final long serialVersionUID = 1L;
	
	public StaffMemberNotFoundException(String message) {
	   super(message);
}
}

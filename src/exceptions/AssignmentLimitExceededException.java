package exceptions;

//Thrown when a staff member already has 5 items
public class AssignmentLimitExceededException extends InventoryException {
	 private static final long serialVersionUID = 1L;

	 public AssignmentLimitExceededException(String message) {
	     super(message);
	 }
	}
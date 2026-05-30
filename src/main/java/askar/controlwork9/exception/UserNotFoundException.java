package askar.controlwork9.exception;

public class UserNotFoundException extends NotFoundEntryException {
    public UserNotFoundException() {
        super("User not found: ");
    }
}

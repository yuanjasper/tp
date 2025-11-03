package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a BillingContact in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidContact(String)}
 */
public class BillingContact {

    public static final String MESSAGE_CONSTRAINTS = "Billing Contacts are phone numbers, it should only contain "
            + "numbers, and must be exactly 8 digits long." + " It should also start with either 6, 8 or 9.";
    public static final String VALIDATION_REGEX = "^\\d{8}$";

    public final String contact;

    /**
     * Constructs a {@code BillingContact}.
     *
     * @param contact A valid tag number.
     */
    public BillingContact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidContact(contact), MESSAGE_CONSTRAINTS);
        this.contact = contact;
    }

    /**
     * Returns true if a given string is a valid contact number.
     */
    public static boolean isValidContact(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return (test.startsWith("6") || test.startsWith("8") || test.startsWith("9"));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BillingContact)) {
            return false;
        }

        BillingContact otherContact = (BillingContact) other;
        return contact.equals(otherContact.contact);
    }

    @Override
    public int hashCode() {
        return contact.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return contact;
    }

}

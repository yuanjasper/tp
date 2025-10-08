package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a BillingContact in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidContact(String)}
 */
public class BillingContact {

    public static final String MESSAGE_CONSTRAINTS = "Billing Contacts are phone numbers, at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";

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
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
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
        return '[' + contact + ']';
    }

}

package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BillingContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BillingContact(null));
    }

    @Test
    public void constructor_invalidBillingContact_throwsIllegalArgumentException() {
        String invalidBillingContact = "";
        assertThrows(IllegalArgumentException.class, () -> new BillingContact(invalidBillingContact));
    }

    @Test
    public void isValidContact() {
        // null billing contact
        assertThrows(NullPointerException.class, () -> BillingContact.isValidContact(null));

        // invalid billing contact
        assertFalse(BillingContact.isValidContact("")); // empty string
        assertFalse(BillingContact.isValidContact(" ")); // spaces only
        assertFalse(BillingContact.isValidContact("91")); // less than 3 numbers
        assertFalse(BillingContact.isValidContact("BillingContact")); // non-numeric
        assertFalse(BillingContact.isValidContact("9011p041")); // alphabets within digits
        assertFalse(BillingContact.isValidContact("9312 1534")); // spaces within digits
        assertFalse(BillingContact.isValidContact("6588888888")); // longer than 8 digits
        assertFalse(BillingContact.isValidContact("11111111")); // does not start with 6, 8 or 9

        // valid billing contact
        assertTrue(BillingContact.isValidContact("93121534")); // starts with 9
        assertTrue(BillingContact.isValidContact("88888888")); // starts with 8
        assertTrue(BillingContact.isValidContact("65656565")); //starts with 6
    }

    @Test
    public void equals() {
        BillingContact contact = new BillingContact("99999999");

        // same values -> returns true
        assertTrue(contact.equals(new BillingContact("99999999")));

        // same object -> returns true
        assertTrue(contact.equals(contact));

        // null -> returns false
        assertFalse(contact.equals(null));

        // different types -> returns false
        assertFalse(contact.equals(5.0f));

        // different values -> returns false
        assertFalse(contact.equals(new BillingContact("88888888")));
    }
}

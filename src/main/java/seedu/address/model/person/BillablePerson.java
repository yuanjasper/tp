package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.BillingContact;
import seedu.address.model.tag.Tag;

/**
 * Represents a tutee with billing information in the address book.
 * Inherits all Person fields and behaviour, and adds session tracking and payment status.
 */
public class BillablePerson extends Person {

    public static final double RATE_PER_HOUR = 70;

    public final int unpaidHours;
    public final double amountOwed;

    /**
     * Creates a BillablePerson with all fields specified.
     */
    public BillablePerson(Name name, Phone phone, Email email, Address address, TuitionDate date, TuitionSlot slot,
                          Set<Tag> tags, Set<BillingContact> contacts, Remark remark,
                          int unpaidHours, double amountOwed) {
        super(name, phone, email, address, date, slot, tags, contacts, remark);
        this.unpaidHours = unpaidHours;
        this.amountOwed = unpaidHours * RATE_PER_HOUR;
    }

    /**
     * Returns the number of unpaid tuition hours for this person.
     */
    public int getUnpaidHours() {
        return unpaidHours;
    }

    /**
     * Returns the total amount owed based on total tuition hours and rate per hour.
     */
    public double getAmountOwed() {
        return amountOwed;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BillablePerson)) {
            return false;
        }
        BillablePerson otherPerson = (BillablePerson) other;
        return super.equals(otherPerson)
                && unpaidHours == otherPerson.unpaidHours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unpaidHours);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(super.toString())
                .add("unpaidHours", unpaidHours)
                .add("ratePerHour", String.format("$%.2f", RATE_PER_HOUR))
                .add("amountOwed", String.format("$%.2f", amountOwed))
                .toString();
    }
}

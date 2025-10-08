package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.BillingContact;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.tag.BillingContact}.
 */
class JsonAdaptedContact {

    private final String contact;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedContact(String contact) {
        this.contact = contact;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedContact(BillingContact source) {
        contact = source.contact;
    }

    @JsonValue
    public String getContact() {
        return contact;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public BillingContact toModelType() throws IllegalValueException {
        if (!BillingContact.isValidContact(contact)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new BillingContact(contact);
    }

}

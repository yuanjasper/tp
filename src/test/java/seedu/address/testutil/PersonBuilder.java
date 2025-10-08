package seedu.address.testutil;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TuitionDate;
import seedu.address.model.person.TuitionSlot;
import seedu.address.model.tag.BillingContact;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE = "Wednesday";
    public static final String DEFAULT_SLOT = "16:00-18:00";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private TuitionDate date;
    private TuitionSlot slot;
    private Set<Tag> tags;
    private Set<BillingContact> contacts;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        date = new TuitionDate(DEFAULT_DATE);
        slot = new TuitionSlot(DEFAULT_SLOT);
        tags = new HashSet<>();
        contacts = new HashSet<>();

    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        date = personToCopy.getDate();
        slot = personToCopy.getSlot();
        tags = new HashSet<>(personToCopy.getTags());
        contacts = new HashSet<>(personToCopy.getContacts());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<BillingContacts>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withContacts(String ... contacts) {
        this.contacts = SampleDataUtil.getContactSet(contacts);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code TuitionDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = new TuitionDate(date);
        return this;
    }

    /**
     * Sets the {@code TuitionSlot} of the {@code Person} that we are building.
     */
    public PersonBuilder withSlot(String slot) {
        this.slot = new TuitionSlot(slot);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, date, slot, tags, contacts);
    }

}

package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} is the keyword given
 */
public class NameIsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameIsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String personName = keywords.stream().reduce((x, y) -> x + " " + y).orElse("");
        return StringUtil.isNameIgnoreCase(person.getName().fullName, personName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameIsKeywordsPredicate)) {
            return false;
        }

        NameIsKeywordsPredicate otherNameIsKeywordsPredicate = (NameIsKeywordsPredicate) other;
        return keywords.equals(otherNameIsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords).toString();
    }
}

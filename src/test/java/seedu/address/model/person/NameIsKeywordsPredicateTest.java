package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameIsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameIsKeywordsPredicate firstPredicate = new NameIsKeywordsPredicate(firstPredicateKeywordList);
        NameIsKeywordsPredicate secondPredicate = new NameIsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameIsKeywordsPredicate firstPredicateCopy = new NameIsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameIsKeywords_returnsTrue() {
        // One keyword
        NameIsKeywordsPredicate predicate = new NameIsKeywordsPredicate(Arrays.asList("Alex"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));

        // Multiple keywords
        predicate = new NameIsKeywordsPredicate(Arrays.asList("Alex", "Yeoh"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));
    }

    @Test
    public void test_nameIsNotKeywords_returnsFalse() {

        // No matching keyword
        NameIsKeywordsPredicate predicate = new NameIsKeywordsPredicate(Arrays.asList("Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));

        // Matches some keywords
        predicate = new NameIsKeywordsPredicate(Arrays.asList("Alex", "Tan"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alex Yeoh").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameIsKeywordsPredicate(Arrays.asList("88888888", "alex@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alex Yeoh").withPhone("88888888")
                .withEmail("alex@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameIsKeywordsPredicate predicate = new NameIsKeywordsPredicate(keywords);

        String expected = NameIsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

package dev.maillo.user.domain;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserTest {

    private User user;

    @BeforeEach
    void setup() {
        User user = new User();
        user.setId(new ObjectId().toString());
        this.user = user;
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e7eaecdbe625fe40641c2", "5e5e7eeae621e7634a2e82aa"})
    void addMailingList_ValidMailingListId_ShouldAddMailingList(String mailingListId) {
        assertDoesNotThrow(() -> user.addMailingList(mailingListId));
    }

    @ParameterizedTest
    @NullSource
    void addMailingList_Null_ShouldThrowException(String mailingListId) {
        assertThrows(IllegalArgumentException.class, () -> user.addMailingList(mailingListId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void addMailingList_EmptyValue_ShouldThrowException(String mailingListId) {
        assertThrows(IllegalArgumentException.class, () -> user.addMailingList(mailingListId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e803d016bfe31286368a6", "5e5e8040a9f94d178811e490"})
    void addMailingList_Duplicate_ShouldThrowException(String mailingListId) {
        user.addMailingList(mailingListId);
        assertThrows(IllegalArgumentException.class, () -> user.addMailingList(mailingListId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e803d016bfe31286368a6", "5e5e8040a9f94d178811e490"})
    void removeMailingList_ExistingMailingListId_ShouldRemoveMailingList(String mailingListId) {
        user.addMailingList(mailingListId);
        assertDoesNotThrow(() -> user.removeMailingList(mailingListId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e811562bb682722e9bc16", "5e5e811a04577548df7ca2d5"})
    void removeMailingList_NonExistingMailingListId_ShouldThrowException(String mailingListId) {
        assertThrows(IllegalArgumentException.class, () -> user.removeMailingList(mailingListId));
    }

    @ParameterizedTest
    @NullSource
    void removeMailingList_Null_ShouldThrowException(String mailingListId) {
        assertThrows(IllegalArgumentException.class, () -> user.removeMailingList(mailingListId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void removeMailingList_EmptyValue_ShouldThrowException(String mailingListId) {
        assertThrows(IllegalArgumentException.class, () -> user.removeMailingList(mailingListId));
    }
}

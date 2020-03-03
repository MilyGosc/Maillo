package dev.maillo.mailinglist.domain;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MailingListTest {

    private MailingList mailingList;

    @BeforeEach
    void setup() {
        MailingList mailingList = new MailingList();
        mailingList.setId(new ObjectId().toString());
        this.mailingList = mailingList;
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e5d89f4a1a2f3030dd0ae", "5e5e5d8f46c7f4dc47be6f99"})
    void grantAccess_ValidId_ShouldGrantAccess(String userId) {
        assertDoesNotThrow(() -> mailingList.grantAccess(userId));
    }

    @ParameterizedTest
    @NullSource
    void grantAccess_Null_ShouldThrowException(String userId) {
        assertThrows(IllegalArgumentException.class, () -> mailingList.grantAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void grantAccess_EmptyValue_ShouldThrowException(String userId) {
        assertThrows(IllegalArgumentException.class, () -> mailingList.grantAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e5e866888343b373313cc", "5e5e5eb79af101ff0ca91d8f"})
    void grantAccess_Duplicate_ShouldThrowException(String userId) {
        mailingList.grantAccess(userId);
        assertThrows(IllegalArgumentException.class, () -> mailingList.grantAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e669780a016c163c9b565", "5e5e669e664adf1b68442771"})
    void revokeAccess_ExistingUserId_ShouldRevokeAccess(String userId) {
        mailingList.grantAccess("5e5e68cc87d7da672b2c0fe0");
        mailingList.grantAccess(userId);
        assertDoesNotThrow(() -> mailingList.revokeAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e684860dad5e2cfb5af4b", "5e5e684c540d0e975070204b"})
    void revokeAccess_NonExistingUserId_ShouldThrowException(String userId) {
        assertThrows(IllegalArgumentException.class, () -> mailingList.revokeAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5e5e688f61d523f72bbddc5b", "5e5e6893f124604f92cadef9"})
    void revokeAccess_LastUser_ShouldThrowException(String userId) {
        mailingList.grantAccess(userId);
        assertThrows(IllegalArgumentException.class, () -> mailingList.revokeAccess(userId));
    }

    @ParameterizedTest
    @NullSource
    void revokeAccess_Null_ShouldThrowException(String userId) {
        assertThrows(IllegalArgumentException.class, () -> mailingList.revokeAccess(userId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void revokeAccess_EmptyValue_ShouldThrowException(String userId) {
        assertThrows(IllegalArgumentException.class, () -> mailingList.revokeAccess(userId));
    }
}

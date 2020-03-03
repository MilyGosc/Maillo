package dev.maillo.mailinglist.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailingListRepository extends MongoRepository<MailingList, String> {
}

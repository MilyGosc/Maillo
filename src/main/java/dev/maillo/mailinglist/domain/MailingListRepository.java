package dev.maillo.mailinglist.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MailingListRepository extends MongoRepository<MailingList, String> {
    List<MailingList> findAllById(Iterable<String> ids);
}

package com.test.search.textme.repositories;

import com.test.search.textme.entities.LiveChatEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveChatRepository extends ElasticsearchRepository<LiveChatEntity, String> {
}

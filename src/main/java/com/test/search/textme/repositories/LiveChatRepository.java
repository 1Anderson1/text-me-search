package com.test.search.textme.repositories;

import com.test.search.textme.entities.LiveChatEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveChatRepository extends ElasticsearchRepository<LiveChatEntity, String> {

    List<LiveChatEntity> findByContentAndOperatorId(String content, Integer operatorId);

    List<LiveChatEntity> findByChatIdIn(List<Integer> chatIds);
}

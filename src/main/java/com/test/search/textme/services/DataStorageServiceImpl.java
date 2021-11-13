package com.test.search.textme.services;

import com.test.search.textme.converters.ChatConverter;
import com.test.search.textme.converters.FileConverter;
import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.models.ChatLogModel;
import com.test.search.textme.models.ChatResponse;
import com.test.search.textme.repositories.LiveChatRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;

@Service
@RequiredArgsConstructor
public class DataStorageServiceImpl implements DataStorageService {

    private final FileConverter fileConverter;
    private final ChatConverter chatConverter;
    private final LiveChatRepository liveChatRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void initDb(@NonNull MultipartFile file) {
        List<ChatLogModel> chatLogModels = fileConverter.convert(file);
        List<LiveChatEntity> liveChatEntities = chatConverter.convert(chatLogModels);
        liveChatRepository.saveAll(liveChatEntities);
    }

    @Override
    public List<ChatResponse> searchChats(@NonNull String text) {
        SearchHits<LiveChatEntity> queryResult = elasticsearchRestTemplate.search(getQuery(text), LiveChatEntity.class);
        if (!queryResult.hasSearchHits()) {
            return Collections.emptyList();
        }
        return chatConverter.convert(queryResult);
    }

    private NativeSearchQuery getQuery(@NonNull String text) {
        return new NativeSearchQueryBuilder()
                .withQuery(fuzzyQuery("messages.content", text))
                .withMaxResults(10)
                .withTrackScores(true)
                .build();
    }

}

package com.test.search.textme.services;

import com.test.search.textme.converters.ChatConverter;
import com.test.search.textme.converters.FileConverter;
import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.models.LiveChat;
import com.test.search.textme.repositories.LiveChatRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        List<LiveChat> liveChats = fileConverter.convert(file);
        List<LiveChatEntity> liveChatEntities = chatConverter.convert(liveChats);
        liveChatRepository.saveAll(liveChatEntities);
    }

    @Override
    public void searchChats(@NonNull String text) {
        SearchHits<LiveChatEntity> result = elasticsearchRestTemplate.search(getQuery(text), LiveChatEntity.class);

    }

    private NativeSearchQuery getQuery(@NonNull String text) {
        return new NativeSearchQueryBuilder()
                .withQuery(fuzzyQuery("messages.content", text))
                .withMaxResults(10)
                .withTrackScores(true)
                .build();
    }

}

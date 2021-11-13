package com.test.search.textme.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Document(indexName = "chat")
@Data
@Builder
public class LiveChatEntity {
    @Id
    private String id;
    @NonNull
    private Integer chatId;
    @NonNull
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<TextMessage> messages;
}

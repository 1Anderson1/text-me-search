package com.test.search.textme.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatResponse {
    private Float score;
    private Integer chatId;
    private List<ChatMessage> conversation;
}

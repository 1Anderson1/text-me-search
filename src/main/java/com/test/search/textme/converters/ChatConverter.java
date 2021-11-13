package com.test.search.textme.converters;

import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.entities.TextMessage;
import com.test.search.textme.models.ChatLogModel;
import com.test.search.textme.models.ChatMessage;
import com.test.search.textme.models.ChatResponse;
import com.test.search.textme.models.enums.ChatMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ChatConverter {

    public List<LiveChatEntity> convert(List<ChatLogModel> chatLogModels) {
        if (CollectionUtils.isEmpty(chatLogModels)) {
            log.error("liveChat list is empty or null");
            throw new IllegalArgumentException("Collection is empty");
        }

        Set<Integer> chatIds = chatLogModels.stream().map(ChatLogModel::getChatId).collect(Collectors.toSet());
        return chatIds.stream()
                .map(chatId -> LiveChatEntity.builder()
                        .chatId(chatId)
                        .messages(chatLogModels.stream()
                                .filter(chatLogModel -> chatLogModel.getChatId().equals(chatId))
                                .map(chatLogModel -> TextMessage.builder()
                                        .content(chatLogModel.getContent())
                                        .operatorId(chatLogModel.getOperatorId())
                                        .timestamp(Date.from(chatLogModel.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                                        .gwAccountNumber(chatLogModel.getGwAccountNumber())
                                        .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }

    public List<ChatResponse> convert(SearchHits<LiveChatEntity> queryResult) {
        List<ChatResponse> chatResponseList = new ArrayList<>();
        queryResult.stream().forEach(liveChatEntitySearchHit -> {
            LiveChatEntity liveChatEntity = liveChatEntitySearchHit.getContent();
            ChatResponse chatResponse = ChatResponse.builder()
                    .score(liveChatEntitySearchHit.getScore())
                    .chatId(liveChatEntity.getChatId())
                    .conversation(liveChatEntity.getMessages().stream()
                            .map(textMessage -> ChatMessage.builder()
                                    .content(textMessage.getContent())
                                    .timestamp(textMessage.getTimestamp())
                                    .chatMember(textMessage.getOperatorId() == null
                                            && Objects.equals(textMessage.getGwAccountNumber(), "")
                                            ? ChatMember.USER : ChatMember.OPERATOR)
                                    .build())
                            .sorted(Comparator.comparingLong(message -> message.getTimestamp().getTime()))
                            .collect(Collectors.toList()))
                    .build();
            chatResponseList.add(chatResponse);
        });
        chatResponseList.sort(Comparator.comparing(ChatResponse::getScore).reversed());
        return chatResponseList;
    }

}

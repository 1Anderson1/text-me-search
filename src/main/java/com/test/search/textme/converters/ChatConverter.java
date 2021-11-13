package com.test.search.textme.converters;

import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.entities.TextMessage;
import com.test.search.textme.models.LiveChat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class ChatConverter {

    public List<LiveChatEntity> convert(List<LiveChat> liveChats) {
        if (CollectionUtils.isEmpty(liveChats)) {
            log.error("liveChat list is empty or null");
            throw new IllegalArgumentException("Collection is empty");
        }

        Set<Integer> chatIds = liveChats.stream().map(LiveChat::getChatId).collect(Collectors.toSet());
        return chatIds.stream()
                .map(chatId -> LiveChatEntity.builder()
                        .chatId(chatId)
                        .messages(liveChats.stream()
                                .filter(liveChat -> liveChat.getChatId().equals(chatId))
                                .map(liveChat -> TextMessage.builder()
                                        .content(liveChat.getContent())
                                        .operatorId(liveChat.getOperatorId())
                                        .timestamp(Date.from(liveChat.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                                        .gwAccountNumber(liveChat.getGwAccountNumber())
                                        .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }

}

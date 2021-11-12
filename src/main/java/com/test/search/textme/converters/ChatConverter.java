package com.test.search.textme.converters;

import com.test.search.textme.entities.LiveChatEntity;
import com.test.search.textme.models.LiveChat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
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
        return liveChats.stream().map(liveChat -> LiveChatEntity.builder()
                        .chatId(liveChat.getChatId())
                        .content(liveChat.getContent())
                        .operatorId(liveChat.getOperatorId())
                        .timestamp(Date.from(liveChat.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                        .build())
                .collect(Collectors.toList());
    }
}

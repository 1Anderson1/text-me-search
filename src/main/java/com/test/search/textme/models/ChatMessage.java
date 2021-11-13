package com.test.search.textme.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.search.textme.models.enums.ChatMember;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ChatMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date timestamp;
    private String content;
    private ChatMember chatMember;
}

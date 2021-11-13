package com.test.search.textme.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ChatLogModel {
    @JsonProperty("chat_id")
    @NonNull
    private Integer chatId;
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime timestamp;
    @JsonProperty("operator_id")
    private Integer operatorId;
    @JsonProperty("gw_account_number")
    private String gwAccountNumber;
    private String content;
}

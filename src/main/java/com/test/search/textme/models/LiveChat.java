package com.test.search.textme.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveChat {
    @JsonProperty("chat_id")
    private Integer chatId;
    @JsonProperty("operator_id")
    private Integer operatorId;
    private String content;
}

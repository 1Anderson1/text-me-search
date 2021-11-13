package com.test.search.textme.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
public class TextMessage {
    @NonNull
    @Field(type = FieldType.Date)
    private Date timestamp;
    private Integer operatorId;
    private String content;
    @Field(name = "gw_account_number")
    private String gwAccountNumber;
}

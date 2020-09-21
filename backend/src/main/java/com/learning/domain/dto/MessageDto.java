package com.learning.domain.dto;

import com.learning.domain.entity.enums.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class MessageDto {
    private Long messageId;
    private String title;
    private String content;
    private Long userFrom;
    private Long userTo;
    private LocalDateTime date;
    private MessageStatus status;
    private List<MessageFileDto> files;
}

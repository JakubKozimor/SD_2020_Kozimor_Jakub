package com.learning.rest.domain.dto.message;

import com.learning.rest.domain.entity.enums.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class GroupMessageFromSearch {
    private Long messageId;
    private String title;
    private String content;
    private Long userFrom;
    private Set<Long> users;
    private LocalDateTime date;
    private MessageStatus status;
    private List<MessageFileDto> files;
}

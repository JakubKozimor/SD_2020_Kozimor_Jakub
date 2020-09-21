package com.learning.domain.dto;

import com.learning.domain.entity.MessageFile;
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
public class MessageDetailsDto {
    private Long messageId;
    private String title;
    private String content;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime date;
    private List<MessageFile> files;
}

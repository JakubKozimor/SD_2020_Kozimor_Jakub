package com.learning.rest.domain.dto.message;

import com.learning.rest.domain.entity.MessageFile;
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
    private Long userFromId;
    private String userFromFirstName;
    private String userFromLastName;
    private String userFromEmail;
    private Long userToId;
    private String userToFirstName;
    private String userToLastName;
    private String userToEmail;
    private LocalDateTime date;
    private List<MessageFile> files;
}

package com.learning.rest.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.rest.domain.entity.enums.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;
    private String title;
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user")
    private User userFrom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_user")
    private User userTo;
    @Column(name = "date")
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id")
    @JsonManagedReference
    private List<MessageFile> files;

    public void addFile(MessageFile messageFile) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(messageFile);
    }
}

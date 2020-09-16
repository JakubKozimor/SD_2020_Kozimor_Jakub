package com.learning.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.domain.entity.enums.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

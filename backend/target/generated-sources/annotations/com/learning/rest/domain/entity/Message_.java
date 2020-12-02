package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.MessageStatus;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, LocalDateTime> date;
	public static volatile SingularAttribute<Message, User> userFrom;
	public static volatile SingularAttribute<Message, Long> messageId;
	public static volatile ListAttribute<Message, MessageFile> files;
	public static volatile SingularAttribute<Message, String> title;
	public static volatile SingularAttribute<Message, User> userTo;
	public static volatile SingularAttribute<Message, String> content;
	public static volatile SingularAttribute<Message, MessageStatus> status;

	public static final String DATE = "date";
	public static final String USER_FROM = "userFrom";
	public static final String MESSAGE_ID = "messageId";
	public static final String FILES = "files";
	public static final String TITLE = "title";
	public static final String USER_TO = "userTo";
	public static final String CONTENT = "content";
	public static final String STATUS = "status";

}


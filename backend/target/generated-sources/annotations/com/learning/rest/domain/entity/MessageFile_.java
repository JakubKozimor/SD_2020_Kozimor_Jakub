package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessageFile.class)
public abstract class MessageFile_ {

	public static volatile SingularAttribute<MessageFile, String> fileName;
	public static volatile SingularAttribute<MessageFile, Long> messageFileId;
	public static volatile SingularAttribute<MessageFile, Message> message;
	public static volatile SingularAttribute<MessageFile, byte[]> fileContent;

	public static final String FILE_NAME = "fileName";
	public static final String MESSAGE_FILE_ID = "messageFileId";
	public static final String MESSAGE = "message";
	public static final String FILE_CONTENT = "fileContent";

}


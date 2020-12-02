package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiveHomeworkAnswerFile.class)
public abstract class LiveHomeworkAnswerFile_ {

	public static volatile SingularAttribute<LiveHomeworkAnswerFile, String> fileName;
	public static volatile SingularAttribute<LiveHomeworkAnswerFile, Long> liveHomeworkAnswerFileId;
	public static volatile SingularAttribute<LiveHomeworkAnswerFile, LiveHomeworkAnswer> liveHomeworkAnswer;
	public static volatile SingularAttribute<LiveHomeworkAnswerFile, byte[]> fileContent;

	public static final String FILE_NAME = "fileName";
	public static final String LIVE_HOMEWORK_ANSWER_FILE_ID = "liveHomeworkAnswerFileId";
	public static final String LIVE_HOMEWORK_ANSWER = "liveHomeworkAnswer";
	public static final String FILE_CONTENT = "fileContent";

}


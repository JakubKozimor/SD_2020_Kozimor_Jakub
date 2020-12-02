package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiveHomeworkFile.class)
public abstract class LiveHomeworkFile_ {

	public static volatile SingularAttribute<LiveHomeworkFile, Long> liveHomeworkFileId;
	public static volatile SingularAttribute<LiveHomeworkFile, String> fileName;
	public static volatile SingularAttribute<LiveHomeworkFile, LiveHomework> liveHomework;
	public static volatile SingularAttribute<LiveHomeworkFile, byte[]> fileContent;

	public static final String LIVE_HOMEWORK_FILE_ID = "liveHomeworkFileId";
	public static final String FILE_NAME = "fileName";
	public static final String LIVE_HOMEWORK = "liveHomework";
	public static final String FILE_CONTENT = "fileContent";

}


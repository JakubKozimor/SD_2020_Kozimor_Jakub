package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HomeworkAnswerFile.class)
public abstract class HomeworkAnswerFile_ {

	public static volatile SingularAttribute<HomeworkAnswerFile, String> fileName;
	public static volatile SingularAttribute<HomeworkAnswerFile, Long> homeworkAnswerFileId;
	public static volatile SingularAttribute<HomeworkAnswerFile, HomeworkAnswer> homeworkAnswer;
	public static volatile SingularAttribute<HomeworkAnswerFile, byte[]> fileContent;

	public static final String FILE_NAME = "fileName";
	public static final String HOMEWORK_ANSWER_FILE_ID = "homeworkAnswerFileId";
	public static final String HOMEWORK_ANSWER = "homeworkAnswer";
	public static final String FILE_CONTENT = "fileContent";

}


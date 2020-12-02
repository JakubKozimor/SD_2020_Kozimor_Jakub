package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HomeworkFile.class)
public abstract class HomeworkFile_ {

	public static volatile SingularAttribute<HomeworkFile, Long> homeworkFileId;
	public static volatile SingularAttribute<HomeworkFile, String> fileName;
	public static volatile SingularAttribute<HomeworkFile, Homework> homework;
	public static volatile SingularAttribute<HomeworkFile, byte[]> fileContent;

	public static final String HOMEWORK_FILE_ID = "homeworkFileId";
	public static final String FILE_NAME = "fileName";
	public static final String HOMEWORK = "homework";
	public static final String FILE_CONTENT = "fileContent";

}


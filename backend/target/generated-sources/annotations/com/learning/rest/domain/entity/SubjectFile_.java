package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubjectFile.class)
public abstract class SubjectFile_ {

	public static volatile SingularAttribute<SubjectFile, String> fileName;
	public static volatile SingularAttribute<SubjectFile, Subject> subject;
	public static volatile SingularAttribute<SubjectFile, Long> lessonFileId;
	public static volatile SingularAttribute<SubjectFile, byte[]> fileContent;

	public static final String FILE_NAME = "fileName";
	public static final String SUBJECT = "subject";
	public static final String LESSON_FILE_ID = "lessonFileId";
	public static final String FILE_CONTENT = "fileContent";

}


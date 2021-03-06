package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LessonFile.class)
public abstract class LessonFile_ {

	public static volatile SingularAttribute<LessonFile, String> fileName;
	public static volatile SingularAttribute<LessonFile, Lesson> lesson;
	public static volatile SingularAttribute<LessonFile, Long> lessonFileId;
	public static volatile SingularAttribute<LessonFile, byte[]> fileContent;

	public static final String FILE_NAME = "fileName";
	public static final String LESSON = "lesson";
	public static final String LESSON_FILE_ID = "lessonFileId";
	public static final String FILE_CONTENT = "fileContent";

}


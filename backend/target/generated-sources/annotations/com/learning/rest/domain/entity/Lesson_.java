package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.LessonStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lesson.class)
public abstract class Lesson_ {

	public static volatile ListAttribute<Lesson, LiveHomework> homeworks;
	public static volatile SingularAttribute<Lesson, User> teacher;
	public static volatile SingularAttribute<Lesson, Subject> subject;
	public static volatile SingularAttribute<Lesson, Long> lessonId;
	public static volatile ListAttribute<Lesson, LessonFile> files;
	public static volatile ListAttribute<Lesson, User> students;
	public static volatile SingularAttribute<Lesson, String> url;
	public static volatile SingularAttribute<Lesson, LessonStatus> status;

	public static final String HOMEWORKS = "homeworks";
	public static final String TEACHER = "teacher";
	public static final String SUBJECT = "subject";
	public static final String LESSON_ID = "lessonId";
	public static final String FILES = "files";
	public static final String STUDENTS = "students";
	public static final String URL = "url";
	public static final String STATUS = "status";

}


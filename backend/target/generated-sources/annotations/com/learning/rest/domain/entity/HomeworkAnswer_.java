package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HomeworkAnswer.class)
public abstract class HomeworkAnswer_ {

	public static volatile SingularAttribute<HomeworkAnswer, Homework> homework;
	public static volatile SingularAttribute<HomeworkAnswer, User> student;
	public static volatile SingularAttribute<HomeworkAnswer, String> grade;
	public static volatile ListAttribute<HomeworkAnswer, HomeworkAnswerFile> files;
	public static volatile SingularAttribute<HomeworkAnswer, String> comment;
	public static volatile SingularAttribute<HomeworkAnswer, Long> homeworkAnswerId;
	public static volatile SingularAttribute<HomeworkAnswer, String> message;

	public static final String HOMEWORK = "homework";
	public static final String STUDENT = "student";
	public static final String GRADE = "grade";
	public static final String FILES = "files";
	public static final String COMMENT = "comment";
	public static final String HOMEWORK_ANSWER_ID = "homeworkAnswerId";
	public static final String MESSAGE = "message";

}


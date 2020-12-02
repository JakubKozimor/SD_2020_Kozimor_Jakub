package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.HomeworkRatedStatus;
import com.learning.rest.domain.entity.enums.HomeworkStatus;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Homework.class)
public abstract class Homework_ {

	public static volatile SingularAttribute<Homework, HomeworkRatedStatus> rated;
	public static volatile SingularAttribute<Homework, Long> homeworkId;
	public static volatile SingularAttribute<Homework, Subject> subject;
	public static volatile ListAttribute<Homework, HomeworkAnswer> homeworkAnswers;
	public static volatile SingularAttribute<Homework, String> description;
	public static volatile ListAttribute<Homework, HomeworkFile> files;
	public static volatile SingularAttribute<Homework, String> title;
	public static volatile SingularAttribute<Homework, Date> deadline;
	public static volatile SingularAttribute<Homework, HomeworkStatus> status;

	public static final String RATED = "rated";
	public static final String HOMEWORK_ID = "homeworkId";
	public static final String SUBJECT = "subject";
	public static final String HOMEWORK_ANSWERS = "homeworkAnswers";
	public static final String DESCRIPTION = "description";
	public static final String FILES = "files";
	public static final String TITLE = "title";
	public static final String DEADLINE = "deadline";
	public static final String STATUS = "status";

}


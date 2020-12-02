package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiveHomeworkAnswer.class)
public abstract class LiveHomeworkAnswer_ {

	public static volatile SingularAttribute<LiveHomeworkAnswer, Long> liveHomeworkAnswerId;
	public static volatile SingularAttribute<LiveHomeworkAnswer, LiveHomework> liveHomework;
	public static volatile SingularAttribute<LiveHomeworkAnswer, User> student;
	public static volatile ListAttribute<LiveHomeworkAnswer, LiveHomeworkAnswerFile> files;
	public static volatile SingularAttribute<LiveHomeworkAnswer, String> message;

	public static final String LIVE_HOMEWORK_ANSWER_ID = "liveHomeworkAnswerId";
	public static final String LIVE_HOMEWORK = "liveHomework";
	public static final String STUDENT = "student";
	public static final String FILES = "files";
	public static final String MESSAGE = "message";

}


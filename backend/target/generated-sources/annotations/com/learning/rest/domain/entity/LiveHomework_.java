package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiveHomework.class)
public abstract class LiveHomework_ {

	public static volatile SingularAttribute<LiveHomework, Long> liveHomeworkId;
	public static volatile SingularAttribute<LiveHomework, Lesson> lesson;
	public static volatile ListAttribute<LiveHomework, LiveHomeworkAnswer> liveHomeworkAnswer;
	public static volatile SingularAttribute<LiveHomework, String> description;
	public static volatile ListAttribute<LiveHomework, LiveHomeworkFile> files;

	public static final String LIVE_HOMEWORK_ID = "liveHomeworkId";
	public static final String LESSON = "lesson";
	public static final String LIVE_HOMEWORK_ANSWER = "liveHomeworkAnswer";
	public static final String DESCRIPTION = "description";
	public static final String FILES = "files";

}


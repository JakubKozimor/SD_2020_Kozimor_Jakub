package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.Day;
import com.learning.rest.domain.entity.enums.Week;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public abstract class Subject_ {

	public static volatile ListAttribute<Subject, Homework> homeworks;
	public static volatile SingularAttribute<Subject, Week> week;
	public static volatile SingularAttribute<Subject, User> teacher;
	public static volatile SingularAttribute<Subject, String> name;
	public static volatile ListAttribute<Subject, SubjectFile> files;
	public static volatile SetAttribute<Subject, User> students;
	public static volatile SingularAttribute<Subject, String> startTime;
	public static volatile SingularAttribute<Subject, Day> day;
	public static volatile SingularAttribute<Subject, Long> subjectId;
	public static volatile SingularAttribute<Subject, Integer> longOfTime;

	public static final String HOMEWORKS = "homeworks";
	public static final String WEEK = "week";
	public static final String TEACHER = "teacher";
	public static final String NAME = "name";
	public static final String FILES = "files";
	public static final String STUDENTS = "students";
	public static final String START_TIME = "startTime";
	public static final String DAY = "day";
	public static final String SUBJECT_ID = "subjectId";
	public static final String LONG_OF_TIME = "longOfTime";

}


package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(School.class)
public abstract class School_ {

	public static volatile SingularAttribute<School, String> city;
	public static volatile SingularAttribute<School, Long> schoolId;
	public static volatile SingularAttribute<School, String> name;
	public static volatile ListAttribute<School, CalendarSchool> calendarSchool;

	public static final String CITY = "city";
	public static final String SCHOOL_ID = "schoolId";
	public static final String NAME = "name";
	public static final String CALENDAR_SCHOOL = "calendarSchool";

}


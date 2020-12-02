package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.Week;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CalendarSchool.class)
public abstract class CalendarSchool_ {

	public static volatile SingularAttribute<CalendarSchool, Week> week;
	public static volatile SingularAttribute<CalendarSchool, Long> calendarSchoolId;
	public static volatile SingularAttribute<CalendarSchool, School> school;
	public static volatile SingularAttribute<CalendarSchool, BaseCalendar> day;

	public static final String WEEK = "week";
	public static final String CALENDAR_SCHOOL_ID = "calendarSchoolId";
	public static final String SCHOOL = "school";
	public static final String DAY = "day";

}


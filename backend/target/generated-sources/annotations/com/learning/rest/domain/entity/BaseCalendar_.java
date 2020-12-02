package com.learning.rest.domain.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseCalendar.class)
public abstract class BaseCalendar_ {

	public static volatile SingularAttribute<BaseCalendar, Date> date;
	public static volatile SingularAttribute<BaseCalendar, Long> calendarId;
	public static volatile ListAttribute<BaseCalendar, CalendarSchool> calendarSchool;

	public static final String DATE = "date";
	public static final String CALENDAR_ID = "calendarId";
	public static final String CALENDAR_SCHOOL = "calendarSchool";

}


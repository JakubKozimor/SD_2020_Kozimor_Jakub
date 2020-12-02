package com.learning.rest.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, School> school;
	public static volatile SingularAttribute<User, String> twitchNick;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile ListAttribute<User, Subject> subjects;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> email;
	public static volatile ListAttribute<User, Lesson> lessons;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String SCHOOL = "school";
	public static final String TWITCH_NICK = "twitchNick";
	public static final String ROLES = "roles";
	public static final String SUBJECTS = "subjects";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";
	public static final String LESSONS = "lessons";

}


package com.learning.rest.domain.entity;

import com.learning.rest.domain.entity.enums.RoleName;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Long> roleId;
	public static volatile SingularAttribute<Role, RoleName> name;

	public static final String ROLE_ID = "roleId";
	public static final String NAME = "name";

}


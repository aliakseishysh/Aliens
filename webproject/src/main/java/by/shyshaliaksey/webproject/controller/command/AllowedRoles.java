package by.shyshaliaksey.webproject.controller.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import by.shyshaliaksey.webproject.model.entity.Role;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AllowedRoles {

	Role[] value() default Role.GUEST;
	
}

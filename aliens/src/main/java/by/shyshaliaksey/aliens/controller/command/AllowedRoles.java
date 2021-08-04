package by.shyshaliaksey.aliens.controller.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import by.shyshaliaksey.aliens.model.entity.User.Role;

/**
 * Provides help to restrict commands access for different roles <br>
 * 
 * @see CommandAccessChecker
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AllowedRoles {

	Role[] value() default Role.GUEST;

}

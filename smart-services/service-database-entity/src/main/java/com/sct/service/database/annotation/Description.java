/*
 * 
 */

package com.sct.service.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adds a textual description to entity dic value.
 *
 * @author 
 * @since 1.0.0
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	/**
	 * The textual description to associate with the bean definition.
	 *
	 * @return The textual description.
	 */
	String value();

}

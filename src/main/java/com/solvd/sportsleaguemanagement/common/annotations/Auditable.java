package com.solvd.sportsleaguemanagement.common.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Auditable {
    String value() default "Tracked";
}

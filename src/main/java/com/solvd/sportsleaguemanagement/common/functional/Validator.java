package com.solvd.sportsleaguemanagement.common.functional;

import com.solvd.sportsleaguemanagement.common.Result;

@FunctionalInterface
public interface Validator<T> {
    Result validate(T t);

    default Validator<T> and(Validator<T> other) {
        return t -> {
            Result r = this.validate(t);
            return r.isOk() ? other.validate(t) : r;
        };
    }
}

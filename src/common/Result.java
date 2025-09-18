package common;

import java.util.Optional;

public class Result<T> {

    private final T value;
    private final String error;

    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(null, msg);
    }

    public boolean isOk() {
        return error == null;
    }

    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

    public Optional<String> getError() {
        return Optional.ofNullable(error);
    }
}

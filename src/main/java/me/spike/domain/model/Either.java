package me.spike.domain.model;

import java.util.function.Consumer;
import java.util.function.Function;

public class Either<D> {
    private final Error error;
    private final D data;

    public Either(D data) {
        this.error = null;
        this.data = data;
    }

    public Either(Error error) {
        this.error = error;
        this.data = null;
    }

    public D getData() {
        return data;
    }

    public void execute(Consumer<D> onData, Consumer<Error> onError) {
        this.map(data -> {
            onData.accept(data);
            return null;
        }, errors -> {
            onError.accept(errors);
            return null;
        });
    }

    public <T> T map(Function<D, T> onData, Function<Error, T> onError) {
        if(data != null) {
            return onData.apply(data);
        } else {
            return onError.apply(error);
        }
    }

}

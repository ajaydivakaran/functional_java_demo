package me.spike.domain.model;

import java.util.Optional;
import java.util.function.Function;

public class Left<TLeft, TRight> extends Either<TLeft, TRight> {
    private final TLeft value;

    public Left(TLeft value) {
        this.value = value;
    }

    @Override
    public <TNewLeft> Either<TNewLeft, TRight> mapLeft(Function<TLeft, TNewLeft> map) {
        return new Left<>(map.apply(value));
    }

    @Override
    public <TNewSuccess> Either<TLeft, TNewSuccess> mapRight(Function<TRight, TNewSuccess> map) {
        return new Left<>(this.value);
    }

    @Override
    public TLeft reduce(Function<TRight, TLeft> map) {
        return value;
    }

    @Override
    public Optional<TLeft> getLeft() {
        return Optional.of(value);
    }

    @Override
    public Optional<TRight> getRight() {
        return Optional.empty();
    }
}

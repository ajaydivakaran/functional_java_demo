package me.spike.domain.model;

import java.util.Optional;
import java.util.function.Function;

public class Right<TLeft, TRight> extends Either<TLeft, TRight> {
    private final TRight value;

    public Right(TRight value) {
        this.value = value;
    }

    @Override
    public <TNewError> Either<TNewError, TRight> mapLeft(Function<TLeft, TNewError> map) {
        return new Right<>(this.value);
    }

    @Override
    public <TNewSuccess> Either<TLeft, TNewSuccess> mapRight(Function<TRight, TNewSuccess> map) {
        return new Right<>(map.apply(this.value));
    }

    @Override
    public TLeft reduce(Function<TRight, TLeft> map) {
        return map.apply(this.value);
    }

    @Override
    public Optional<TLeft> getLeft() {
        return Optional.empty();
    }

    @Override
    public Optional<TRight> getRight() {
        return Optional.of(value);
    }
}

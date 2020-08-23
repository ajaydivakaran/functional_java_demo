package me.spike.domain.model;

import java.util.Optional;
import java.util.function.Function;

public abstract class Either<TLeft, TRight> {
    public abstract <TNewLeft> Either<TNewLeft, TRight> mapLeft(Function<TLeft, TNewLeft> map);

    public abstract <TNewRight> Either<TLeft, TNewRight> mapRight(Function<TRight, TNewRight> map);

    public abstract TLeft reduce(Function<TRight, TLeft> map);

    public abstract Optional<TLeft> getLeft();
    public abstract Optional<TRight> getRight();

}

package ua.com.findcoach.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomListCollector<T, R> implements Collector<T, List<T>, R> {
    @Override
    public Supplier<List<T>> supplier() {
        return (Supplier<List<T>>) ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> { left.addAll(right); return left; };
    }

    @Override
    public Function<List<T>, R> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}

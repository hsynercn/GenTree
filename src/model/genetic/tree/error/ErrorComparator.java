package model.genetic.tree.error;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by saruman on 24.07.2017.
 */
public class ErrorComparator implements Comparator<TreeError> {


    @Override
    public int compare(TreeError o1, TreeError o2) {
        return o1.getError() < o2.getError() ? 1 : o1.getError() == o2.getError() ? 0 : -1;
    }

    @Override
    public Comparator<TreeError> reversed() {
        return null;
    }

    @Override
    public Comparator<TreeError> thenComparing(Comparator<? super TreeError> other) {
        return null;
    }

    @Override
    public <U> Comparator<TreeError> thenComparing(Function<? super TreeError, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<TreeError> thenComparing(Function<? super TreeError, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<TreeError> thenComparingInt(ToIntFunction<? super TreeError> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<TreeError> thenComparingLong(ToLongFunction<? super TreeError> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<TreeError> thenComparingDouble(ToDoubleFunction<? super TreeError> keyExtractor) {
        return null;
    }
}

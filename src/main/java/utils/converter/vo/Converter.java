package utils.converter.vo;

/**
 * Created by vlasov-id-131216 on 07.03.15.
 */
public interface Converter<F, T> {
    T convertSourceToTarget(F from, T to);
    F convertTargetToSource(T from, F to);
    Class<F> getSource();
    Class<T> getTarget();
}

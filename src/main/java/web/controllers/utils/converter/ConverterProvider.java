package web.controllers.utils.converter;

/**
 * Created by vlasov-id-131216 on 03.04.15.
 */
public interface ConverterProvider<F, T> {
    T convertSourceToTarget(F from, T to);
    F convertTargetToSource(T from, F to);
    Class<F> getSource();
    Class<T> getTarget();
}

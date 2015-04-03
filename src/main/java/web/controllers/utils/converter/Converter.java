package web.controllers.utils.converter;

import java.util.List;
import java.util.Set;

public interface Converter {

    <S,T> T convert(S source, Class<T> toClass);
    <S,T> T convert(S source);
    <S,T> List<T> convert(List<S> source, Class<T> toClass);
    <S,T> List<T> convert(List<S> source);
    <S,T> Set<T> convert(Set<S> source, Class<T> toClass);
    <S,T> Set<T> convert(Set<S> source);
    <T> Class getOppositeClass(Class<T> clazz);

}
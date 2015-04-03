package web.controllers.utils.converter;

import org.hibernate.proxy.HibernateProxy;

import javax.annotation.PostConstruct;
import java.util.*;

public class ConverterImpl implements Converter {

    private List<ConverterProvider> providers;
    private Map<ProviderMapKey, ConverterProvider> keys;

    @Override
    public <S, T> T convert(S source, Class<T> toClass) {
        if (source == null) return null;
        Class fromClass = getPersistentClass(source);
        ConverterProvider p = keys.get(new ProviderMapKey(fromClass, toClass));
        if (p != null) {
            return (T) (fromClass.equals(p.getSource()) ?
                    p.convertSourceToTarget(source, null) : p.convertTargetToSource(source, null));
        } else {
            p = keys.get(new ProviderMapKey(toClass, fromClass));
        }
        if (p != null) {
            return (T) (fromClass.equals(p.getSource()) ?
                    p.convertTargetToSource(source, null) : p.convertSourceToTarget(source, null));
        }
        String message = "converting from " + source.getClass() + " to " + toClass + " not supported yet";
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <S, T> T convert(S source) {
        if (source == null) return null;
        Class clazz = getOppositeClass(source.getClass());
        return (T) convert(source, clazz);
    }

    public <S, T> Collection<T> convert(Collection<S> source, Class<T> toClass, Collection<T> container) {
        if (source == null || source.size() == 0) {
            return container;
        }
        if (toClass != null) {
            S sourceExample = source.iterator().next();
            Class fromClass = getPersistentClass(sourceExample);
            ConverterProvider p = keys.get(new ProviderMapKey(fromClass, toClass));
            if (p != null) {
                for (S s : source) {
                    container.add(
                            (T) (fromClass.equals(p.getSource()) ?
                                    p.convertSourceToTarget(s, null) : p.convertTargetToSource(s, null))
                    );
                }
                return container;
            } else {
                p = keys.get(new ProviderMapKey(toClass, fromClass));
                if (p != null) {
                    for (S s : source) {
                        container.add(
                                (T) (fromClass.equals(p.getSource()) ?
                                        p.convertTargetToSource(s, null) : p.convertSourceToTarget(s, null))
                        );
                    }
                    return container;
                }
            }
        } else {
            for (S s : source) {
                container.add((T) convert(s));
            }
            if (container.size() > 0) return container;
        }
        String message = "converting from " + source.iterator().next().getClass() + " to " + toClass + " not supported yet";
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <S, T> List<T> convert(List<S> source, Class<T> toClass) {
        return (List<T>) convert(source, toClass, new LinkedList<T>());
    }

    @Override
    public <S, T> List<T> convert(List<S> source) {
        return (List<T>) convert(source, null, new LinkedList<T>());
    }

    @Override
    public <S, T> Set<T> convert(Set<S> source, Class<T> toClass) {
        return (Set<T>) convert(source, toClass, new HashSet<T>());
    }

    @Override
    public <S, T> Set<T> convert(Set<S> source) {
        return (Set<T>) convert(source, null, new HashSet<T>());
    }

    @Override
    public <T> Class getOppositeClass(Class<T> clazz) {
        for (ConverterProvider p : providers) {
            if (p.getSource().equals(clazz)) {
                return p.getTarget();
            } else {
                if (p.getTarget().equals(clazz)) {
                    return p.getSource();
                }
            }
        }
        String message = "can't find opposite class for " + clazz;
        throw new UnsupportedOperationException(message);
    }

    @PostConstruct
    public void init() {
        keys = new HashMap<ProviderMapKey, ConverterProvider>();
        for (ConverterProvider p : providers) {
            keys.put(new ProviderMapKey(p.getSource(), p.getTarget()), p);
            keys.put(new ProviderMapKey(p.getTarget(), p.getSource()), p);
        }
    }

    private Class getPersistentClass(Object o) {
        if (o instanceof HibernateProxy) {
            return ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass();
        } else {
            return o.getClass();
        }
    }

    public void setProviders(List<ConverterProvider> providers) {
        this.providers = providers;
    }

    public Map<ProviderMapKey, ConverterProvider> getKeys() {
        return keys;
    }
}
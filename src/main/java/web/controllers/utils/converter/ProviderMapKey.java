package web.controllers.utils.converter;

public class ProviderMapKey {
    private Class fromClass;
    private Class toClass;

    public ProviderMapKey(Class fromClass, Class toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public Class getFromClass() {
        return fromClass;
    }

    public void setFromClass(Class fromClass) {
        this.fromClass = fromClass;
    }

    public Class getToClass() {
        return toClass;
    }

    public void setToClass(Class toClass) {
        this.toClass = toClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderMapKey that = (ProviderMapKey) o;
        if (fromClass != null ? !fromClass.equals(that.fromClass) : that.fromClass != null) return false;
        if (toClass != null ? !toClass.equals(that.toClass) : that.toClass != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = fromClass != null ? fromClass.hashCode() : 0;
        result = 31 * result + (toClass != null ? toClass.hashCode() : 0);
        return result;
    }
}
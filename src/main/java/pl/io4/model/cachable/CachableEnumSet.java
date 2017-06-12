package pl.io4.model.cachable;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.function.Consumer;
import org.json.JSONArray;

/**
 * Created by Zax37 on 25.05.2017.
 */
public final class CachableEnumSet<T extends Enum<T>>
        implements Cachable, Collection<T> {
    private EnumSet<T> set;
    private Class<T> cls;

    public CachableEnumSet(Class<T> cls) {
        this.cls = cls;
        this.set = EnumSet.noneOf(cls);
    }

    public CachableEnumSet(Class<T> cls, EnumSet<T> set) {
        this.cls = cls;
        this.set = set;
    }

    public static <E extends Enum<E>> CachableEnumSet noneOf(Class<E> cls) {
        return new CachableEnumSet(cls);
    }

    public static <E extends Enum<E>> CachableEnumSet allOf(Class<E> cls) {
        return new CachableEnumSet(cls, EnumSet.allOf(cls));
    }

    public void clear() {
        set.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        CachableEnumSet<T> that = (CachableEnumSet<T>) o;
        return set.equals(that.set);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(T el) {
        return set.add(el);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return set.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        set.forEach(action);
    }

    @Override
    public String toString() {
        return set.toString();
    }

    @Override
    public JSONArray cache() {
        JSONArray ret = new JSONArray();
        for (T value : set) {
            ret.put(value.toString());
        }
        return ret;
    }

    @Override
    public void load(Object data) {
        if (data.getClass() != JSONArray.class) {
            throw new IllegalArgumentException("Not a JSONArray.");
        } else {
            load((JSONArray)data);
        }
    }

    public void load(JSONArray data) {
        set.clear();
        for (int i = 0, size = data.length(); i < size; i++) {
            for (T enu : EnumSet.allOf(cls)) {
                if (enu.toString().equals(data.getString(i))) {
                    set.add(enu);
                    break;
                }
            }
        }
    }
}

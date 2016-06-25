package self.map;

import java.util.Collection;
import java.util.Set;

public interface SMap<K, V> {
  int size();

  boolean isEmpty();

  boolean containsKey(Object key);

  boolean containsValue(Object value);

  V get(Object key);

  V put(K key, V value);

  V remove(Object key);

  void putAll(SMap<? extends K, ? extends V> m);

  void clear();

  Set<K> keySet();

  Collection<V> values();

  Set<SMap.Entry<K, V>> entrySet();

  interface Entry<K, V> {
    K getKey();

    V getValue();

    V setValue(V value);

    boolean equals(Object o);

    int hashCode();
  }

  boolean equals(Object o);

  int hashCode();
}

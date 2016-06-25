package self.map;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractSMap<K, V> implements SMap<K, V> {

  protected AbstractSMap() {
  }

  @Override
  public int size() {
    return entrySet().size();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsValue(Object value) {
    Iterator<Entry<K, V>> iter = entrySet().iterator();
    if (value == null) {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (entry.getValue() == null) return true;
      }
    } else {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (value.equals(entry.getValue())) return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsKey(Object key) {
    Iterator<SMap.Entry<K, V>> iter = entrySet().iterator();
    if (key == null) {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (entry.getKey() == null) return true;
      }
    } else {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (key.equals(entry.getKey())) return true;
      }
    }
    return false;
  }

  @Override
  public V get(Object key) {
    Iterator<Entry<K, V>> iter = entrySet().iterator();
    if (key == null) {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (entry.getKey() == null) return entry.getValue();
      }
    } else {
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (key.equals(entry.getKey())) return entry.getValue();
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V remove(Object key) {
    Iterator<Entry<K, V>> iter = entrySet().iterator();
    Entry<K, V> correctEntry = null;
    if (key == null) {
      while (correctEntry == null && iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (entry.getKey() == null) correctEntry = entry;
      }
    } else {
      while (correctEntry == null && iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        if (key.equals(entry.getKey())) correctEntry = entry;
      }
    }

    V oldValue = null;
    if (correctEntry != null) {
      oldValue = correctEntry.getValue();
      iter.remove();
    }
    return oldValue;
  }

  @Override
  public void putAll(SMap<? extends K, ? extends V> m) {
    for (SMap.Entry<? extends K, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {
    entrySet().clear();
  }

  transient volatile Set<K> keySet;
  transient volatile Collection<V> values;

  @Override
  public Set<K> keySet() {
    if (keySet == null) {
      keySet = new AbstractSet<K>() {
        @Override
        public Iterator<K> iterator() {
          return new Iterator<K>() {
            private Iterator<Entry<K, V>> it = entrySet().iterator();

            @Override
            public boolean hasNext() {
              return it.hasNext();
            }

            @Override
            public K next() {
              return it.next().getKey();
            }

            @Override
            public void remove() {
              it.remove();
            }
          };
        }

        @Override
        public int size() {
          return AbstractSMap.this.size();
        }

        @Override
        public boolean isEmpty() {
          return AbstractSMap.this.isEmpty();
        }

        @Override
        public void clear() {
          AbstractSMap.this.clear();
        }

        public boolean contains(Object o) {
          return AbstractSMap.this.containsKey(o);
        }
      };
    }
    return keySet;
  }

  @Override
  public Collection<V> values() {
    if (values == null) {
      values = new AbstractCollection<V>() {
        @Override
        public Iterator<V> iterator() {
          return new Iterator<V>() {
            private Iterator<Entry<K, V>> it = entrySet().iterator();

            @Override
            public boolean hasNext() {
              return it.hasNext();
            }

            @Override
            public V next() {
              return it.next().getValue();
            }

            @Override
            public void remove() {
              it.remove();
            }
          };
        }

        @Override
        public int size() {
          return AbstractSMap.this.size();
        }

        @Override
        public boolean isEmpty() {
          return AbstractSMap.this.isEmpty();
        }

        @Override
        public void clear() {
          AbstractSMap.this.clear();
        }

        @Override
        public boolean contains(Object o) {
          return AbstractSMap.this.containsValue(o);
        }
      };
    }
    return values;
  }

  public abstract Set<Entry<K, V>> entrySet();

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof SMap)) return false;

    SMap<?, ?> sm = (SMap<?, ?>) obj;
    if (sm.size() != size()) return false;

    try {
      Iterator<Entry<K, V>> iter = entrySet().iterator();
      while (iter.hasNext()) {
        Entry<K, V> entry = iter.next();
        K key = entry.getKey();
        V value = entry.getValue();
        if (value == null) {
          if (!(sm.get(key) == null && sm.containsKey(key))) return false;
        } else {
          if (!value.equals(sm.get(key))) return false;
        }
      }
    } catch (ClassCastException unused) {
      return false;
    } catch (NullPointerException unused) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int h = 0;
    Iterator<Entry<K, V>> iter = entrySet().iterator();
    while (iter.hasNext()) {
      h += iter.next().hashCode();
    }
    return h;
  }

  @Override
  public String toString() {
    Iterator<Entry<K, V>> iter = entrySet().iterator();
    if (!iter.hasNext()) return "{}";

    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (;;) {
      Entry<K, V> entry = iter.next();
      K key = entry.getKey();
      V value = entry.getValue();
      sb.append(key == this ? "(this Map)" : key).append("=")
          .append(value == this ? "(this Map)" : value);
      if (!iter.hasNext()) return sb.append("}").toString();
      sb.append(',').append(' ');
    }
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    AbstractSMap<?, ?> result = (AbstractSMap<?, ?>) super.clone();
    result.keySet = null;
    result.values = null;
    return result;
  }

  private static boolean eq(Object o1, Object o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  public static class SimpleEntry<K, V> implements Entry<K, V>, Serializable {
    private static final long serialVersionUID = -8499721149061103585L;

    private final K key;
    private V value;

    public SimpleEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public SimpleEntry(Entry<? extends K, ? extends V> entry) {
      this.key = entry.getKey();
      this.value = entry.getValue();
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof SMap.Entry)) return false;

      SMap.Entry<?, ?> entry = (SMap.Entry<?, ?>) obj;
      return eq(key, entry.getKey()) && eq(value, entry.getValue());
    }

    @Override
    public int hashCode() {
      return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
      return key + "=" + value;
    }
  }

  public static class SimpleImmutableEntry<K, V> implements Entry<K, V>, Serializable {
    private static final long serialVersionUID = 7138329143949025153L;

    private final K key;
    private final V value;

    public SimpleImmutableEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
      this.key = entry.getKey();
      this.value = entry.getValue();
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof SMap.Entry)) return false;
      SMap.Entry<?, ?> entry = (SMap.Entry<?, ?>) obj;
      return eq(key, entry.getKey()) && eq(value, entry.getValue());
    }

    @Override
    public int hashCode() {
      return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
      return key + "=" + value;
    }
  }
}

package self.collection;

import java.util.Iterator;

public interface SCollection<E> extends Iterable<E> {

  int size();

  boolean isEmpty();

  boolean contains(Object obj);

  Iterator<E> iterator();

  Object[] toArray();

  <T> T[] toArray(T[] a);

  boolean add(E e);

  boolean remove(Object obj);

  boolean containsAll(SCollection<?> e);

  boolean addAll(SCollection<? extends E> c);

  boolean removeAll(SCollection<? extends E> c);

  boolean retainAll(SCollection<E> c);

  void clear();

  boolean equals(Object obj);

  int hashCode();
}

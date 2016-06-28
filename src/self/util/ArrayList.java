package self.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;

public class ArrayList<E> extends AbstractCollection<E> implements List<E>, RandomAccess,
    Cloneable, Serializable {

  private static final long serialVersionUID = 8683452581122892189L;

  private static final int DEFAULT_CAPACITY = 10;

  private static final Object[] EMPTY_ELEMENTDATA = {};

  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

  transient Object[] elementData;

  private int size;

  public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.elementData = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }

  public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
      if (elementData.getClass() != Object[].class) {
        elementData = Arrays.copyOf(elementData, size, Object[].class);
      }
    } else {
      this.elementData = EMPTY_ELEMENTDATA;
    }
  }

  public void trimToSize() {
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // TODO Implement List<E>.addAll
    return false;
  }

  @Override
  public E get(int index) {
    // TODO Implement List<E>.get
    return null;
  }

  @Override
  public E set(int index, E element) {
    // TODO Implement List<E>.set
    return null;
  }

  @Override
  public void add(int index, E element) {
    // TODO Implement List<E>.add

  }

  @Override
  public E remove(int index) {
    // TODO Implement List<E>.remove
    return null;
  }

  @Override
  public int indexOf(Object o) {
    // TODO Implement List<E>.indexOf
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    // TODO Implement List<E>.lastIndexOf
    return 0;
  }

  @Override
  public ListIterator<E> listIterator() {
    // TODO Implement List<E>.listIterator
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    // TODO Implement List<E>.listIterator
    return null;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    // TODO Implement List<E>.subList
    return null;
  }

  @Override
  public Iterator<E> iterator() {
    // TODO Implement AbstractCollection<E>.iterator
    return null;
  }

  @Override
  public int size() {
    // TODO Implement AbstractCollection<E>.size
    return 0;
  }

}

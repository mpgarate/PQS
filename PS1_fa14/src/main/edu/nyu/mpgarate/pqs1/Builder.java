package edu.nyu.mpgarate.pqs1;

/**
 * A Builder is a class whose purpose is to construct an object of type T. In
 * this library, a buider's setter methods should begin with the word 'with' and
 * return an instance of the current builder.
 * 
 * @author Michael Garate
 *
 * @param <T>
 *          the type for a builder to create
 */
interface Builder<T> {
  public T build();
}

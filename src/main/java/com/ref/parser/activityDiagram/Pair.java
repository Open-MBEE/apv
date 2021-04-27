package com.ref.parser.activityDiagram;

import java.io.Serializable;


/**
 * <p>
 * A convenience class to represent name-value pairs.
 * </p>
 * 
 * @since JavaFX 2.0
 */
public class Pair<K, V extends Comparable> implements Serializable, Comparable<Pair<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180918202341866070L;
	/**
	 * Key of this <code>Pair</code>.
	 */
	private K key;

	/**
	 * Gets the key for this pair.
	 * 
	 * @return key for this pair
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Value of this this <code>Pair</code>.
	 */
	private V value;

	/**
	 * Gets the value for this pair.
	 * 
	 * @return value for this pair
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Creates a new pair
	 * 
	 * @param key The key for this pair 62 * @param value The value to use for this
	 *            pair 63
	 */
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 70 *
	 * <p>
	 * <code>String</code> representation of this 71 * <code>Pair</code>.
	 * </p>
	 * 72 * 73 *
	 * <p>
	 * The default name/value delimiter '=' is always used.
	 * </p>
	 * 74 * 75 * @return <code>String</code> representation of this
	 * <code>Pair</code> 76
	 */
	@Override
	public String toString() {
		return key + "=" + value;
	}

	/**
	 * 83 *
	 * <p>
	 * Generate a hash code for this <code>Pair</code>.
	 * </p>
	 * 84 * 85 *
	 * <p>
	 * The hash code is calculated using both the name and 86 * the value of the
	 * <code>Pair</code>.
	 * </p>
	 * 87 * 88 * @return hash code for this <code>Pair</code> 89
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (key != null ? key.hashCode() : 0);
		hash = 31 * hash + (value != null ? value.hashCode() : 0);
		return hash;
	}

	/**
	 * 99 *
	 * <p>
	 * Test this <code>Pair</code> for equality with another 100 *
	 * <code>Object</code>.
	 * </p>
	 * 101 * 102 *
	 * <p>
	 * If the <code>Object</code> to be tested is not a 103 * <code>Pair</code> or
	 * is <code>null</code>, then this method 104 * returns <code>false</code>.
	 * </p>
	 * 105 * 106 *
	 * <p>
	 * Two <code>Pair</code>s are considered equal if and only if 107 * both the
	 * names and values are equal.
	 * </p>
	 * 108 * 109 * @param o the <code>Object</code> to test for 110 * equality with
	 * this <code>Pair</code> 111 * @return <code>true</code> if the given
	 * <code>Object</code> is 112 * equal to this <code>Pair</code> else
	 * <code>false</code> 113
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof Pair) {
			Pair<?, ?> pair = (Pair<?, ?>) o;
			if (key != null ? !key.equals(pair.key) : pair.key != null)
				return false;
			if (value != null ? !value.equals(pair.value) : pair.value != null)
				return false;
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Pair<K, V> o) {
		return o.getValue().compareTo(this.getValue());
	}
}

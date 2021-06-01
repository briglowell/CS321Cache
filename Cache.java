import java.util.*;

/**
 * This is a the class for implementing a cache of objects. Uses Linked List
 * data structure.
 * 
 * @author BrigLowell
 * @param <T>
 *
 */
public class Cache<T> {

	LinkedList<T> cache;
	private int size;
	private int cacheMax;
	private int DEFAULT_CACHE_MAX = 1000;
	// private double HR, HR1, HR2; //total, 1st level, and 2nd level cache Hit
	// ratios
	// private int NH, NH1, NH2; //total, 1st level, and 2nd level number of cache
	// hits
	// private int NR, NR1, NR2; //total, 1st level, and 2nd level cache hit
	// references

	public Cache() {
		size = 0;
		cacheMax = DEFAULT_CACHE_MAX;
		cache = new LinkedList<T>();
	}

	/**
	 * Constructor to build Cache with specified size
	 * 
	 * @param cacheSize
	 */
	public Cache(int cacheSize) {
		size = 0;
		if (cacheSize <= 0) {
			throw new IndexOutOfBoundsException("Cache size must be greater than 0.");
		}
		cacheMax = cacheSize;
		cache = new LinkedList<T>();
	}

	/**
	 * Returns true if Object is in list
	 * 
	 * @param obj
	 */
	public T getObject(int i) {

		return cache.get(i);
	}

	/**
	 * @param obj
	 * @return true if Object is in list
	 */
	public boolean hasObject(T obj) {
		return cache.contains(obj);
	}

	/**
	 * Adds object to top of
	 * 
	 * @param obj
	 */
	public void addObject(T obj) {

		if (cache.contains(obj)) { // search cache for item
			// NH++; //increment cache hit if found
			cache.remove(obj); // removes the object if found so it can be added
			size--; // decrement size
		}

		if (size == cacheMax) { // checks if cache is full
			cache.addFirst(obj); // adds object to the top of cache
			cache.removeLast(); // removes last object since it is full
		} else {
			cache.addFirst(obj); // adds object to top of cache
			size++; // increment size
		}
		// NR ++; //increment number reference
	}

	/**
	 * Searches cache for object and removes the object from list
	 * 
	 * @param obj
	 * @return
	 */
	public T removeObject(T obj) {

		if (!isEmpty()) { // checks if cache is empty
			if (cache.contains(obj)) { // checks if cache contains object
				cache.remove(obj);
				size--;
			} else {
				throw new NoSuchElementException("Object: " + obj + " is not in cache.");
			}
		} else {
			throw new NoSuchElementException("Cache is Empty.");
		}

		return obj;
	}

	/**
	 * clears the cache
	 */
	public void clearCache() {
		cache.clear();
		size = 0;
	}

	/**
	 * @return true if cache is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @return cache size
	 */
	public int getSize() {
		return size;
	}
	//
	// /**
	// * @return true if cache is full
	// */
	// public boolean isFull() {
	// return (size == cacheMax);
	// }

	//
	// /**
	// * @return cache hits
	// */
	// public int getcacheHits() {
	// return NH;
	// }
	//
	// /**
	// * @return number of references
	// */
	// public int getcacheReferences() {
	// return NR;
	// }
	//
	// /**
	// * @return cache max size
	// */
	// public int getMax() {
	// return cacheMax;
	//
	// }
}


 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester.hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A Map that uses ints as the keys.
 * <p>Use just like any java.util.Map, except that the keys must be ints.
 * This is much faster than creating a new Integer for each access.</p>
 * <p>For non-Map access (faster) use the put(int, Object) method.</p>
 * <p>This class implements Map for convenience, but this is not the most
 * efficient usage.</p>
 * @see java.util.HashMap
 * @see java.util.Map
*/
public class IntHashMap extends Object implements Map
{
    private Entry       table[];
    private int         count;
    private int         threshold;
    private float       loadFactor;

    /*===================================================================
        Private static classes
      ===================================================================*/
    private static class IntHashMapIterator implements Iterator
    {
        boolean         keys;
        int             index;
        Entry           table[];
        Entry           entry;

        IntHashMapIterator(Entry table[], boolean keys)
    	{
    	    super();
        	this.table = table;
        	this.keys = keys;
        	this.index = table.length;
    	}

        /*===================================================================
            Iterator interface
          ===================================================================*/
        public boolean hasNext()
        {
            if (entry != null) {
                return true;
            }
            while (index-- > 0) {
                if ((entry = table[index]) != null) {
                    return true;
                }
            }
            return false;
        }

        public Object next()
        {
            if (entry == null) {
                while ((index-- > 0) && ((entry = table[index]) == null)) {
                    /* do nothing */
                }
            }
            if (entry != null) {
                Entry       e = entry;

                entry = e.next;
                return keys ? new Integer(e.key) : e.value;
            }
            throw new NoSuchElementException("IntHashMapIterator");
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove");
        }
    }

    /*===================================================================
        Public static classes
      ===================================================================*/
    public static class Entry extends Object
    {
        int         hash;
        int         key;
        Object      value;
        Entry       next;

        public Entry()
        {
            super();
        }
    }

    /*===================================================================
        Constructors
      ===================================================================*/
    public IntHashMap(int initialCapacity, float loadFactor)
    {
        super();
        if (initialCapacity <= 0 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity];
        threshold = (int)(initialCapacity * loadFactor);
    }

    public IntHashMap(int initialCapacity)
    {
        this(initialCapacity, 0.75f);
    }

    public IntHashMap()
    {
        this(101, 0.75f);
    }

    /*===================================================================
        Protected methods
      ===================================================================*/
    protected void rehash(boolean wait)
    {
        int         oldCapacity = table.length;
        Entry       oldTable[] = table;
        int         newCapacity = oldCapacity * 2 + 1;
        Entry       newTable[] = new Entry[newCapacity];
        
    	try {
    		Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        threshold = (int)(newCapacity * loadFactor);
        table = newTable;
        for (int i = oldCapacity ; i-- > 0 ;) {
            for (Entry old = oldTable[i] ; old != null;) {
                Entry       e = old;
                int         index = ( e.hash & 0x7FFFFFFF ) % newCapacity;

                old = old.next;
                
                if (wait) {
                	try {
                		Thread.currentThread().sleep(2000);
                	} catch (InterruptedException e1) {
                		e1.printStackTrace();
                	}
                }
                e.next = newTable[index];
                System.out.print(Thread.currentThread().toString());
                if (e!=null) {
                	System.out.print("======e:"+e.key);
                	if (e.next!=null) {
                		System.out.print("======next:"+e.next.key);
                	} else {
                		System.out.print("======next:null");
                	}
                }
                System.out.println();
                newTable[index] = e;
            }
        }
        System.out.println("rehash finish");
    }

    /*===================================================================
        Public methods
      ===================================================================*/
    public final boolean containsKey(int key)
	{
        int         index = (key & 0x7FFFFFFF) % table.length;

        for (Entry e = table[index] ; e != null ; e = e.next) {
            if ((e.hash == key) && (e.key == key)) {
                return true;
            }
        }
        return false;
	}

    public final Object get(int key)
    {
        int         index = (key & 0x7FFFFFFF) % table.length;

        for (Entry e = table[index] ; e != null ; e = e.next) {
            if ((e.hash == key) && (e.key == key)) {
                return e.value;
            }
        }
        return null;
    }

    public final Object put(int key, Object value,boolean wait)
    {
        int         index = ( key & 0x7FFFFFFF ) % table.length;

        if (value == null) {
            throw new IllegalArgumentException();
        }
        for (Entry e = table[index] ; e != null ; e = e.next) {
            if ((e.hash == key) && (e.key == key)) {
                Object      old = e.value;

                e.value = value;
                return old;
            }
        }

        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded.
        	System.out.println("rehash:["+key+"]");
            rehash(wait);
            return put(key, value,wait);
        }

        Entry       e = new Entry();

        e.hash = key;
        e.key = key;
        e.value = value;
        e.next = table[index];
        table[index] = e;
        ++count;
        return null;
    }

    public final Object remove(int key)
    {
        int         index = (key & 0x7FFFFFFF) % table.length;

        for (Entry e = table[index], prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hash == key) && (e.key == key)) {
                if ( prev != null ) {
                    prev.next = e.next;
                } else {
                    table[index] = e.next;
                }
                --count;
                return e.value;
            }
        }
        return null;
    }

    /*===================================================================
        Map interface
      ===================================================================*/
    public int size()
    {
    	return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public Object get(Object key)
	{
        if (!(key instanceof Number)) {
            throw new IllegalArgumentException("key is not an Number subclass");
        }
        return get(((Number)key).intValue());
	}

    public Object put(Object key, Object value)
    {
        if (!(key instanceof Number)) {
            throw new IllegalArgumentException( "key cannot be null" );
        }
        return put(((Number)key).intValue(), value );
    }

    public void putAll(Map otherMap)
    {
        for (Iterator it = otherMap.keySet().iterator(); it.hasNext();) {
            Object      k = it.next();

            put(k, otherMap.get(k));
        }
    }

    public Object remove(Object key)
	{
        if (!(key instanceof Number)) {
            throw new IllegalArgumentException("key cannot be null");
        }
    	return remove(((Number)key).intValue());
	}

    public void clear()
	{
        Entry     tab[] = table;

        for (int index = tab.length; --index >= 0;) {
            tab[index] = null;
        }
        count = 0;
	}

    public boolean containsKey(Object key)
	{
        if (!(key instanceof Number)) {
            throw new InternalError( "key is not an Number subclass" );
        }
        return containsKey(((Number)key).intValue());
	}

    public boolean containsValue(Object value)
    {
        Entry       tab[] = table;

        if (value == null) {
            throw new IllegalArgumentException();
        }
        for (int i = tab.length ; i-- > 0;) {
            for (Entry e = tab[i] ; e != null ; e = e.next ) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set keySet()
    {
        Set     result = new HashSet();

        for (Iterator it = new IntHashMapIterator(table, true); it.hasNext();) {
            result.add(it.next());
        }
        return result;
    }

    public Collection values()
    {
        List        result = new ArrayList();

        for (Iterator it = new IntHashMapIterator(table, false); it.hasNext();) {
            result.add(it.next());
        }
        return result;
    }

    public Set entrySet()
    {
        throw new UnsupportedOperationException("entrySet");
    }
}



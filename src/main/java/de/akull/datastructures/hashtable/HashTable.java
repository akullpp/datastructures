package de.akull.datastructures.hashtable;

@SuppressWarnings({"WeakerAccess", "unchecked"})
class HashTable<K, V> {

    static final int INCREASE_FACTOR = 2;
    static final int HASH_FACTOR = 31;
    static final double LOAD_FACTOR = 0.75;

    int capacity = 10;

    int size = 0;
    LinkedList<K, V>[] buckets = new LinkedList[capacity];

    int hash(String key) {
        int hash = 0;
        // O(N)
        char[] chars = key.toCharArray();

        // O(N)
        for (char c : chars) {
            hash = HASH_FACTOR * hash + c;
        }
        return hash;
    }

    void ensureCapacity() {
        if (size >= Math.floor(capacity * LOAD_FACTOR)) {
            size = 0;
            LinkedList<K, V>[] tmp = buckets;
            buckets = new LinkedList[capacity *= INCREASE_FACTOR];

            for (LinkedList<K, V> bucket : tmp) {
                if (bucket != null) {
                    Node<K, V> current = bucket.head;
                    while (current != null) {
                        put(current.key, current.value);
                        current = current.next;
                    }
                }
            }
        }
    }

    int computeIndex(K key) {
        return Math.abs(hash(key.toString()) % buckets.length);
    }

    // O(1)
    void put(K key, V value) {
        ensureCapacity();

        int index = computeIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        buckets[index].append(new Node<>(key, value));
        size++;
    }

    V get(K key) {
        int index = computeIndex(key);

        Node<K, V> current = buckets[index].head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
}

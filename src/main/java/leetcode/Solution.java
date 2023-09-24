package leetcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        String data = "data.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(data)));
        int n = Integer.parseInt(reader.readLine());
        HashTable table = new HashTable(n * 10);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            int key = Integer.parseInt(parts[1]);

            if (command.equals("put")) {
                int value = Integer.parseInt(parts[2]);
                table.put(key, value);
            } else if (command.equals("get")) {
                Integer result = table.get(key);
                if (result == null) {
                    sb.append("None").append("\n");
                } else {
                    sb.append(result).append("\n");
                }
            } else {
                Integer result = table.delete(key);
                if (result == null) {
                    sb.append("None").append("\n");
                } else {
                    sb.append(result).append("\n");
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}

class HashTable {
    private final Entry[] table;
    private final int capacity;

    public HashTable(int initialCapacity) {
        capacity = findNextPrime(initialCapacity);
        table = new Entry[capacity];
    }

    public int calculateHashCode(int key, int i) {
        int hash1 = (key & 0x7FFFFFFF) % capacity;
        int hash2 = 1 + ((key & 0x7FFFFFFF) % (capacity - 1));
        return hash1 + hash2 * i;
    }

    public int getBucketIndex(int hashCode) {
        return Math.abs(hashCode) % capacity;
    }

    public void put(int key, int value) {
        int i = 0;
        while (true) {
            int hashCode = calculateHashCode(key, i);
            int index = getBucketIndex(hashCode);

            if (table[index] == null || table[index].deleted) {
                table[index] = new Entry(key, value);
                return;
            } else {
                if (table[index].key == key) {
                    table[index].value = value;
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    public Integer get(int key) {
        int i = 0;
        while (true) {
            int hashCode = calculateHashCode(key, i);
            int index = getBucketIndex(hashCode);

            Entry entry = table[index];
            if (entry == null) {
                return null;
            } else if (entry.key == key && !entry.deleted) {
                return entry.value;
            } else {
                i++;
            }
        }
    }

    public Integer delete(int key) {
        int i = 0;
        while (true) {
            int hashCode = calculateHashCode(key, i);
            int index = getBucketIndex(hashCode);

            Entry entry = table[index];
            if (entry == null) {
                return null;
            } else if (entry.key == key && !entry.deleted) {
                entry.deleted = true;
                return entry.value;
            } else {
                i++;
            }
        }
    }

    private int findNextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

class Entry {
    int key;
    int value;
    boolean deleted;

    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }
}

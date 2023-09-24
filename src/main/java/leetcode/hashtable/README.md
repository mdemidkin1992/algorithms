### Description

* To store pairs (k,v) in an array, the Entry class is implemented with the fields int key, int value, boolean deleted.
Implemented the HashTable class, which contains an array of such objects of the Entry class.

* Array capacity is set as a large prime number to reduce the likelihood of collisions
(number of requests 10 - if this is not a prime number, it is incremented by 1 until it becomes prime).
  
* The problem of collisions in the hashtable was solved using the open addressing and probing method.
A double hash function is used with a probing step i for the second hash in case of collisions.
The bucket index in the table is calculated by taking the modulo hash of the array size.

* When adding values (put method), it is checked whether the bucket is busy, and if it is busy, the hash / index is recalculated with the new i.
The value is written to the first free bucket. If the keys match, the value of the bucket is updated.

* When retrieving values (get method) by hash / bucket index, the key value is checked, if the key matches,
the value is returned. If the first empty bucket is hit at the probing step i, null is returned.

* When deleting a value (the delete method), it occurs in a “lazy” way by changing the deleted flag of Entry to true.
(i.e. old values are not removed from the table). New values can be added to the bucket using the put method if deleted = true.

### Time complexity
In total, the time complexity of the algorithm directly depends on the number of operations - O(n).
- Calculating the hash value and index takes O(1).
- Each operation (put, get, delete) in the for loop takes O(1), since the array is accessed by index
- The probability of collisions and the need for probing (i++) is not taken into account, since the hash function and the calculation of the bucket index occurs in an efficient way, giving a uniform distribution of indices

### Space complexity
* Algorithm memory also depends on the number of operations since the array size takes n as input for calculation
capacity. The total memory allocation is O(n * 10).

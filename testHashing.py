import random

class HashEntry:

    def __init__(self, key=None, value=None):
        self.key = key
        self.value = value


class HashTable_LinearProbing:

    def __init__(self, capacity=1000003):
        self.capacity = capacity
        self.size = 0
        self.probes_get = 0
        self.probes_put = 0 
        self.data = [None] * capacity

    def size(self):
        return self.size

    def is_empty(self):
        if self.size == 0:
            return True
        else:
            return False

    def get(self, key, count=False):
        i = abs(hash(key)) % self.capacity

        while self.data[i] is not None:
            if count:
                self.probes_get += 1
            if self.data[i].key == key:
                return self.data[i].value
            i = (i + 1) % self.capacity
        return None

    def put(self, item, count=False):

        i = abs(hash(item.key)) % self.capacity

        if count:
            self.probes_put += 1

        while self.data[i] is not None:
            if self.data[i].value == "REMOVED":
                break
            if self.data[i].key == item.key:
                break
            i = (i + 1) % self.capacity
            if count:
                self.probes_put += 1

        self.data[i] = item
        self.size += 1

    def remove(self, key):
        i = abs(hash(key)) % self.capacity

        while self.data[i] is not None:
            if self.data[i].key == key:
                temp = self.data[i]
                self.data[i] = HashEntry(i, "REMOVED")
                self.size -= 1
                return temp
            i = (i + 1) % self.capacity
        return None

    def get_probes(self, t):
        if t == 0:
            return self.probes_get
        elif t == 1:
            return self.probes_put
        else:
            return None

    def clear_probes(self):
        self.probes_put = 0
        self.probes_get = 0


class HashTable_DoubleHashing:

    def __init__(self, capacity=1000003):
        self.capacity = capacity
        self.size = 0
        self.probes_get = 0
        self.probes_put = 0
        self.data = [None] * capacity

    def size(self):
        return self.size

    def is_empty(self):
        if self.size == 0:
            return True
        else:
            return False

    def get(self, key, count=False):

        i = abs(hash(key)) % self.capacity
        j = (i + 1) % (self.capacity - 2)

        while self.data[i] is not None:
            if count:
                self.probes_get += 1
            if self.data[i].key == key:
                return self.data[i]
            i = (i + j) % self.capacity
        return None

    def put(self, item, count=False):

        i = abs(hash(item.key)) % self.capacity
        j = (i + 1) % (self.capacity - 2)

        if count:
            self.probes_put += 1
        while self.data[i] is not None:
            if self.data[i].value == "REMOVED":
                break
            if self.data[i].key == item.key:
                break
            i = (i + j) % self.capacity
            if count:
                self.probes_put += 1

        self.data[i] = item
        self.size += 1

    def remove(self, key):

        i = abs(hash(key)) % self.capacity
        j = (i + 1) % (self.capacity - 2)

        while self.data[i] is not None:
            if self.data[i] == key:
                temp = self.data[i]
                self.data[i] = HashEntry(i, "REMOVED")
                return temp
            i = (i + j) % self.capacity
        return None

    def get_probes(self, t):
        if t == 0:
            return self.probes_get
        elif t == 1:
            return self.probes_put
        else:
            return None

    def clear_probes(self):
        self.probes_put = 0
        self.probes_get = 0


class main:

    size = 1000003
    data = []
    loads = [0.25, 0.5, 2/3.0, 0.75, 0.8, 0.9, 0.95]
    search = []
    hash_linear = HashTable_LinearProbing()
    hash_double = HashTable_DoubleHashing()
    old_index = 0
    n = round(0.01 * size)

    for x in range(size):
        data.append(random.randint(1, 1000000000))

    print("Average probes:")

    for load in loads:

        print("\nLoad factor = ", load)
        factor_index = round(load*size)
        search.clear()

        for x in range(old_index, factor_index):  # fills table with values up to load factor
            hash_linear.put(HashEntry(data[x]))
            hash_double.put(HashEntry(data[x]))

        for i in range(n):  # fills search array with random values from table
            r = random.randint(0, factor_index)
            search.append(data[r])

        for s in search:  # searches table for known values
            hash_linear.get(s, True)
            hash_double.get(s, True)

        for k in range(factor_index, factor_index + n):  # unsuccessful searches
            hash_linear.put(HashEntry(data[k]), True)
            hash_double.put(HashEntry(data[k]), True)

        print("\tSuccessful search: ")
        print("Linear probing: ", hash_linear.get_probes(0) / n)
        print("Double hashing: ", hash_double.get_probes(0) / n)
        print("\tUnsuccessful search: ")
        print("Linear probing: ", hash_linear.get_probes(1) / n)
        print("Double hashing: ", hash_double.get_probes(1) / n)

        hash_linear.clear_probes()
        hash_double.clear_probes()
        old_index = factor_index


if __name__ == "__main__":
    main()

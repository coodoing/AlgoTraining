package Searching.HashFunc;

public class HTable {
	private int[] hash_table;
	private int MAX_NUM;

	public HTable(int max) {
		MAX_NUM = max + 10;
		hash_table = new int[MAX_NUM];
	}
	
	public int[] getHashTable()
	{
		return hash_table;
	}

	public int hashValue(int value) {
		return (int) (value % MAX_NUM);
	}

	public void insert(int value) {
		hash_table[hashValue(value)] = hashValue(value);
	}

	public boolean find(int value) {
		if (hash_table[hashValue(value)] == hashValue(value))
			return true;
		else
			return false;
	}
}

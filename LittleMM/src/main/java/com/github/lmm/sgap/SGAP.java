package com.github.lmm.sgap;

import java.util.Arrays;
import java.util.Vector;

public class SGAP {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		Vector testData = new Vector(Arrays.asList(1,2,3,4,5));
		Vector results = getAllCombinations(testData);
		for(int i=0; i<results.size(); i++)
			
			System.out.println(results.elementAt(i));
		//System.out.println(results.size());
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static Vector getAllCombinations(Vector data)
	{
		Vector allCombinations = new Vector();
		for(int i=1; i<=data.size(); i++)
		{
			Vector initialCombination = new Vector();
			allCombinations.addAll(getAllCombinations(data, i));
		}

		return allCombinations;
	}

	@SuppressWarnings("rawtypes")
	public static Vector getAllCombinations(Vector data, int length)
	{
		Vector allCombinations = new Vector();
		Vector initialCombination = new Vector();
		combination(allCombinations, data, initialCombination, length);
		return allCombinations;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void combination(Vector allCombinations, Vector data, 
		Vector initialCombination, int length)
	{
		if(length == 1)
		{
			for(int i=0; i<data.size(); i++)
			{
				Vector newCombination = new Vector(initialCombination);
				newCombination.add(data.elementAt(i));
				allCombinations.add(newCombination);
			}
		}

		if(length > 1)
		{
			for(int i=0; i<data.size(); i++)
			{
				Vector newCombination = new Vector(initialCombination);
				newCombination.add(data.elementAt(i));
				Vector newData = new Vector(data);
				for(int j=0; j<=i; j++)
					newData.remove(data.elementAt(j));
				combination(allCombinations, newData, newCombination, length - 1);
			}
		}
	}
	
	

}

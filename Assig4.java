// CS 0445 Spring 2020
// Assig4.java
// ARCHANA RAJASEHARAN
// 4262349

import java.util.*;
public class Assig4
{
	
	public static Random R = new Random();
	
	// Data will be an ArrayList of Sorter<T> objects.
	private ArrayList<Sorter<Integer>> sorts;

	private Integer [] A;	
	private int size;
	private int totalRuns;
	private static boolean isDataSorted;
	private int minVal = 3;
	static double [][] arrayData = new double[4][15];
	static double [][] arrayAve = new double [4][4];
	
	public static void main(String [] args)
	{
		
		new Assig4(args[0], args[1], args[2]);
		
		double bestAve = 100;
		double worstAve = 0;
		
		int minValBest = 0;
		int minValWorst = 0;
		
		// traversing through min recurse to determine the best and worst average time for each algorithm 
		// ie. determine the best & worst average time in each row -- each row represents a different algo
		for(int row = 0; row < 4; row++) {
			
			bestAve = 100;
			worstAve = 0;
			
			for(int col = 0; col < 15; col++) {
				
				if(arrayData[row][col] < bestAve) {
					bestAve = arrayData[row][col];
					minValBest = col;
				}
				
				if(arrayData[row][col] > worstAve) {
					worstAve = arrayData[row][col];
					minValWorst = col;
				}
				
			}

			int setCol = 0;
			
			// ******************* Save arrayAve in this format *********************
			// 0,0  0,1  0,2  0,3 (best, worst, minValBest, minValWorst for Simple Piv)
			// 1,0  1,1  1,2  1,3 (best, worst, minValBest, minValWorst for MedOfThree)
			// 2,0  2,1  2,2  2,3 (best, worst, minValBest, minValWorst for Random Piv)
			// 3,0  3,1  3,2  3,3 (best, worst, minValBest, minValWorst for MergeSort)
			
			arrayAve[row][setCol] = bestAve;
			arrayAve[row][setCol + 1] = worstAve;
			arrayAve[row][setCol + 2] = (minValBest * 5) + 3; // use index to determine the min recurse val
			arrayAve[row][setCol + 3] = (minValWorst * 5) + 3; // use index to determine the min recurse val
 		}
		
		// determine the best average time overall (compared to different algorithm)
		int c = 0;
		double saveB = arrayAve[0][c]; // set it to first respective  coordinate - special case
		double bestMinRec = arrayAve[0][2]; // set it to its first respective coordinate - special case
		int saveRowBest = 0;
		double saveBestAlgo = arrayAve[0][c]; // set it to first respective  coordinate - special case
	
		for(int r = 1; r < 4; r++) {
			
			if(saveB > arrayAve[r][c]) { 
				saveBestAlgo = arrayAve[r][c];
				saveB = arrayAve[r][c];
				bestMinRec = (int) arrayAve[r][c+2];
				saveRowBest = r; 
			}
		}
		
		// determine what algo it is to print out in the final output
		String printAlgoBest = "";
		if(saveRowBest == 0)
			printAlgoBest = "Simple Pivot QuickSort";
		else if(saveRowBest == 1)
			printAlgoBest = "Median of Three QuickSort";
		else if(saveRowBest == 2)
			printAlgoBest = "Random Pivot QuickSort";
		else if(saveRowBest == 3)
			printAlgoBest = "MergeSort";
		
		// determine the worst average time overall (compared to different algorithm)
		c = 1;		
		double saveW = arrayAve[0][c]; // set it to first respective  coordinate - special case
		double worstMinRec = arrayAve[0][3]; // set it to first respective  coordinate - special case
		int saveRowWorst = 0;
		double saveWorstAlgo = arrayAve[0][c]; // set it to first respective  coordinate - special case
		
		for(int r = 1; r < 4; r++) {
			
			if(saveW < arrayAve[r][c]) { 
				saveWorstAlgo = arrayAve[r][c];
				saveW = arrayAve[r][c];
				worstMinRec = (int) arrayAve[r][c+2];
				saveRowWorst = r;
			}
		}
		
		// determine what algo it is to print out in the final output
		String printAlgoWorst = "";
		if(saveRowWorst == 0)
			printAlgoWorst = "Simple Pivot QuickSort";
		else if(saveRowWorst == 1)
			printAlgoWorst = "Median of Three QuickSort";
		else if(saveRowWorst == 2)
			printAlgoWorst = "Random Pivot QuickSort";
		else if(saveRowWorst == 3)
			printAlgoWorst = "MergeSort";
		
		// determine whether data is random or sorted to print out in the final output
		String printSorted = "";
		if(isDataSorted == false) 
			printSorted = "Random";
		
		else if(isDataSorted == true) 
			printSorted = "Sorted";	
			
		System.out.println("After the tests, here is the best setup:\n" + 
				"	Algorithm: " + printAlgoBest + "\n" + 
				"	Data status:	" + printSorted + "\n" + 
				"	Min Recurse:	" + bestMinRec + "\n" + 
				"	Average:	" + saveBestAlgo + " sec\n");
		
		System.out.println("After the tests, here is the worst setup:\n" + 
				"	Algorithm: " + printAlgoWorst + "\n" + 
				"	Data status:	" + printSorted + "\n" + 
				"	Min Recurse:	" + worstMinRec + "\n" + 
				"	Average:	" + saveWorstAlgo + " sec\n");
		
		System.out.println("Here are the per algorithm results:\n" + 
				"	Algorithm: Simple Pivot QuickSort\n" + 
				"	Best Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[0][2] +"\n" + 
				"		Average: " + arrayAve[0][0] + " sec\n" + 
				"	Worst Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[0][3] + "\n" + 
				"		Average: " + arrayAve[0][1] + "sec\n");
		
		System.out.println("Algorithm: Median of Three QuickSort\n" + 
				"	Best Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[1][2] +"\n" + 
				"		Average: " + arrayAve[1][0] + " sec\n" + 
				"	Worst Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[1][3] + "\n" + 
				"		Average: " + arrayAve[1][1] + "sec\n");
		
		System.out.println("Algorithm: Random Pivot QuickSort\n" + 
				"	Best Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[2][2] +"\n" + 
				"		Average: " + arrayAve[2][0] + " sec\n" + 
				"	Worst Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[2][3] + "\n" + 
				"		Average: " + arrayAve[2][1] + "sec\n");
		
		System.out.println("Algorithm: MergeSort\n" + 
				"	Best Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[3][2] +"\n" + 
				"		Average: " + arrayAve[3][0] + " sec\n" + 
				"	Worst Result:\n" + 
				"		Min Recurse: " + (int)arrayAve[3][3] + "\n" + 
				"		Average: " + arrayAve[3][1] + "sec");
		
		
	}
	
	public Assig4(String sz, String runs, String isSorted)
	{
		size = Integer.parseInt(sz);
		totalRuns = Integer.parseInt(runs);
		isDataSorted = Boolean.parseBoolean(isSorted);
		
		// Put the sorting objects into the ArrayList
		sorts = new ArrayList<Sorter<Integer>>();
		sorts.add(new QuickSort<Integer>(new SimplePivot<Integer>()));
		sorts.add(new QuickSort<Integer>(new MedOfThree<Integer>()));
		sorts.add(new QuickSort<Integer>(new RandomPivot<Integer>()));
		sorts.add(new MergeSort<Integer>());
		
		A = new Integer[size];
		
		// cumulative time taken for each sort
		double saveSimplePivot = 0;
		double saveMedOfThree = 0;
		double saveRandPiv = 0;
		double saveMergeSort = 0;
		
		// average time taken for each sort
		double aveSimplePiv = 0;
		double aveMedOfThree = 0;
		double aveRandPiv = 0;
		double aveMergeSort = 0;
		
		int r = 0;
		int c = 0;
		
		//increase the minimum recurse by 5
		for(int minVal = 3; minVal <= 73; minVal += 5) {
			
			saveSimplePivot = 0;
			saveMedOfThree = 0;
			saveRandPiv = 0;
			saveMergeSort = 0;
			
			// execute all runs
			for(int j = 0; j < totalRuns; j++) {
				
				// Iterate through all of the sorts and test each one				
				for (int i = 0; i < sorts.size(); i++) {
			
					R.setSeed(123456);  // This will enable all sorts to use the same data.  If
					// you have multiple runs with the same algorithm you should only
					// set this one time for each algorithm so that the different runs
					// will have different data.
				
					// fill array from fillArray method with boolean isDataSorted as the parameter
					fillArray(isDataSorted);
			
					// Get the current Sorter<T> object, set the min and sort the data
					sorts.get(i).setMin(minVal);
			
					// start the timer to time the sorting algorithm
					long nano_startTime = System.nanoTime(); 
					// sort algorithm
					sorts.get(i).sort(A, A.length);
					// end timer after sort is complete
					long nano_endTime = System.nanoTime(); 

					// find the total time taken for sort
					double diffTime = (nano_endTime - nano_startTime);
					// convert time taken from nano seconds to seconds
					diffTime = (double) (diffTime * Math.pow(10,  -9));
				
					// add all time taken for each run, specific to the sort
					if(i == 0)
						saveSimplePivot += diffTime;
					else if(i == 1)
						saveMedOfThree += diffTime;
					else if(i == 2)
						saveRandPiv += diffTime;
					else if(i == 3)
						saveMergeSort += diffTime;

				}
			}
			
			// save results to 0,0, 1,0, 2,0, 3,0
			// save results to 0,1, 1,1, 2,1, 3,1
			
			// find the average time taken for each sort
			aveSimplePiv = saveSimplePivot/totalRuns;
			aveMedOfThree = saveMedOfThree/totalRuns;
			aveRandPiv = saveRandPiv/totalRuns;
			aveMergeSort = saveMergeSort/totalRuns;
				
			// assign the average time taken for each sort into arrayData
			arrayData [r][c] = aveSimplePiv;
			arrayData [r+1][c] = aveMedOfThree;
			arrayData [r+2][c] = aveRandPiv;
			arrayData [r+3][c] = aveMergeSort;
			
			// c - column - represents each minimum recurse (up to 15)
			c++; 
		}
	}
	
	// Fill array with random data
	public void fillArray(boolean isSorted)
	{
		// random data
		if(isSorted == false) {
			
			for(int i = 0; i < A.length; i++)
			{
				// Values will be 0 <= X < 1 billion
				A[i] = new Integer(R.nextInt(1000000000));
			}
		}
		
		// sorted data
		else if(isSorted == true) {
			
			for(int i = 0; i < A.length; i++)
			{
				A[i] = new Integer(R.nextInt(1000000000));
			}
			
			// insertion sort the random data so it is presorted 
			 int n = A.length; 
		        for (int i = 1; i < n; ++i) { 
		            int key = A[i]; 
		            int j = i - 1; 
		  
		    
		            while (j >= 0 && A[j] > key) { 
		                A[j + 1] = A[j]; 
		                j = j - 1; 
		            } 
		            A[j + 1] = key; 
			}
		        
		}
	}

}

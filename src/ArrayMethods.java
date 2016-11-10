import java.util.*;
public class ArrayMethods {
	private int[] values;
	public ArrayMethods(int[] initialValues) {
		values = initialValues;
	}
	public void swapFirstAndLast() {
		int firstVal = values[0];
		int lastVal = values[values.length-1];
		values[0] = lastVal;
		values[values.length-1] = firstVal;
	}
	public void shiftRight() {
		for(int c = 0; c<values.length; c++){
			if(c==values.length-1){
				values[0] = values[c];
			}
			else {
				values[c+1] = values[c];
			}
		}
	}
	public void evenToZero() {
		for(int c = 0; c<values.length; c++){
			if(values[c]%2==0){
				values[c]=0;
			}
		}
	}
	public void toBiggerElement() {
		int ans[] = new int[values.length];
		int c;
		for(c = 0; c<values.length;c++){
			if(c==0 || c==values.length-1){ //for the first and last elements
				ans[c] = values[c];
			}
			else if(values[c-1]>values[c+1]){ //if the one to the left is bigger
				ans[c] = values[c-1];
			}
			else{ //if the one to the right is bigger or they tie (since then it wouldn't matter)
				ans[c]=values[c+1];
			}
		}
		for(c=0;c<values.length;c++){
			values[c]=ans[c];
		}
	}
	public void removeMiddle() {
		if(values.length%2==0){ //even
			values[values.length/2] = 0;
			values[(values.length/2)-1] = 0; //because the array indices are 1 less than the length, the other middle is one back
		}
		else{ //odd
			values[values.length/2] = 0;
		}
	}
	public void evenToFront() {
		int buildCount = 0; //because we're going through values[] twice
		int build[] = new int[values.length]; //this will be funneled back to values[] at the end
		for (int c1 = 0; c1 < values.length; c1++){ //putting even numbers into build[]
			if(values[c1]%2==0){ //if the element is even
				build[buildCount] = values[c1];
				buildCount++;
			}
		}
		for (int c2 = 0; c2 < values.length; c2++){ //putting odd numbers into build[]
			if (values[c2]%2!=0){ //if the element is odd
				build[buildCount] = values[c2];
				buildCount++;
			}
		}
	}
	public int returnSecondLargest() {
		int largest = 0;
		int secondLargest = 0; //these are given values so the loop doesn't yell at me
		for (int c = 1; c < values.length; c++){
			if (c == 1){ //sets the largest and second largest, so values[0] doesn't get forgotten
				if(values[c]>values[c-1]){
					largest = values[c];
					secondLargest = values[c-1];
				}
				else { //ties don't really matter here, since they'd both be the largest and second largest at this point
					largest = values[c-1];
					secondLargest = values[c];
				}
			}
			else{ //for the rest of the array
				if(values[c]==values[c-1]){ //in case of a tie
					if (values[c]>largest) { //if the values are the largest
						largest = values[c]; //both of these would be filled since technically they ARE the largest and second largest
						secondLargest = values[c]; //c or c-1 doesn't matter since they're the same in this case
					}
					else if(values[c]>secondLargest) { //in case these values are not the largest but the second largest
						secondLargest = values[c]; //again, c or c-1 doesn't matter here
					} //if neither of these are fulfilled, then the tie doesn't matter anyway and nothing happens
				}
				else if (values[c]> values[c-1]){ //c is larger
					if (values[c] > largest) { //c is the largest element
						secondLargest = largest; //the former largest is now the second largest
						largest = values[c]; //c is now the largest
					}
					else if (values[c] > secondLargest) { //c is the second largest
						secondLargest = values[c];
					} //if neither of these re fulfilled, nothing happens
				}
				else { //c-1 is larger, since that's the only other possible scenario at this point
					if (values[c-1] > largest) { //c-1 is the largest element
						secondLargest = largest; //the former largest is now the second largest
						largest = values[c-1]; //c-1 is now the largest
					}
					else if (values[c-1] > secondLargest) { //c-1 is the second largest
						secondLargest = values[c-1];
					} //if neither of these re fulfilled, nothing happens
				}
			}
		}
		return secondLargest;
	}
	public boolean isSorted() {
		int sortedValues[] = new int[values.length];
		for (int c1 = 0; c1<values.length;c1++){ //setting them equal so I don't mess with values[] at all
			sortedValues[c1] = values[c1];
		}
		Arrays.sort(sortedValues);
		return Arrays.equals(values, sortedValues); //the test of equality
	}
	public boolean hasAdjacentDuplicate() {
		for (int c = 1; c < values.length; c++) {
			if (values[c] == values[c-1]) { //if they're equal
				return true; //because this only needs to happen once
			}
		}
		return false; //if the loop ends without returning true, then there are no duplicate element and must be false
	}
	public boolean hasDuplicate() {
		int comparedValues[] = new int[values.length];
		for (int c1 = 0; c1 < values.length; c1++) {
			comparedValues[c1] = values[c1]; //setting these equal so they can be compared
		}
		for (int c2 = 0; c2 < comparedValues.length; c2++) { //comparedValues.length equals values.length, but it keeps what the loops do straight in my head
			for (int c3 = 0; c3 < values.length; c3++) {
				if (c2 != c3) { //so the same elements are never compared, otherwise this method would always be true
					if(comparedValues[c2] == values[c3]) {
						return true;
					}
				}
			}
		}
		return false; //if the for loop exits, the method is false
	}
}

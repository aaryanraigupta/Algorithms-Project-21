import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArrivalTime {

	public static void main(String[] args) throws IOException {
		String file = "/Users/seanlysaght/eclipse-workspace/AlgosGroupProject/src/stop_times.txt";		
		int size = size(file);
		String[] test1 = lineSplit(file, size);		
		int columns = columns(test1);
		String[][] test = individualSplit(test1, size, columns);
		//String[] allTime = allTime(test,size);
		//String[][] validTime = validTime(test, size, allTime);
		//String[][] test2 = individualTimeSplit(allTime, size);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the desired arrival time in the format - hh:mm:ss");
		String input = scanner.nextLine();
		input = input.replaceFirst("^0*", "");
		ArrayList<String> tripID = searchTime(test, size, input);
		//System.out.print(Arrays.deepToString(test2));
		tripPrint(test, size, tripID); 
	}


	static int size(String file) throws IOException {
		int size = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (br.readLine() != null) {						
				size++;
			}	
		}
		catch(FileNotFoundException e) {

		}		
		return size;
	}

	static int columns(String[] string) {
		int columns = 0;
		String[] arr = string[1].split(",");
		columns = arr.length + 1;
		return columns;
	}

	static String[] lineSplit(String file, int size) throws IOException {

		String[] arr = new String[size];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for(int i = 0; i < size; i++) {
				arr[i] = br.readLine();
			}
		}
		catch(FileNotFoundException e) {

		}
		return arr;
	}

	static String[][] individualSplit(String[] string, int size, int columns)  {

		String[] arr = string[1].split(",");		
		String[][] fullFile = new String[size][columns];
		for(int i = 0; i < size; i++) {
			string[i] = string[i].replaceAll(", ", ",");
			arr = string[i].split(",");
			for(int j = 0; j < arr.length ; j++) {
				fullFile [i][j] = arr[j];
			}
		}

		return fullFile;
	}

	static ArrayList<String> searchTime(String[][] fullFile, int size, String input) {		
		ArrayList<String> tripID = new ArrayList<String>();		
		for (int i = 0; i < size; i++) {          						
			if (fullFile[i][1].equals(input)) {				
				tripID.add(fullFile[i][0]);	
			}
		}				
		return tripID;
	}

	/*	static String[] allTime (String[][] file, int size){
		String[] allTime = new String[size];		
		for (int i = 0; i < size; i++) {          						
			allTime[i] = file[i][1];
		}				
		return allTime;
	}

	static String[][] individualTimeSplit(String[] string, int size)  {	
		String[][] fullTimeFile = new String[size][3];
		for(int i = 0; i < size; i++) {			
			String[] arr = new String[3];
			arr = string[i].split(":");
			for(int j = 0; j < 3 ; j++) {
				fullTimeFile [i][j] = arr[j];
			}
		}

		return fullTimeFile;
	}

	static String[][] validTime (String[][] file, int size, String[] allTime){

		int columns = 0;
    	String[] arr = file[1][1].split(",");
		columns = arr.length + 1;		
		String[][] validTime = new String[size][columns];

		for (int i = 0; i < size; i++) {          						
		for(int j = 0; j < columns; j++) {
			for(int x = 0; x < allTime.length; x++) {
				//	if (allTime[x] = "1" ) {
					{
					validTime[i][j] = file[i][j];
					}
				}
			}		
		}
		return validTime;
	} */



	static void tripPrint(String[][] time, int size, ArrayList<String> tripID) {

		System.out.println("Here are all of the journeys that feature this arrival time!");
		for (int i = 0; i < size; i++) {
			for(int j = 0; j < tripID.size(); j++) {
				if (time[i][0].equals(tripID.get(j))) {					
					System.out.println("This is stop " + time[i][4] + " of the journey");
					System.out.println("The trip id is - " + time[i][0]);
					System.out.println("The arrival time is - " + time[i][1]);
					System.out.println("The departure time is - " + time[i][2]);
					System.out.println("The stopID is - " + time[i][3]);
					System.out.println("The stop headsign is - " + time[i][5]);
					System.out.println("The pick up type is - " + time[i][6]);
					System.out.println("The drop off type is - " + time[i][7]);
					System.out.println("The shape distance traveled is - " + time[i][8]);
					System.out.println(" ");
				}
			}
		}
	}	
}

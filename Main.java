package csvReader;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static final String HASHMAP_FILE = "C:\\Users\\picka\\Downloads\\ipad CSV\\hashmap.ser";

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//input file and output here:
		String fileName = "";
		String outputFile = "";
		Scanner keyboard = new Scanner(System.in);
		
		Scanner sc = null;
		PrintWriter pw = null;
        HashMap<String, String> tf = null;
		
		if(fileName == null)
		{
			System.out.println("Paste the location of your input file CSV:");
			fileName = keyboard.nextLine();
		}
		
		if(outputFile == null) 
		{
			System.out.println("Paste the location of where you would like your output file:");
			outputFile = keyboard.nextLine();
		}
		
		try {
			sc = new Scanner(new File(fileName));
			pw = new PrintWriter(new File(outputFile));
			System.out.println("Files opened");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		
		//Initialize HashMap if it does not exist
		//Can be modified to be different. (KEY, VALUE)
		tf = deserializeHashMap();
		if(tf == null)
		{
			tf = new HashMap<>();
			tf.put("1011952093", "STATE");
			tf.put("1014151692", "FED");
			tf.put("1011348782", "STATE");
		}
		
		
		//skip first line
		if (sc.hasNextLine()) {
            sc.nextLine();
        }
		
		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			String[] columns = line.split(",");
			
			 //checks to see if there is valid columns
			if(columns.length >= 1 && columns.length >= 9)
			{
				try {
					String orderNum = columns[8];
                    
					//checks HashMap for order number	
                    if(tf.containsKey(orderNum))
                    {
                        System.out.print(orderNum + ", " + columns[0] + ", ");
                        System.out.println(tf.get(orderNum));
                        //write to outputFile
                        pw.println(columns[0] + ", " + tf.get(orderNum) + ", " + orderNum);
                    }
                    else
                    {
                    	// Key not found, add to the HashMap with a default value or custom
                    	String defaultValue = " ";
                    	
                    	System.out.println("Enter what funding " + columns[0] + ", " + columns[8] + " will be or press enter: ");
                    	defaultValue = keyboard.nextLine();
                    	
                    	if (defaultValue.isBlank())
                    	{
                    		defaultValue = "UNKNOWN";
                    	}
                    	
                    	//puts new key into HashMap
                        tf.put(orderNum, defaultValue);
                        System.out.println("Added new key: " + orderNum + " with value: " + defaultValue);
                        
                        //write to outputFile
                        pw.println(columns[0] + ", " + defaultValue + ", " + orderNum);
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in column 9: " + columns[8]);
                    //Handle nonInteger values
                    pw.println(columns[0] + ", UNKNOWN");
                }
			}
			else
			{
				System.out.println("Does not have 9th column" + line);
			}
			//lineNumber++;
		}
		sc.close();
		pw.close();
        keyboard.close();
        
        serializeHashMap(tf);
        System.out.println("OutputFile written to, now closing...");
	}
	
	private static void serializeHashMap(HashMap<String, String> tf) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HASHMAP_FILE))) {
            oos.writeObject(tf);
            System.out.println("HashMap serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static HashMap<String, String> deserializeHashMap() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HASHMAP_FILE))) {
            return (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("HashMap file not found, starting with a new one.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
	

}

package csvReader;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class HashMapReader {
    //Add path for .ser file here. (HashMap):
    private static final String HASHMAP_FILE = "";
	
	public static void main(String[] args) {
		HashMap<String, String> tf = null;
		Scanner keyboard = new Scanner(System.in);
		
		tf = deserializeHashMap();
		
		System.out.println("The current map looks like: ");
		System.out.println(tf.keySet());
		System.out.println(tf.values());
		
		boolean flag = true;

		//Is used to add another key with its associated value.
		while(flag)
		{
			System.out.println("Enter a key or press enter to exit");
			String key = keyboard.nextLine();
			//keyboard.nextLine();
			
			if(key.isBlank())
			{
				flag = false;
				continue;
			}
			System.out.println("Enter the value associated with the key");
			String value = keyboard.nextLine();
			
			tf.put(key, value);
		
		}
		
		serializeHashMap(tf);
		keyboard.close();
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

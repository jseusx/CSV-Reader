# CSV-Reader
A Java script which will read an Apple School Manager CSV file and output its correlated funding information based on previous data stored/its order number.

## How it works/Setup:
First, create a folder that will store your files. 3 will be used in total, your input, output, and HashMap. The HashMap will be used as a means of storing the previous order numbers to retrieve the funding information quicker.

Using Apple School Manager (ASM), in devices input the desired serial numbers into the search menu. Then, download it to the folder you made previously.

> [!NOTE]
> Having a code editor can be helpful here.

If you are comfortable editing code you can choose to change the file locations to make it permanent:
```java
private static final String HASHMAP_FILE = "D:\\csv Reader\\hashmap.ser";

	public static void main(String[] args) {
		//input file and output
		String fileName = "D:\\csv Reader\\Last 1000.csv";
		String outputFile = "D:\\csv Reader\\ipad.csv";
```
Here **"fileName"** is the input file, the CSV file you downloaded from ASM.\
**"outputFile"** is the variable used to store the location of where the output file will be stored. If it is your first time running the program, you can either create an empty .csv file in the folder and copy its location or get the location of the folder and just put the name.
**HASHMAP_FILE** this will be the file that contains the serialized HashMap file. If this file is not found, it will create one where the location is set. For example, in the code above, it would be called "hashmap.ser" and be created in the same folder as the rest of the files.

Initially, your HashMap file will be empty as you will have no information. 

```java
if(tf == null)
		{
			tf = new HashMap<>();
			tf.put("1011952093", "STATE");
			tf.put("1014151692", "FED");
			tf.put("1011348782", "STATE");
		}
```

This is the default information stored. However, you are free to change it depending on if you have any previous information. Here the format is: **(Key, Value)**. Where Key is the order number and the Value is the funding. 
As you run the program you will be prompted to enter an order numbers funding information if it is not found, it will automatically be stored.

Once you run the program and are finished, your output file will be made and the HashMap will be serialized for future use.

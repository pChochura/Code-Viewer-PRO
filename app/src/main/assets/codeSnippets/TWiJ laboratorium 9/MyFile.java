import java.io.File;
import java.io.IOException;
public class MyFile {
	public void main(String[] args) {
		try {
			File file = new File("./Myfile.txt");
			if (file.createNewFile()) {
				System.out.println("Success!");
			} else {
				System.out.println("Error, file already exists.");
			}
			FileWriter writer = new FileWriter(file);
			writer.write("My file test data");
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
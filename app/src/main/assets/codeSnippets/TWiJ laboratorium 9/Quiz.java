import java.io.*;
import java.util.*;
public class Quiz {
	public static void main(String[] args) {
		int sum = 0;
		try {
			Scanner file = new Scanner(new File("questions.txt"));
			Scanner user = new Scanner(System.in);
			while (file.hasNext()) {
				for (int i = 0; i < 4; i++) {
					System.out.println(file.nextLine());
				}
				String ok = file.nextLine();
				System.out.println("What is the correct ? ");
				String ans = user.next();
				ans = ans.toUpperCase();
				if (ans.length() > 1) {
					ans = ans.substring(0, 1);
				}
				if (ans.equals(ok)) {
					sum++;
					System.out.println("OK !\n");
				} else {
					System.out.println("NO !\n");
				}
			}
			System.out.println("\nResult:" + sum + " of 3");
		} catch (FileNotFoundException e) {
			System.out.println("No questions !");
		}
	}
}
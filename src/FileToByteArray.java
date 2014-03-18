import java.io.File;
import java.io.FileInputStream;


public class FileToByteArray {
	public static byte[] convert(File fileName) {
		byte[] b = null;
		try {
			b = new byte[(int) fileName.length()];
			FileInputStream fis = new FileInputStream(fileName);
			fis.read(b);
		} catch(Exception e) {
			System.out.println("Exception in file to byte conversion: ");
			e.printStackTrace();
		}
		return b;	
	}
}

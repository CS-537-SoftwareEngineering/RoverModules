package MAHLI;



import java.io.File;

public class MainModule {

	public static void main(String[] args) {
		File file = new File("mahli_zinc.jpg");
		 ProcessImage objprocessImage=new ProcessImage();
			System.out.println(objprocessImage.getImageColor(file));

	}

}

import java.io.IOException;

public class PrintTextLocations {
    public static void main( String[] args ) {
    	
    	try {
    		//Read the pdf file
    		ReadPdf readpdf = new ReadPdf();
			readpdf.ReadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long startTime = System.nanoTime();
    	// Get the connections between sections
    	LogicSpace logicSpace = new LogicSpace();
        logicSpace.getText();
		long endTime   = System.nanoTime();
		double totalTime = endTime - startTime;
		System.out.println(totalTime/1000000000 +" Sec");
    }

}

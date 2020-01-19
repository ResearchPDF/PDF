public class AfterFilter {

	//LogicSpace logicSpace = new LogicSpace();
	String[][] valuesPair = LogicSpace.valuesPair;
	
	void removeDublicates() {
		
		for(int i=0; i<100 ;i++) {
			if(valuesPair[i][0]!=null & valuesPair[i][5]==null) {
				String keyword = valuesPair[i][0];
				for(int j=0; j<100 ;j++) {
					///System.out.println("Keyword : " + valuesPair[i][0] +"\tValue : "+ xyz[1]);
					if(valuesPair[j][0]!=null && valuesPair[j][1].equals(keyword)) {
						//System.out.println("Keyword : ");
						valuesPair[j][6]="1";
						
						break;
					}
				}
				//System.out.println("Keyword : " + xyzz[0] +"\tValue : "+ xyzz[1]);
				
			}
		
			//System.out.println("Keyword : " + xyzz[0] +"\tValue : "+ xyzz[1] +"\tValue : "+ xyzz[5]);
    	}
	}
	
	void printResult() {
		for(String[] xyzz : valuesPair) {
			if(xyzz[0]!=null & xyzz[5]==null  & xyzz[6]==null) {
				//System.out.println("Keyword : " + xyzz[0] +"\tValue  : "+ xyzz[1]);
			}
			//System.out.println("Keyword : " + xyzz[0] +"\tValue : "+ xyzz[1] +"\tValue : "+ xyzz[6]);
    	}
	}
}
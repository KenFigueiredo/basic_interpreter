package interpreter;

import java.util.regex.Pattern;

public class Logic {
	
	private Functions f;
	private String defaultStep = "1";
	
	private Pattern forPattern = Pattern.compile("^FOR [A-Za-z]*=\\d*.\\d* TO \\d*.\\d*");
	private Pattern forStepPattern = Pattern.compile("^FOR [A-Za-z]*=\\d*.\\d* TO \\d*.\\d* STEP \\d*.\\d*");
	private Pattern nextPattern = Pattern.compile("^NEXT [A-Za-z]*");
	
	public Logic(Functions f){
		this.f = f;
	}
	
		public String FOR(String s){
			String[] arg = s.split(" ");

			//"Case 1: for loop w/o set step e.g.: "FOR I=1 TO 5"
				if(arg.length == 4){ 
					if(forPattern.matcher(s).matches()){
						String var = arg[1].split("=")[0];
						
						return "for(int " + arg[1] + ";" + var + "<" + arg[3] + ";" 
								+ var + "+=" + defaultStep + "){\n";					
					}
					
					else
						return "ERROR: FOR LOOP FORMATTED INCORRECTLY\n";
				}
			
			//"Case 2: for loop with set step e.g.: "FOR I=1.96 TO 5.48 STEP 0.0037"
				else if(arg.length == 6){
					if(forStepPattern.matcher(s).matches()){
						String var = arg[1].split("=")[0];
						
						return "for(double " + arg[1] + ";" + var + "<" + arg[3] + ";" 
						+ var + "+=" + arg[5] + "){\n";						
					}
					
					else
						return "ERROR: FOR-STEP LOOP FORMATTED INCORRECTLY\n";
				}
			
			//if no format fail and return nullstring.
				else
					return "ERROR: INCORRECT USE OF FOR LOOP\n";
		}	
		
		public String FORdescript(){
			return "Usage: \n" +
					"FOR number_variable=number_value TO number_value [STEP number_value] \n" +
					"Description: \n" +
					"Executes all lines between FOR and NEXT as loop with counter. \n" +
					"Lines are executed as long as counter is less than final value (given after word TO), but at least one time. \n" +
					"After every executing counter is increased by value given after word STEP (if isn't present, it is assumed 1). \n" +
					"Step value can be also less then zero. Loops can also be embedded. \n" +
					"Examples: \n" +
					"10 S=0:FOR I=1 TO 100:S=S+I:NEXT I \n" +
					"10 FOR I=1.96 TO 5.48 STEP 0.0037 \n";
		}
		
		public String NEXT(String s){
			if(nextPattern.matcher(s).matches())
				return "}";
			
			else
				return "ERROR: NEXT INCORRECTLY FORMATTED\n";	
		}
		
}

//Kelvin David 1345360
//Junit Runner
//----------------------------------
//This class is responsible for running the
//written MTFTests I created

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

public class MTFRunner
{
	public static void main(String[] args) 
   	{
     		Result result = JUnitCore.runClasses(MTFTests.class); //run MTFTests.class
		
     		for (Failure failure : result.getFailures()) //for each failure
      		{
         		System.out.println(failure.toString()); //show failed method and expected/output
      		}
		
		//Output:  [if all tests are sucessfull [Number of tests run] [number failed] [number sucessfull
      		System.out.println("All tests successful: " + result.wasSuccessful() +
      		" (" + result.getRunCount() + " Tests, " +  "Failed: "
      		+ result.getFailureCount() + ", " + "Successful: "  +
      		(result.getRunCount() - result.getFailureCount()) + ")"); 
   	}
}

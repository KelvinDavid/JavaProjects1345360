//Kelvin David 1345360
//This class runs the tests for the Hash Table class

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

public class HashRunner
{
	public static void main(String[] args) 
   	{
     		Result result = JUnitCore.runClasses(HashTests.class);
		
     		for (Failure failure : result.getFailures()) 
      		{
         		System.out.println(failure.toString());
      		}
		
      		System.out.println("All tests successful: " + result.wasSuccessful() + 
      		" (" + result.getRunCount() + " Tests, " +  "Failed: "
      		+ result.getFailureCount() + ", " + "Successful: "  +
      		(result.getRunCount() - result.getFailureCount()) + ")");
   	}
}

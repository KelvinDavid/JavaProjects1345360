//Kelvin David 1345360
//JUnitRunner for BSTlex
//--------------------------------
//this runs the test for the
//BSTlex
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

class BSTRunner
{
	public static void main(String [] args)
	{
     		Result result = JUnitCore.runClasses(BSTTester.class);
		
     		for (Failure failure : result.getFailures()) //for each fail
      		{
         		System.out.println(failure.toString()); //show where it failed
      		}
		
		System.out.println("");
		//this block prints if all succeed which failed and which passed
		System.out.println("===================================================");
      		System.out.println("All tests successful: " + result.wasSuccessful() + 
      		" (" + result.getRunCount() + " Tests, " +  "Failed: "
      		+ result.getFailureCount() + ", " + "Successful: "  +
      		(result.getRunCount() - result.getFailureCount()) + ")");	
	}
}

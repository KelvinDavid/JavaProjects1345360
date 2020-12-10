
Project created by:
Daniel Wilson ID: 1345359
Kelvin David  ID: 1345360

****Running Project****

Project was created through Vagrant VM and this did not allow us to run "./c.sh and ./r0.sh"
we also found that we couldn’t compile different instances of the robots from the same folder, 
(Even when the projects had finished compiling) as such we had to have each of the different robots as separate folders copied over.

The ports are mapped using the ID of the robot by adding to the base port number used during compilation (If robot id is "1", it will 
be added to the passed in ROBOT_PORT to be port "10000 + 1 = 10001"

so, all robots should be compiled using:

make all PORT=tap<rID> ROBOT_ID=<rID> ROBOT_PORT=10000 term

and controller should be compiled as normal:

PORT=<tap> make all term ROBOT_ADDRESSES=<Robot_IP_1>,<Robot_IP_2>,<.......>


**** Automation Techniques: ****

The following project aims to automate the actions in the following ways:

- splitting a map up evenly between the number of robots and sends through jobs messages with the starting coordinates, end coordinates
 for a robot to navigate on its own without needing other instructions from the controller

 	- If there is one robot is passed into the controller the entire area is assigned

	- If there is an even number of robots the width of the map is split in half and then the height of the map is split by 
	  half the number of robots to have 2 even groups working either side.

	- If there is an odd number of robots, the number of robots - 1 are split through the even method, and the last robot is set aside as a backup for if others
 	  run out of energy or blow up

- Robots continually communicate their position back to the controller for this data to be used in building a map which
the user and controller can view, 

- Half-finished jobs are automatically reassigned to idle robots who have finished searching their area, The point where a robot needs to go
to resume a job is communicated to the robot rather than having the entire job be performed from the start. Robots will carry on searching the area while there are still tiles left to 
be searched

- In the case that a robot suddenly goes missing (Times out for 3 seconds), the program is designed to predict the locations of bombs through the controller keeping track of the pattern 
of movement that a robot would have made and using the last known coordinates, (It always goes left to begin with, once it reaches the bounds of its area it will 
go down, then it will start going right etc) These predictions are sent out to all robots before they are assigned a new job in order to allow for them 
to store the coordinates and make their way around the mines safely on their path. If a robot has previously sent out a "Low energy" message that it can’t move, then 
the controller would not treat the disappearance as a bomb blowing up the robot. 


Communication Strategy:

In order to keep messages short and easy to understand, CSV values with different keywords are used to identify the type of message being sent or received from the controller or robots.
this and the way that communication updates are only sent from the robot during traveling makes the energy used for communicating quite a bit more efficient than constant 
backward and forward messages for commands and updates.





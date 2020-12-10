Hash Fuction Trial and Error Log
========================================

	LOGGED HASH METHODS USED

		int hc1 = ((intialHash % 10) * 100000) + (intialHash / 10);
		int key = (hc0 + hc1) % capacity;	
		
		int hc0 = (intialHash % 10) * 100000;
		int hc1 = intialHash / 10;
		int hc2 = intialHash % 10;
		int key = (hc0 + hc1 + hc2) % capacity;
		
		int hc0 = ((intialHash / 100) % 100) * ((intialHash / 100) % 100);
		int hc0 = ((intialHash % capacity) + (intialHash / capacity));
		int hc1 = ((intialHash % 10) * 100000) + (intialHash / 10);
		int key = (hc1 + hc0) % capacity;
		

		int hc0 = (intialHash % 10) * 100000;
		int hc1 = intialHash / capacity;		
		int hc2 = intialHash / 10;				//Spread primary Cluster
		int key = (hc0 + hc1 + hc2) % capacity;

		int hc0 = intialHash % capacity;
		int hc1 = intialHash / 10;			//No Collisions till = Load [15.4] (PRIMARY CLUSTER)
		int key = (hc0 + hc1) % capacity;	//But less end collisions
		
		int hc0 = (intialHash % 10) * 100000;
		int hc1 = intialHash / capacity;		//No Collisions till = Load [15.4]		
		int key = (hc0 + hc1) % capacity;		
		
		int hc0 = (intialHash % 10) * 100000;
		int hc1 = intialHash / 10;			//No Collisions till = Load [13.3] 
		int key = (hc0 + hc1) % capacity;
		
		int hc0 = (intialHash / 100) % 100;
		int hc1 = (intialHash / 100) % 100;	//No Collisions till = Load [6.9]
		int key = (hc0 * hc1) % capacity;
			
		int hc0 = (intialHash / 100000) * 100;
		int hc1 = ((intialHash / 1000) % 10) * 10;	//sNo Collisions till = Load [8.4]
		int hc2 = intialHash % 10;
		int key = (hc0 + hc1 + hc2) % capacity;	
	
		int hc0 = (intialHash / 100000) * 100;
		int hc1 = ((intialHash / 1000) % 10) * 10;	//sNo Collisions till = Load [8.4] 
		int hc2 = intialHash % 10;
		int key = (hc0 + hc1 + hc2) % capacity;
		
		int hc0 = intialHash % capacity;
		int hc1 = intialHash / capacity;	//No Collisions till = Load [15.2]
		int key = (hc0 + hc1) % capacity;
		
		
	All Attemps on making less collisions resulted in an increase in collisions
	I couldn't implement any of the hash methods correctly to be able to decrease
	the amount of collisions.
	
	I instead just played with the re-hash skip to be able to get the least amount of 
	collisions.
	
	Instead of creating a hash code using:
	
		hash + ascii value:
		hash *= 256
		
		i used ((hash % 10) * 100000) + (hash / 10) to minimise the collisons as 
		much as I could
		
	
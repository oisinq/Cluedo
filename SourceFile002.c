
#pragma config(Sensor, S3,     colour,         sensorEV3_Color)
#pragma config(Sensor, S1,     Touch,          sensorEV3_Touch)
#pragma config(Sensor, S2,     Gyro,           sensorEV3_Gyro, modeEV3Gyro_Angle)
#pragma config(Sensor, S4,     sonar4,         sensorEV3_Ultrasonic)
#pragma config(Motor,  motorB,          motorLeft,     tmotorEV3_Large, PIDControl, encoder)
#pragma config(Motor,  motorC,          motorRight,    tmotorEV3_Large, PIDControl, encoder)

task main()
{
	int touchCheck=0;
	//step 1 drive forward
	//maybe try a while loop that is if touched turn else drive straight ahead
	while(touchCheck==0){
	if(SensorValue[Touch]){
		touchCheck++;

	}
	else{
		motor[motorC] = 0;
 		motor[motorB] = 80;

		sleep(100);
	}

	}


	//step 2 turn
	//turnLeft(1, 0.3, 50);
	motor[motorLeft] = 100;
	motor[motorRight] = 0;
	sleep(1000);



	//step 3 follow the line
	 while(SensorValue(getColorName(colorRed)) )
 {
		motor[motorC] = 0;
 		motor[motorB] = 80;
 }

}
//while(SensorValue(lightSensor) >= 45)
// {
 		//motor[motorB] = 80;
 	//	motor[motorC] = 0;
 //}


	//step 4 find the hole
	const int distanceToMaintain = 30;

	int currentDistance = 0;
  int wallFound = 0;
	while(wallFound==0)
	{
		// Read the sensor
		currentDistance = SensorValue[sonar4];
		displayCenteredBigTextLine(4, "Dist: %3d cm", currentDistance);

		// We're too far away, move forward
		if ((distanceToMaintain - currentDistance) < -2)
		{
			wallFound++;
		}
		// We're too close, move backwards
		else if ((distanceToMaintain - currentDistance) > 2)
		{
			motor[motorLeft] = -30;
			motor[motorRight] = -30;
		}
		// We're good, don't go anywhere


		//Loop to monitor value in Sensor debugger window
		sleep(100);
	}

	//turn and drive in
	//turnRight(1, 0.25, 50);
	motor[motorLeft] = 0;
	motor[motorRight] = 50;
	sleep(1000);
  //setMultipleMotors(100, motorB, motorC);
	motor[motorLeft] = 100;
	motor[motorRight] = 100;
  sleep(500);
  //turnLeft(1, 0.25, 50);
  motor[motorLeft] = 50;
	motor[motorRight] = 0;
  sleep(1000);
  //setMultipleMotors(100, motorB, motorC);//reverse after her
  motor[motorLeft] = 100;
	motor[motorRight] = 100;
  sleep(500);
  //setMultipleMotors(-100, motorB, motorC);
  motor[motorLeft] = -100;
	motor[motorRight] = -100;
  sleep(500);
  //turnLeft(1, 0.25, 50);
  motor[motorLeft] = 50;
	motor[motorRight] = 0;
  sleep(500);
  motor[motorLeft] = 100;
	motor[motorRight] = 100;
  sleep(1000);
  //turnLeft(1, 0.35, 50);
    motor[motorLeft] = 50;
	motor[motorRight] = 0;
	sleep(1000);
  //step 5 , get home
  motor[motorLeft] = 100;
	motor[motorRight] = 100;
  sleep(5000);


}

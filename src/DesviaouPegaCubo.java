import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.*;

public class DesviaouPegaCubo implements Behavior {

    private UltrasonicSensor sonarCubo;
    private DifferentialPilot pilot;
    private Navigator navigator;
    private RegulatedMotor garra = Motor.A;
    private ColorSensor colorSensor;

    public DesviaouPegaCubo(){
	this.pilot = Pilot.getPilot();
	this.navigator = Pilot.getNavigator();
	this.sonarCubo = new UltrasonicSensor(SensorPort.S1);
	colorSensor = new ColorSensor(SensorPort.S4);
    }
	
    public boolean takeControl() {
	sonarCubo.ping();
	return sonarCubo.getDistance() < 10;
    }
	
    public void suppress() {		
    }
		
    public void action() {
	garra.rotate(-50);
	pilot.setTravelSpeed(10);		
	pilot.travel(15);
	// Identifica cubo
	if (colorSensor.getColorID() == Color.BLUE) {
	    PegaCubo();
	} 
	else {
	    Desvia();
	}
    }
	
    private void PegaCubo(){
	garra.rotate(65);
    }
	
    private void Desvia(){
	pilot.travel(-15);
	pilot.rotate(90);
	pilot.travel(15);
	pilot.rotate(-90);
	pilot.travel(30);
	pilot.rotate(-90);
	pilot.travel(15);
	pilot.rotate(90);
    }
}

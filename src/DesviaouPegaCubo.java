import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;

public class DesviaouPegaCubo implements Behavior {

	private UltrasonicSensor sonarCubo;
	private DifferentialPilot pilot;
	private Navigator navigator;
	private RegulatedMotor garra = Motor.A;
	private ColorSensor colorSensor;
	private boolean suppressed;
	
	public DesviaouPegaCubo(){
		this.pilot = Pilot.getPilot();
		this.navigator = Pilot.getNavigator();
		this.sonarCubo = new UltrasonicSensor(SensorPort.S1);
		colorSensor = new ColorSensor(SensorPort.S4);
		garra.setSpeed(55);
		pilot.setRotateSpeed(45);
	}
	
	public boolean takeControl() {
		return !Pilot.pegou;
	}
	
	public void suppress() {
		suppressed = true;
	}
		
	public void action() {
		int cor;
		LCD.clear();
		LCD.drawString("DESVIA CUBO", 0, 0);
		suppressed = false;
		Delay.msDelay(300);
		pilot.setTravelSpeed(22);
		pilot.forward();
		while (sonarCubo.getDistance() > 11) ;
		pilot.stop();
		garra.rotate(-65);
		pilot.travel(16);
		Delay.msDelay(500);
		pilot.setTravelSpeed(2);
		pilot.forward();
		while ((cor = colorSensor.getColorID()) == 7 || cor == 1) ;
		pilot.stop();
		LCD.drawInt(cor, 0, 2);
		// Identifica cubo
		pilot.setTravelSpeed(22);
		if (cor == Color.BLUE) {
			PegaCubo();
			Pilot.pegou = true;
		}

		else {
			Desvia();
		}
	}
	private void PegaCubo(){
		pilot.travel(-3);
		garra.rotate(68);
	}
	
	private void Desvia(){
		Pose p;
		pilot.travel(-12);
		p = navigator.getPoseProvider().getPose();
		navigator.singleStep(true);
		if (Pilot.contaGol == 0) {
			navigator.addWaypoint(p.getX() + 22, p.getY() - 22);
			navigator.addWaypoint(p.getX() + 45, 40, 0);
		} else {
			navigator.addWaypoint(p.getX() - 25, p.getY() + 22);
			navigator.addWaypoint(p.getX() - 45, -40, 180);
		}
		navigator.followPath();
        navigator.waitForStop();
		garra.rotate(65);
	}
}

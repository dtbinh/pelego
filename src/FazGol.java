import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.*;

public class FazGol implements Behavior {
	private UltrasonicSensor parede;
	private DifferentialPilot pilot;
	private Navigator navigator;
	private RegulatedMotor garra = Motor.A;
	private ColorSensor colorSensor;
	private boolean supressed;
	
	public FazGol() {
		this.pilot = Pilot.getPilot();
		this.navigator = Pilot.getNavigator();
		this.parede = new UltrasonicSensor(SensorPort.S1);
		garra.setSpeed(55);
	}
	
	public boolean takeControl() {
		return Pilot.pegou;
	}

	public void action(){
		LCD.clear();
		LCD.drawString("FAZ GOL", 0, 0);
		supressed = false;
		garra.setSpeed(45);
		pilot.setRotateSpeed(45);
		pilot.setTravelSpeed(22);
		
		
		
		while (!supressed) {
			if (Pilot.contaGol == 0) {
				pilot.forward();
				while (parede.getDistance() > 10) ;
				pilot.stop();	
				pilot.rotate(-90);
				pilot.travel(40);
				pilot.rotate(90);
				pilot.forward();
				while (parede.getDistance() > 10) ;
				pilot.stop();
				Pilot.posicao = navigator.getPoseProvider().getPose();
				garra.rotate(-65);
				pilot.travel(-14);
				garra.rotate(65);
				pilot.travel(11);
				pilot.travel(-10);
				pilot.rotate(180);
			}
			
			else {
				navigator.singleStep(true);
				navigator.goTo(Pilot.posicao.getX(), Pilot.posicao.getY() - 2, Pilot.posicao.getHeading()-360);
				navigator.followPath();
		        navigator.waitForStop();
				garra.rotate(-65);
				pilot.travel(-14);
				garra.rotate(65);
				pilot.travel(15);
			}
			pilot.reset();
			Pilot.pegou = false;
			Pilot.fezGol = true;
			Pilot.contaGol ++;
			suppress();
		}		
	}


	@Override
	public void suppress() {
		supressed = true;
		
	}
	
}

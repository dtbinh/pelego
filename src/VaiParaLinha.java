import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.*;

public class VaiParaLinha implements Behavior {

	private boolean supressed = false;
	private DifferentialPilot pilot;
	private Navigator navigator;
	private RegulatedMotor garra = Motor.A;
	
	public VaiParaLinha() {
		this.pilot = Pilot.getPilot();
		this.navigator = Pilot.getNavigator();
		OdometryPoseProvider position = new OdometryPoseProvider (pilot);
		Pose tmp = new Pose(0.0f, 0.0f, 0.0f);
        position.setPose(tmp);
		garra.setSpeed(40);
	}
	
	@Override
	public boolean takeControl() {
		if (Pilot.comecou() || Pilot.fezGol) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void action() {
		if (Pilot.comecou) garra.rotate(-15);
		Pilot.comecou = false;
		Pilot.fezGol = false;
		LCD.clear();
		LCD.drawString("VAI PRA LINHA", 0, 0);
		
		pilot.setTravelSpeed(20);  // cm por segundo
        pilot.setRotateSpeed(35);  // graus por segundo
        
        Pose p = navigator.getPoseProvider().getPose();
        // Força ele a parar em cada ponto
        navigator.singleStep(true);
        if (Pilot.contaGol == 0)
        	navigator.addWaypoint(0, 40.0f, 0);
        else
        	navigator.addWaypoint(p.getX(), - 40.0f, 175);
        navigator.followPath();
        navigator.waitForStop();
	}

	@Override
	public void suppress() {
		supressed = true;
	}

}

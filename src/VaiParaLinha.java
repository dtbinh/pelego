import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.*;

public class VaiParaLinha implements Behavior {

	private boolean supressed = false;
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		DifferentialPilot pilot = Pilot.getPilot();
		Navigator navigator = Pilot.getNavigator();
		
        pilot.setTravelSpeed(20);  // cm por segundo
        pilot.setRotateSpeed(45);  // graus por segundo

        // Força ele a parar em cada ponto
        navigator.singleStep(true);

        navigator.addWaypoint(0.0f, 40.0f, 0);
        
        navigator.followPath();
        navigator.waitForStop();
        
        while(!supressed){
        	
        }
	}

	@Override
	public void suppress() {
		supressed = true;
	}

}

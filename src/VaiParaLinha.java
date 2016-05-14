import lejos.nxt.*;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.*;

public class VaiParaLinha implements Behavior {

    private boolean supressed = false;
    private DifferentialPilot pilot;
    private Navigator navigator;
    private RegulatedMotor garra = Motor.A;

    public VaiParaLinha() {
	this.pilot = Pilot.getPilot();
	this.navigator = Pilot.getNavigator();
    }

    @Override
    public boolean takeControl() {
	return true;
    }

    @Override
    public void action() {
	
        pilot.setTravelSpeed(20);  // cm por segundo
        pilot.setRotateSpeed(45);  // graus por segundo

        // Força ele a parar em cada ponto
        navigator.singleStep(true);
        navigator.addWaypoint(0.0f, 40.0f, 0);
        navigator.followPath();
        navigator.waitForStop();
 	
	// Procura Cubo
	garra.rotate(-15);
    	pilot.forward(); 
        while(!supressed){
	    Thread.yield(); // Anda pra frente até achar cubo
        }
        pilot.stop();
        
    }

    @Override
    public void suppress() {
	supressed = true;
    }

}

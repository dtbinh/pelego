import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;


public class Pilot {
	
	static DifferentialPilot pilot = new DifferentialPilot(5.6f, 11.2f, Motor.C, Motor.B, true);
    static Navigator navigator = new Navigator (pilot);
    
    public static DifferentialPilot getPilot() {
    	return pilot;
    }
    
    public static Navigator getNavigator() {
    	return navigator;
    }
}

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.*;

public class Main {
	
	public static void main (String args[]){
		Behavior vaiLinha = new VaiParaLinha();
		Behavior desviaCubo = new DesviaouPegaCubo();
		Behavior fazGol = new FazGol();
		Behavior[] behaviorList	= { fazGol, desviaCubo, vaiLinha}; 
		Arbitrator arby	= new Arbitrator(behaviorList); 
		arby.start(); 
	}
	
}

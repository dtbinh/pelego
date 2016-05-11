import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.*;

public class Main {
	
	public static void main (String args[]){
		Behavior vaiLinha = new VaiParaLinha();
		// Juntar VaiPraLinha + ProcuraCubo
		/*Behavior procuraCubo = new ProcuraCubo();
		Behavior desviaCubo = new DesviaouPegaCubo();
		Behavior fazGol = new FazGol();
		Behavior Landmarks = new Landmarks();
		Behavior DeadReckoning = new DeadReckoning();*/
		Behavior[] behaviorList	= { vaiLinha/*, procuraCubo, desviaCubo, fazGol, Landmarks, DeadReckoning*/ }; 
		Arbitrator arby	= new Arbitrator(behaviorList); 
		arby.start(); 
	}
	
}

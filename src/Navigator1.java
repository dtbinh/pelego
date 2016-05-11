import lejos.nxt.Motor;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.DifferentialPilot;

public class Navigator1 {
    static Navigator navigator;

    public static void main(String[] args) {
        //DifferentialPilot(diametro_roda, largura_robo, Motor1, Motor2, head)
        DifferentialPilot pilot = new DifferentialPilot(5.6f, 11.2f, Motor.C, Motor.B, false);
        navigator = new Navigator (pilot);

        pilot.setTravelSpeed(20);  // cm por segundo
        pilot.setRotateSpeed(45);  // graus por segundo

        // Força ele a parar em cada ponto
        navigator.singleStep(true);

        navigator.addWaypoint(0.0f, 40.0f, 0.0f);
//        navigator.addWaypoint(0.0f, 80.0f);
//        navigator.addWaypoint(-80.0f, 00.0f, 90.0f);

        navigator.followPath();
        navigator.waitForStop();
    }
}

package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import robot.Robot;
import sensors.ColorSensor;
import sensors.TouchSensor;
import sensors.UltrasonicSensor;

public class Test {
	//static List<Method> methodStore;
	
	final static Robot robot = new Robot();
	
	/*public Test() {
		methodStore = new ArrayList<Method>();
		try {
			methodStore.add(this.getClass().getMethod("testUltrasonicSensor"));
			methodStore.add(this.getClass().getMethod("testColorSensor"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {		
		//Test cls = new Test();
	    //Class c = cls.getClass();
		
		int button = 0;
		
		do {
			System.out.println("--Pour valider un test, appuyez sur la touche de droite, pour signaler un probl�me, appuyez sur la touche de gauche--");
			System.out.println("Le test va bient�t commencer...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Forward
			forward();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Travel
			travel();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Turn Left
			turnLeft();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Turn Right
			turnRight();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Touch sensor
			testTouchSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Ultrasonic sensor
			testUltrasonicSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Color sensor
			testColorSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
			
			//Sound test
			testSound();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
		
		} while (Button.ESCAPE.isUp());
		
		/*for(Method m : methodStore) {
			try {
				c.getMethod(m.getName());
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionn�.");
			}
			
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionn�.");
			}
		}*/
	}
	
	private static void forward() {
		System.out.println("--Test forward--");
		robot.forward();
		Delay.msDelay(3000);
		robot.stop();
		System.out.print("Le robot a avanc� pendant 3 secondes" + "\n" + "Est ce que le r�sultat vous parait correct ?");
		//si on clique tu le bouton � droit on fait robot.stop()
	}
	
	private static void travel() {
		System.out.println("--Test travel de 35--");
		robot.travel(35.0);
		robot.travel(-35.0);
		System.out.print("Le robot a avanc� de 35 cm et � recul� de 35cm" + "\n" + "Est ce que le r�sultat vous parait correct ?");
	}
	
	private static void turnLeft() {
		System.out.println("--Test tourner � gauche--");
		robot.turnLeft();
		System.out.print("Le robot a tourn� � gauche" + "\n" + "Est ce que le r�sultat vous parait correct ?");
	}

	private static void turnRight() {
		System.out.println("--Test tourner � droite--");
		robot.turnRight();
		System.out.print("Le robot a tourn� � droite" + "\n" + "Est ce que le r�sultat vous parait correct ?");
	}
	
	private static void testUltrasonicSensor() {
		System.out.println("--Test capteur ultrason--");
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		float sample = ultrasonicSensor.getDistance();
		System.out.println("La distance qui s�pare le capteur � ultrason de l'obstacle est de " + sample * 100 + ", ce r�sultat vous semble-t-il correct ?");
	}
	
	private static void testColorSensor() {
		System.out.println("--Test capteur couleur--");
		final ColorSensor colorSensor = new ColorSensor();
		System.out.println("Veuillez placer un objet rouge sous le capteur de couleur, ne le placez ni trop loin ni trop pr�s, cela pourrait fausser les r�sultats.");
		if(colorSensor.redIsFound()) {
			System.out.println("La couleur rouge a �t� d�tect�e, ce r�sultat vous semble-t-il correct ?");
		}
	}
	
	private static void testTouchSensor() {
		System.out.println("--Test Touch Sensor--");
		TouchSensor touchSensor = new TouchSensor();
		boolean testTouchSensor = touchSensor.isPressed();
		if (testTouchSensor == true) {
			System.out.println("Confirmez-vous qu'au moins un des capteurs tactiles a �t� press� ?");
		} else if (testTouchSensor == false) {
			System.out.println("Aucun des deux capteurs tactiles n'a �t� press�.");
		}
	}

	private static void testSound() {
		System.out.println("--Test son--");
		int playDuration;
		playDuration = Sound.playSample(new File("/home/root/secret.wav"));
		if (playDuration >= 0) {
			System.out.println("Confirmez-vous qu'un son a �t� jou� ?");
		}
	}
}

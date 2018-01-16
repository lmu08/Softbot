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
			System.out.println("--Pour valider un test, appuyez sur la touche de droite, pour signaler un problème, appuyez sur la touche de gauche--");
			System.out.println("Le test va bientôt commencer...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Forward
			forward();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Travel
			travel();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Turn Left
			turnLeft();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Turn Right
			turnRight();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Touch sensor
			testTouchSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Ultrasonic sensor
			testUltrasonicSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Color sensor
			testColorSensor();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
			
			//Sound test
			testSound();
			button = Button.waitForAnyPress();
			if(button == Button.ID_LEFT) {
				System.out.println("Le test n'a pas fonctionné.");
			}
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
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
				System.out.println("Le test n'a pas fonctionné.");
			}
			
			if(button == Button.ID_RIGHT) {
				System.out.println("Le test a pas fonctionné.");
			}
		}*/
	}
	
	private static void forward() {
		System.out.println("--Test forward--");
		robot.forward();
		Delay.msDelay(3000);
		robot.stop();
		System.out.print("Le robot a avancé pendant 3 secondes" + "\n" + "Est ce que le résultat vous parait correct ?");
		//si on clique tu le bouton à droit on fait robot.stop()
	}
	
	private static void travel() {
		System.out.println("--Test travel de 35--");
		robot.travel(35.0);
		robot.travel(-35.0);
		System.out.print("Le robot a avancé de 35 cm et à reculé de 35cm" + "\n" + "Est ce que le résultat vous parait correct ?");
	}
	
	private static void turnLeft() {
		System.out.println("--Test tourner à gauche--");
		robot.turnLeft();
		System.out.print("Le robot a tourné à gauche" + "\n" + "Est ce que le résultat vous parait correct ?");
	}

	private static void turnRight() {
		System.out.println("--Test tourner à droite--");
		robot.turnRight();
		System.out.print("Le robot a tourné à droite" + "\n" + "Est ce que le résultat vous parait correct ?");
	}
	
	private static void testUltrasonicSensor() {
		System.out.println("--Test capteur ultrason--");
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		float sample = ultrasonicSensor.getDistance();
		System.out.println("La distance qui sépare le capteur à ultrason de l'obstacle est de " + sample * 100 + ", ce résultat vous semble-t-il correct ?");
	}
	
	private static void testColorSensor() {
		System.out.println("--Test capteur couleur--");
		final ColorSensor colorSensor = new ColorSensor();
		System.out.println("Veuillez placer un objet rouge sous le capteur de couleur, ne le placez ni trop loin ni trop près, cela pourrait fausser les résultats.");
		if(colorSensor.redIsFound()) {
			System.out.println("La couleur rouge a été détectée, ce résultat vous semble-t-il correct ?");
		}
	}
	
	private static void testTouchSensor() {
		System.out.println("--Test Touch Sensor--");
		TouchSensor touchSensor = new TouchSensor();
		boolean testTouchSensor = touchSensor.isPressed();
		if (testTouchSensor == true) {
			System.out.println("Confirmez-vous qu'au moins un des capteurs tactiles a été pressé ?");
		} else if (testTouchSensor == false) {
			System.out.println("Aucun des deux capteurs tactiles n'a été pressé.");
		}
	}

	private static void testSound() {
		System.out.println("--Test son--");
		int playDuration;
		playDuration = Sound.playSample(new File("/home/root/secret.wav"));
		if (playDuration >= 0) {
			System.out.println("Confirmez-vous qu'un son a été joué ?");
		}
	}
}

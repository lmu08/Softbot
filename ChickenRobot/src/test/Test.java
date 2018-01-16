package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import sensors.ColorSensor;
import sensors.UltrasonicSensor;

public class Test {
	static List<Method> methodStore;
	
	public Test() {
		methodStore = new ArrayList<Method>();
		try {
			methodStore.add(this.getClass().getMethod("testUltrasonicSensor"));
			methodStore.add(this.getClass().getMethod("testColorSensor"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		Test cls = new Test();
	    Class c = cls.getClass();
		
		int button = 0;
				
		System.out.println("--Pour valider un test, appuyez sur la touche de droite, pour signaler un probl�me, appuyez sur la touche de gauche--");
		System.out.println("Le test va bient�t commencer...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Method m : methodStore) {
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
		}
	}
	
	private static void testUltrasonicSensor() {
		System.out.println("--Test capteur ultrason--");
		final UltrasonicSensor ultrasonicSensor = new UltrasonicSensor();
		float sample = ultrasonicSensor.getDistance();
		System.out.println("La distance qui s�pare le capteur � ultrason de l'obstacle est de " + sample * 100 + ", ce r�sultat vous semble-t-il correct ?");
	}
	
	private void testColorSensor() {
		System.out.println("--Test capteur couleur--");
		final ColorSensor colorSensor = new ColorSensor();
		System.out.println("Veuillez placer un objet rouge sous le capteur de couleur, ne le placez ni trop loin ni trop pr�s, cela pourrait fausser les r�sultats.");
		if(colorSensor.redIsFound()) {
			System.out.println("La couleur rouge a �t� d�tect�e, ce r�sultat vous semble-t-il correct ?");
		}
	}
}

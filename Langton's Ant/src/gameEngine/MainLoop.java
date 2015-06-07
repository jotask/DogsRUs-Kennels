package gameEngine;

public class MainLoop {

	private final static int TICKS_PER_SECOND = 25;
	private final static int SKIP_TICKS = 1000; //Ticks_per_seconds
	private final static int MAX_FRAMESKIP = 5;
	
	private static int loops;
	private static float interpolation;
	private static float nextGameTick = GetTickCount();
	
	private static boolean runGame = true;
	
	private static long GetTickCount(){
		return System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		while(runGame){
			loops = 0;
			while (GetTickCount() > nextGameTick && loops < MAX_FRAMESKIP){
				//update();
				nextGameTick += SKIP_TICKS;
				loops++;
			}
			interpolation = (float) (GetTickCount() + SKIP_TICKS - nextGameTick) / SKIP_TICKS;
			//render(interpolation);
		}
	}
	

}
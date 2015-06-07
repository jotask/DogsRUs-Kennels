import static org.lwjgl.glfw.GLFW.*; // allows us to create windows
import static org.lwjgl.opengl.GL11.*; // gives us access to things like "GL_TRUE" which we'll need 
import static org.lwjgl.system.MemoryUtil.*; // allows us to use 'NULL' in our code, note this is slightly different from java's 'null'
import java.nio.ByteBuffer; // Used for getting the primary monitor later on.
import org.lwjgl.glfw.GLFWvidmode; // again used for primary monitor stuff.

public class Main implements Runnable {

	private Thread thread;
	private boolean running = true;

	public static final String GAME_NAME = "Endless Runner";
	
	private long window;
	private static final int WIDTH = 400;
	private static final int HEIGTH = 300;

	public static void main(String[] args) {
		Main game = new Main();
		game.Start();
	}

	private void Start() {
		running = true;
		thread = new Thread(this, GAME_NAME);
		thread.start();
	}

	public void Init() {
		// Initializes our window creator library - GLFW
		// This basically means, if this glfwInit() doesn't run properlly
		// print an error to the console
		if (glfwInit() != GL_TRUE) {
			// Throw an error.
			System.err.println("GLFW initialization failed!");
		}

		// Allows our window to be resizable
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

		// Creates our window. You'll need to declare private long window at the
		// top of the class though.
		// We pass the width and height of the game we want as well as the title
		// for
		// the window. The last 2 NULL parameters are for more advanced uses and
		// you
		// shouldn't worry about them right now.
		window = glfwCreateWindow(WIDTH, HEIGTH, "Endless Runner", NULL, NULL);

		// This code performs the appropriate checks to ensure that the
		// window was successfully created.
		// If not then it prints an error to the console
		if (window == NULL) {
			// Throw an Error
			System.err.println("Could not create our Window!");
		}

		// creates a bytebuffer object 'vidmode' which then queries
		// to see what the primary monitor is.
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Sets the initial position of our game window.
		glfwSetWindowPos(window, 100, 100);
		// Sets the context of GLFW, this is vital for our program to work.
		glfwMakeContextCurrent(window);
		// finally shows our created window in all it's glory.
		glfwShowWindow(window);
	};

	public void Update() {
		// Polls for any window events such as the window closing etc.
		glfwPollEvents();
	};

	public void Render() {
		// Swaps out our buffers
		glfwSwapBuffers(window);
	};

	@Override
	public void run() {
		Init();
		while (running) {
			Update();
			Render();
			if(glfwWindowShouldClose(window) == GL_TRUE){
				running = false;
			}
		}
	}

}

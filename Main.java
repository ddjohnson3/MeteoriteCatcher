import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		IterableMultiKeyRBT<MeteoriteObjectInterface> rbt = new IterableMultiKeyRBT<>();
        BackendInterface backend = new Backend(rbt);
		
		Frontend frontend = new Frontend(backend, scanner);
		frontend.startCommandLoop();        
	}
}

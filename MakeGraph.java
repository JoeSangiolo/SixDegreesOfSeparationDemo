import java.util.Scanner;

// You shouldn't need to make any changes in this file.
public class MakeGraph {

    public static void main(String[] args) {
	Graph G = new Graph();
	G.loadFile(args[0]);
	boolean done = false;
	Scanner input = new Scanner(System.in);
	while (!done) {
	    System.out.print("Connections To Actors Movies Quit >>> ");
	    String response = input.nextLine().toLowerCase();
	    if (response.equals("quit"))
		done = true;
	    else if (response.equals("connect")) {
		System.out.print("Source >>> ");
		String who = input.nextLine();
		G.findAllPaths(who);
	    } else if (response.equals("to")) {
		System.out.print("Name >>> ");
		String who = input.nextLine();
		Vertex v = G.getVertex(who);
		if (v != null)
		    System.out.println(v.getPath());
	    } else if (response.equals("actors")) {
		G.printAvailableActors();
	    } else if (response.equals("movies")) {
		G.printAvailableMovies();
	    } else {
		System.out.println("Connect: will ask for a source actor and will look for connections");
		System.out.println("To: will ask for an actor and will connect the source to that actor");
		System.out.println("Actors: listss all actors connected to the source");
		System.out.println("Movies: lists all movies connected to the  source");
		System.out.println("Quit: exits the system");
		System.out.println("Anything else displays this message\n");
	    }
	}
	input.close();	//I added this
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

public class Graph {
    public static int Infinity = Integer.MAX_VALUE;

    private HashMap<String, Vertex> vertexMap;
    private LinkedList<Vertex> actors;
    private LinkedList<Vertex> movies;

    public Graph() {
	vertexMap = new HashMap<String, Vertex>();
	movies = new LinkedList<Vertex>();
	actors = new LinkedList<Vertex>();
    }

    public Vertex getVertex(String name) {
	Vertex v = vertexMap.get(name);
	if (v == null) {
	    v = new Vertex(name);
	    vertexMap.put(name, v);
	}
	return v;
    }

    HashMap<String, Vertex> vertexMap() {
	return vertexMap;
    }

    public int vertexCount() {
	return vertexMap.size();
    }

    public void addEdge(String source, String dest) {
	Vertex v = getVertex(source);
	Vertex w = getVertex(dest);
	List<Edge> L = v.adjacents();
	L.add(new Edge(w));
    }

    public void printAvailableActors() {
	for (Vertex e : actors)
	    System.out.printf("%d: %s\n", e.distance() / 2, e);	//Why does this give the distance? What is it relative to?
    }

    public void printAvailableMovies() {
	for (Vertex e : movies)
	    System.out.printf("%s\n", e);
    }

    // EVERYTHING ABOVE THIS LINE IS ALREADY IMPLEMENTED.

    // This reads the named file one line at a time and builds the graph.
    // The file is formatted in the form
    // source|destination
    public void loadFile(String fName) {
	Scanner scan;
	try {
	    if (fName.substring(0, 4).equals("http"))
		scan = new Scanner(new URL(fName).openStream());
	    else
		scan = new Scanner(new File(fName));
	    
	    while (scan.hasNextLine()) {
		String line = scan.nextLine();
		//System.out.println(line.replace("|", " "));
	
		String[] amPair = line.replace(" ", "_").replace("|"," ").split(" ");	//<<< This works, but...
		String actor = amPair[0].replace("_", " ");
		String movie = amPair[1].replace("_", " ");

		/*
		int b = line.indexOf('|');						//<<< This might be better...
		System.out.println("b = "+b);
		String actor = line.substring(0,b);
		String movie = line.substring(b+1);
		*/
		addEdge(actor, movie);
		addEdge(movie, actor);
		
		
		if (!actors.contains(getVertex(actor)))
		    actors.add(getVertex(actor));
		if (!movies.contains(getVertex(movie)))
		    movies.add(getVertex(movie));
		
		//String[] amPair = line.split("|", 3);
	//	System.out.println(Arrays.toString(amPair));
	    }
	    
	    //vvv for checking the graph vvv 
	   
	    for (String key : vertexMap.keySet()) {
		System.out.println("key: "+vertexMap.get(key));
		for (Edge e : getVertex(key).adjacents()) {
		    System.out.println(e.destination());
		}
	    } 
	    
	} catch (FileNotFoundException e) {
	    System.out.printf("File '%s' not found\n", fName);
	} catch (IOException e) {
	}

    }
    

    // This implements the All Paths Single Source algorithm. As it works if it
    // finds that a node has a less-than-infinite distance from the source node
    // it
    // should add the node together the available movie list or the available
    // actors
    // list.
    // Note that a node of the graph represents an actor if its distance is
    // even,
    // and it represents a movie if its distance is odd.
        
    public void findAllPaths(String s) {
	Vertex source = getVertex(s);
	source.distance = 0;
	Queue<Vertex> vq = new LinkedList<Vertex>();
	vq.offer(source);
		
	while (!vq.isEmpty()) {
	    Vertex pre = vq.poll();	//could use peek() instead if while loop breaks too early
	    
	  //  System.out.println();
	  //  System.out.println(pre+":");
	    
	    for (Edge e : pre.adjacents()) {
		Vertex v = e.destination();
				
		if (v.distance == Infinity) {		    
		    v.predecessor = pre;
		    v.distance = pre.distance + 1;
		    vq.offer(v);
		    if (v.distance%2==0)
			actors.add(v);
		    else
			movies.add(v);
		    
		//    System.out.println(v+" distance: "+v.distance+" prev node: "+v.predecessor);
		}
	    }
	}
    }

}

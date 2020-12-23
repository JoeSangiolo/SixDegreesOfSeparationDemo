
// You need to add distance and predecessor information for the Single Source All Paths algorithm.
// You also need to complete the getPath method.

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Vertex {
    String name;
    List<Edge> adjacentList;
    // add distance and predecessor variables here
    int distance;
    Vertex predecessor;

    // initialize distance and predecessor here.
    public Vertex(String name) {
	this.name = name;
	adjacentList = new LinkedList<Edge>();
	this.distance = Graph.Infinity;
	this.predecessor = null;
    }

    public int distance() {
	return distance;
    }

    public List<Edge> adjacents() {
	return adjacentList;
    }

    public String toString() {
	return name;
    }

    // This is called, after the allPaths algorithm is run, to represent the
    // path from the source to this vertex.
    // Follow the predecessor nodes back to the source (whose predecessor should
    // be null), each time pushing the vertex
    // onto a stack. Then pop the stack until it is empty, each time adding a
    // String version of the vertex onto a String.
    // When the stack is empty return the string.
    // If 'A">is the source nod3, "C" is the current node and the path goes
    // through "B" this should return something like
    // "A => B => C"

    public String getPath() {
	String path = "";
	Stack<Vertex> vstack = new Stack<Vertex>();
	
	/*
	Vertex current = new Vertex(this.name);
	current = this;
	*/
	
	Vertex current = this;
	
	//System.out.println(this.toString());
	vstack.push(current);
	for (int i=0;i<distance;i++) {
	    current = current.predecessor;
	    vstack.push(current);
	    
	   // System.out.println(current.toString());
	}
	
	path += vstack.pop();
	while (!vstack.isEmpty()) {
	    Vertex path_node = vstack.pop();
	    
	   // System.out.println(path_node);
	    
	    path += " => "+path_node;
	}
	
	return path;
    }
}

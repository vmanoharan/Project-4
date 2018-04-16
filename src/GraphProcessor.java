import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {


    /**
     * Graph which stores the dictionary words and their associated connections
     */
    private GraphADT<String> graph;


    final private int MAX = Integer.MAX_VALUE;
    private int[][] dist; //Distance between to vertices
    private int[][] next; //Next vertex in a path
    private Object[] dictionary;

    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     * @throws IOException 
     */
	public Integer populateGraph(String filepath) throws IOException {
		dictionary = WordProcessor.getWordStream(filepath).toArray();
    	for(int i=0; i<dictionary.length; i++)
    	{
    		graph.addVertex((String)dictionary[i]);// adds the vertices
    	}
    	for(int i=0; i<dictionary.length; i++)
    	{
    		for(int j=0; j<dictionary.length; j++)
    		{
    			if(WordProcessor.isAdjacent((String)dictionary[i], (String)dictionary[j]))
    			{
    				graph.addEdge((String)dictionary[i], (String)dictionary[j]); // adds the edges
    			}
    		}
    	}
		return dictionary.length;
    }

    
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     *
     * If word1 = word2, List will be empty (0 items).
     * Both the arguments will always be present in the graph.
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
    public List<String> getShortestPath(String word1, String word2) {
        List<String> path = new ArrayList<String>();
        if(word1.equals(word2))
        {
            return path;
        }

        int index1 = getIndex(word1);
        int index2 = getIndex(word2);
        if(index1 < 0 || index2 < 0)
        {
	        	throw new IllegalArgumentException(index1 + "," + index2);// if the word is not found
        }
        if (next[index1][index2] == -1)
            return path;
	
        path.add((String)dictionary[index1]);
        while (index1 != index2) {
            index1 = next[index1][index2];
            path.add((String)dictionary[index1]);// adds the path step by step
        }
		return path;
    }
    
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     *   = 3 (the number of edges in the shortest path)
     *
     * Distance = -1 if no path found between words (true also for word1=word2)
     * Both the arguments will always be present in the graph.
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
    public Integer getShortestDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return -1;
        }

        int index1 = getIndex(word1);
        int index2 = getIndex(word2);
        if(index1 < 0 || index2 < 0)
        {
        	return -1;
        }// if the word is not found

        if (dist[index1][index2] == MAX)
            return -1;

        return dist[index1][index2];
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {

        dist = new int[dictionary.length][dictionary.length];
        next = new int[dictionary.length][dictionary.length];

        for (int i = 0 ; i < dictionary.length ; i++) {
            for (int j = 0 ; j < dictionary.length ; j++) {
                dist[i][j] = MAX;
                next[i][j] = -1;
            }
        }

        for (int i = 0 ; i < dictionary.length ; i++) {
            for (int j = 0 ; j < dictionary.length ; j++) {
                if (graph.isAdjacent((String)dictionary[i], (String)dictionary[j])) {
                    dist[i][j] = 1;
                    next[i][j] = j;
                }
            }
        }// initialize the results

        for (int k = 0 ; k < dictionary.length ; k++) {
            for (int i = 0 ; i < dictionary.length ; i++) {
                for (int j = 0 ; j < dictionary.length ; j++) {
                    if (dist[i][k]!=MAX && dist[k][j]!=MAX && dist[i][j]>dist[i][k]+dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

                }
            }
        }// find the shortest path


    }

    /**
     * Finds the index of a word in dictionary array. Returns -1 if not found
     * @param word is the word to find
     * @return the index of the word
     */
    private int getIndex(String word) {
        for (int i = 0 ; i < dictionary.length ; i++) {
            if (((String)dictionary[i]).equalsIgnoreCase(word))
                return i;
        }
        return -1;
    
    }
}

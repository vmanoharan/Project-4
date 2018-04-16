import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * Used adjacency matrix implementation
 * @param <E> type of a vertex
 * 
 */
public class Graph<E> implements GraphADT<E> {
    private int[][] edges = new int[5][5];
    @SuppressWarnings("unchecked")
    private E[] dictionary = (E[]) new Object[5];
    private int items = 0;

    /**
     * Insert a vertex into the graph, fill in
     * the added edges into the adjacency matrix
     * @param <E> type of a vertex
     * @return E added vertex
     */
    @Override
    public E addVertex(E vertex) {
        if(vertex == null)
      	{
      		return null;// The added vertex cannot be null
      	}
        int index = getIndex(vertex);
        if(index >= 0)
        {
        	return null;
        }
		if(items >= dictionary.length)
        {
        	@SuppressWarnings("unchecked")// warnings in this case are chekced
		E[] temp = (E[]) new Object[dictionary.length*2];
        	int[][] temp1 = new int[edges.length*2][edges.length*2];// expansion of matrix and vertex list
        	for(int i=0; i<dictionary.length; i++)
        	{
        		temp[i] = dictionary[i];
        		for(int j=0; j<edges[i].length; j++)
        		{
        			temp1[i][j] = edges[i][j];//Iterate throught the adjacency matrix to fill
        		}				// the edges of the new vertex
        	}
        	edges = temp1;
        	dictionary = temp;
        }
		dictionary[items] = vertex;
        items++;
        return vertex;
    }

    /**
     * Remove a vertex from the graph, remove the
     * edge relationships in the matrix
     * @param <E> type of a vertex
     * @return E removed vertex
     */
    @Override
    public E removeVertex(E vertex) {
    	if(vertex == null)
        {
    			return null;
        }
        int index = getIndex(vertex);
        if(index < 0)
        {
        	return null;
        }
        else
        {
            for(int i = 0; i < dictionary.length; i ++)
            {
                if(isAdjacent(vertex, dictionary[i])) 
                {
                    removeEdge(vertex, dictionary[i]);// remove the edges
                }
            }
        	dictionary[index] = null;
        }
        return vertex;
        
    }

    /**
     * Add an edge to between two given vertices
     * by utilizing the adjacency matrix
     * @param <E> type of two vertices
     * @return boolean if the addition of edge is successful
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
	int index1 = getIndex(vertex1);
	int index2 = getIndex(vertex2);
        if(index1 < 0 || index2 < 0 || index1 == index2)
        {
        	return false;
        }
        edges[index1][index2] = 1;
        edges[index2][index1] = 1;// update the adjacency matrix
        return true;
        
    }    
    /**
     * Retrieves the index for the give vertex
     * If vertex isn't in graph returns -1    
     * @param vertex
     * @return index of given vertex
     */
    private int getIndex(E vertex){
    	for(int i = 0; i < items; i++)
    	{
    		if(dictionary[i] != null && (((String)dictionary[i]).equalsIgnoreCase((String)vertex)))
    		{
    			return i;
    		}
        }
	    return -1;
    }
    /**
     * removes an edge to between two given vertices
     * by utilizing the adjacency matrix
     * @param <E> type of two vertices
     * @return boolean if the removal of edge is successful
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
    	int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if(index1 == -1 || index2 == -1 || vertex1.equals(vertex2) || index1 == index2)
	{
        	return false;
        }
        else
	{
        	edges[index1][index2] = 0;
        	edges[index2][index1] = 0;
          return true;
        }
    }

    /**
     * Determines if two given vertices are adjacent
     * by chekcing if they have a edge in the adjacency
     * matrix
     * @param <E> type of two vertices
     * @return boolean if there exits an edge between two given vertices
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if(index1 == -1 || index2 == -1 || vertex1.equals(vertex2) || index1 == index2)
	{
        	return false;
        }
        else if(edges[index1][index2] == 1)
	{
        	return true;
        }
        return false;
    }

    /**
     * Returns an ArrayList of neighbouring vertices of a given vertex
     * @param <E> type of vertex
     * @return an ArrayList<E> type of neighbours of a given vertex
     */
    @Override
    public Iterable<E> getNeighbors(E vertex){
        int index = getIndex(vertex);
        int[] adjacency_list = edges[index];
        ArrayList<E> neighbors = new ArrayList<E>();
        
        for(int i = 0; i < adjacency_list.length; i++)
        {
        	if(adjacency_list[i] == 1)
        	{
        		if(dictionary[i] != null)
        		{
        			 neighbors.add(dictionary[i]);// add the neighbours
        		}
        	}
        }
        return neighbors;
        
    }

    /**
     * Returns an ArrayList of all the vertices in the graph
     * @return an ArrayList<E> containing all the vertices in the graph
     */
    @Override
    public Iterable<E> getAllVertices() {
        ArrayList<E> result = new ArrayList<E>();        
        for(int i = 0; i < items; i++)
        {
            if(dictionary[i] != null)
            {
            	result.add(dictionary[i]); // add the vertices
            }
        }
        
        return result;
        
    }

}

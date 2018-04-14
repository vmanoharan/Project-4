import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    private int[][] edges = new int[5][5];
	@SuppressWarnings("unchecked")
	private E[] dictionary = (E[]) new Object[5];
    private int items = 0;
    /**
     * Instance variables and constructors
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        if(vertex == null)
      	{
      		return null;
      	}
        int index = getIndex(vertex);
        if(index < 0)
        {
        	return null;
        }
		if(items >= dictionary.length)
        {
        	@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Object[dictionary.length*2];
        	int[][] temp1 = new int[edges.length*2][edges.length*2];
        	for(int i=0; i<dictionary.length; i++)
        	{
        		temp[i] = dictionary[i];
        		for(int j=0; j<edges[i].length; j++)
        		{
        			temp1[i][j] = edges[i][j];
        		}
        	}
//        	int k = 0;
//        	int l = 0;
//        	for(int i=0; i<edges.length; i++)
//        	{
//        		if(dictionary[i] != null)
//        		{
//	        		temp[i - k] = dictionary[i];
//	        		for(int j=0; j<edges.length; j++)
//	        		{
//	        			if(dictionary[j] != null)
//	        			{
//	        				temp1[i - k][j - l] = edges[i][j];
//	        			}
//	        			else
//	        			{
//	        				l++;
//	        			}
//	        		}
//        		}
//        		else
//        		{
//        			k++;
//        			items--;
//        		}
//        	}
        	edges = temp1;
        	dictionary = temp;
        }
		dictionary[items] = vertex;
        items++;
        return vertex;
    }

    /**
     * {@inheritDoc}
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
        	dictionary[index] = null;
        }
        return vertex;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
		int index1 = getIndex(vertex1);
		int index2 = getIndex(vertex2);
        if(index1 < 0 || index2 < 0)
        {
        	return false;
        }
        edges[index1][index2] = 1;
        edges[index2][index1] = 1;
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
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
    		int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if(index1 == -1 || index2 == -1 || vertex1.equals(vertex2) || index1 == index2){
        	return false;
        }
        else{
        	edges[index1][index2] = 0;
        	edges[index2][index1] = 0;
          return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if(index1 == -1 || index2 == -1 || vertex1.equals(vertex2) || index1 == index2){
        	return false;
        }
        else if(edges[index1][index2] == 1){
        	return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        
        int index = getIndex(vertex);
        int[] adjacency_list = edges[index];
        ArrayList<E> neighbors = new ArrayList<E>();
        
        for(int i = 0; i < adjacency_list.length; i++)
        {
        	if(adjacency_list[i] == 1)
        	{
        		if(dictionary[i] != null)
        		{
        			 neighbors.add(dictionary[i]);
        		}
        	}
        }
        return neighbors;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        ArrayList<E> result = new ArrayList<E>();
        
        for(int i = 0; i < items; i++)
        {
            if(dictionary[i] != null)
            {
            	result.add(dictionary[i]);
            }
        }
        
        return result;
        
    }

}

import java.util.HashMap;
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
    private int[][] edges;
		private E[] dictionary;
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
				if(items >= dictionary.length())
        {
        	E[] temp = new E[dictionary.length*2];
        	for(int i=0; i<items; i++)
          {
          	temp[i] = dictionary[i];
          }
          int[][] temp = new int[edges.length()][edges.length()]
          for(int i=0; i<)
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
        
        
    }    
		private int getIndex(E vertex){
    		index = 0;	
    		boolean isFound = false;
    	  for(int i = 0; i < dictionary.length; i++){
        		if(dictionary[i].equals()vertex == true){
            	index = i;
              isFound = true;
            }
        }
        if(isFound){
        	return index;
        }
        else{
        	return -1;
				}
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
        	dictionary[i][j] = 0;
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
        else if(dictionary[i][j] == 1){
        	return true;
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        return this.dictionary;
        
    }

}

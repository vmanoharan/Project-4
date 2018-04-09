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
        for(int i=0; i<dictionary.length; i++)
        {
        	if(dictionary[i].toString().equals(vertex.toString()))
        	{
        		return null;
        	}
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
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        
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
        
    }

}

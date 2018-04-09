import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/////////////////////////////////////////////////////////////////////////////
// Semester: CS400 Spring 2018
// PROJECT: cs400_XTeam_Project4
// FILES: Graph.java 
// GraphADT.java
// GraphTest.java
// WordProcessor.java
// GraphProcessor.java  
//
// USER: 
//
// Instructor: Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs: no known bugs, but not complete either
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Junit test class to test class @see GraphProcessor
 *
 * @author Yuming Ma
 */
public class GraphProcessorTest {

	private GraphADT<String> graph;
	
	private static List<String> vertices;
	
	private static int numOfVertices = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vertices = new ArrayList<>();
		vertices.add("at");
		vertices.add("it");
		vertices.add("cat");
		vertices.add("hat");
		vertices.add("hot");
		vertices.add("rat");
		vertices.add("heat");
		vertices.add("neat");
		vertices.add("major");
		vertices.add("wheat");
		vertices.add("streak");	
		vertices.add("husband");
		for (String vertex : vertices)
			numOfVertices++;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		vertices = null;
		numOfVertices = 0;
	}

	@Before
	public void setUp() throws Exception {
		this.graph = new Graph<>();
	}

	@After
	public void tearDown() throws Exception {
		this.graph = null;
	}
    
    @Test
    public void test01_isAdjacent_given_nonexistent_vertices() {
        expected = "";
        actual = "";
        if (!expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }
    @Test
    public void test02_insert_null() {
        
    }
    @Test
    public void test03_insert_invalid() {
        
    }

}



import static org.junit.Assert.*;

import java.io.IOException;
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
// GraphADT.java
// GraphTest.java
// WordProcessor.java
// GraphProcessor.java  
// GraphProcessor.java
//
// USER: 
// USER:
//
// Instructor: Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs: no known bugs, but not complete either
public class GraphProcessorTest {
	private GraphProcessor graphProcessor;
	private String fileName;
	private Integer numOfVertices;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {
        this.graphProcessor = new GraphProcessor();
        this.fileName = "word_list.txt";
        numOfVertices = graphProcessor.populateGraph(fileName);
    }

    @After
    public void tearDown() throws Exception {
        this.graphProcessor = null;
    }

    /**
     * This method checks isAdjacent() in WordProcessor and see whether it returns the expected value.
     */
    @Test
    public void test01_WordProcessor_IsAdjacent() {
        assertEquals(WordProcessor.isAdjacent("body", "boy"), true);
        assertEquals(WordProcessor.isAdjacent("cute", "cut"), true);
        assertEquals(WordProcessor.isAdjacent("soy", "boy"), true);
        assertEquals(WordProcessor.isAdjacent("sour", "sad"), false);
        assertEquals(WordProcessor.isAdjacent("ginger", "ginseng"), false);
        assertEquals(WordProcessor.isAdjacent("easy", "yes"), false); // if order does matter
        assertEquals(WordProcessor.isAdjacent("tease", "tea"), false);
        assertEquals(WordProcessor.isAdjacent("morningstar", "morning"), false);
        assertEquals(WordProcessor.isAdjacent("coming", "morning"), false);
    }

    /**
     * This test checks whether populateGraph method returns correct number of vertices
     * within the graph.
     */
    @Test
    public void test02_PopulatGraph_Value() {
        assertEquals(numOfVertices, new Integer(441));
    }

    /**
     * This test checks whether empty arraylist will be returned after passing two same words into
     * getShortestPath method.
     */
    @Test
    public void test03_sameWord_getShortestPath() {
        ArrayList<String> list = new ArrayList<String>();
        graphProcessor.shortestPathPrecomputation();
        for (String word : graphProcessor.getShortestPath("whiner", "whiner")) {
            list.add(word);
        }
        assertEquals(list.isEmpty(), true);
    }

    /**
     * This test checks whether getShortestPath() returns correct path after two different
     * words are passed into it
     * @throws IOException 
     */
    @Test
    public void test04_getShortestPath() throws IOException {
        graphProcessor.shortestPathPrecomputation();
        List<String> actual = graphProcessor.getShortestPath("comely" , "comedo");
        assertEquals(actual.get(0), "comely");
        assertEquals(actual.get(1), "comedy");
        assertEquals(actual.get(2), "comedo");

    }

    /**
     * This test checks if the getShortestDistance() returns -1 after passing same words
     */
    @Test
    public void test05_SameWord_getShortestDistance() {
        graphProcessor.shortestPathPrecomputation();
        assertEquals(graphProcessor.getShortestDistance("protest", "protest"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("tremulous", "tremulous"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("peace", "peace"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("airfoil", "airfoil"), new Integer(-1));
    }

    /**
     * This test checks if the getShortestDistance() returns -1 after passing different words, but
     * no path expect to exist.
     */
    @Test
    public void test05_DiffWord_nopath_getShortestDistance() {
        graphProcessor.shortestPathPrecomputation();
        assertEquals(graphProcessor.getShortestDistance("protest", "airfoil"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("tremulous", "airfoil"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("cheap", "peace"), new Integer(-1));
        assertEquals(graphProcessor.getShortestDistance("cheap", "cheap"), new Integer(-1));
    }

    /**
     * This test checks if the getShortestDistance() returns -1 after passing different words, path
     * exist
     */
    @Test
    public final void test06_DiffWord_havepath_getShortestDistance() {
        graphProcessor.shortestPathPrecomputation();
        assertEquals(graphProcessor.getShortestDistance("COMEDO", "CHARGE"), new Integer(49));
        assertEquals(graphProcessor.getShortestDistance("CHARGE", "GIMLETS"), new Integer(78));
        assertEquals(graphProcessor.getShortestDistance("BELLIES", "JOLLIES"), new Integer(2));
        assertEquals(graphProcessor.getShortestDistance("RAPINE", "HOMINY"), new Integer(8));
    }
 }
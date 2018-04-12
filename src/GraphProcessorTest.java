import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
private GraphProcessor graphProcessor;
private String fileName;
private Integer numOfVertices;
@BeforeClass
public static void setUpBeforeClass() throws Exception {
}

@AfterClass
public static void tearDownAfterClass() throws Exception {
}

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

//this method checks isAdjacent() in WordProcessor and see whether it returns the expected value
@Test
public void test01_WordProcessor_IsAdjacent() {
assertEquals(WordProcessor.isAdjacent("body","boy"), true);
assertEquals(WordProcessor.isAdjacent("cute","cut"), true);
assertEquals(WordProcessor.isAdjacent("soy","boy"), true);
assertEquals(WordProcessor.isAdjacent("sour","sad"), false);
assertEquals(WordProcessor.isAdjacent("ginger","ginseng"), false);
assertEquals(WordProcessor.isAdjacent("easy","yes"), false); //if order does matter
assertEquals(WordProcessor.isAdjacent("tease","tea"), false);
assertEquals(WordProcessor.isAdjacent("morningstar","morning"), false);
assertEquals(WordProcessor.isAdjacent("coming","morning"), false);
}

//this test checks whether populateGraph method returns correct number of vertices within the graph
@Test
public void test02_PopulatGraph_Value() {
assertEquals(numOfVertices, new Integer(441));
}

//this test checks whether empty arraylist will be returned after passing two same words into getShortestPath method
@Test
public void test03_SameWord_getShortestPath() {
ArrayList<String> list = new ArrayList<String>();
graphProcessor.shortestPathPrecomputation();
for (String word: graphProcessor.getShortestPath("whiner", "whiner")) {
list.add(word);
}
assertEquals(list.isEmpty(), true);
}


//this test checks whether getShortestPath() returns correct path after two different words are passed into it
@Test
public void test04_getShortestPath() {
ArrayList<String> list = new ArrayList<String>();
graphProcessor.shortestPathPrecomputation();
for (String elem: graphProcessor.getShortestPath("RAPINE", "HOMINY")) { //TODO use another example
list.add(elem);
}
assertEquals(list.get(0),"RAPINE");
assertEquals(list.get(1),"RAVINE");
assertEquals(list.get(2),"RAVING");
assertEquals(list.get(3),"ROVING");
assertEquals(list.get(4),"ROPING");
assertEquals(list.get(5),"COPING");
assertEquals(list.get(6),"COMING");
assertEquals(list.get(7),"HOMING");
assertEquals(list.get(8),"HOMINY");
}

//this test checks if the getShortestDistance() returns -1 after passing same words
@Test
public void test05_SameWord_getShortestDistance() {
graphProcessor.shortestPathPrecomputation();
assertEquals(graphProcessor.getShortestDistance("protest", "protest"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("tremulous", "tremulous"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("peace", "peace"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("airfoil", "airfoil"), new Integer(-1));
}

//this test checks if the getShortestDistance() returns -1 after passing different words, but no path exist
@Test
public void test05_DiffWord_nopath_getShortestDistance() {
graphProcessor.shortestPathPrecomputation();
assertEquals(graphProcessor.getShortestDistance("protest", "airfoil"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("tremulous", "airfoil"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("cheap", "peace"), new Integer(-1));
assertEquals(graphProcessor.getShortestDistance("cheap", "cheap"), new Integer(-1));
}

//this test checks if the getShortestDistance() returns -1 after passing different words, which path exist
@Test
public void test06_DiffWord_havepath_getShortestDistance() {
graphProcessor.shortestPathPrecomputation();
//assertEquals(graphProcessor.getShortestPath("body" , "soy"), new String("body + /n + boy + /n + soy"));
// TODO add examples
}

}

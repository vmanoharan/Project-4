import static org.junit.Assert.*;
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
 * @author Yuming Ma and Yuxuan Liu
 *
 */
public class TestSearchTree {

    SearchTreeADT<String> tree = null;
    String expected = null;
    String actual = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tree = new BalancedSearchTree<String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // reset tree, actual, expected, and actual to null
        tree = null;
        actual = null;
        expected = null;
    }

    @Test
    public void test01_isEmpty_on_empty_tree() {
        expected = "true";
        actual = "" + tree.isEmpty();
        if (!expected.equals(actual))
            fail("expected: " + expected + " actual: " + actual);
    }

   

}



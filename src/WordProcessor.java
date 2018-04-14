import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class contains some utility helper methods
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class WordProcessor {
	/**
	 * Gets a Stream of words from the filepath.
	 * 
	 * The Stream should only contain trimmed, non-empty and UPPERCASE words.
	 * 
	 * @see <a href="http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html">java8 stream blog</a>
	 * 
	 * @param filepath file path to the dictionary file
	 * @return Stream<String> stream of words read from the filepath
	 * @throws IOException exception resulting from accessing the filepath
	 */
	public static Stream<String> getWordStream(String filepath) throws IOException {
		/**
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">java.nio.file.Files</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html">java.nio.file.Paths</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html">java.nio.file.Path</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">java.util.stream.Stream</a>
		 * 
		 * class Files has a method lines() which accepts an interface Path object and 
		 * produces a Stream<String> object via which one can read all the lines from a file as a Stream.
		 * 
		 * class Paths has a method get() which accepts one or more strings (filepath),  
		 * joins them if required and produces a interface Path object
		 * 
		 * Combining these two methods:
		 *     Files.lines(Paths.get(<string filepath>))
		 *     produces
		 *         a Stream of lines read from the filepath
		 * 
		 * Once this Stream of lines is available, you can use the powerful operations available for Stream objects to combine 
		 * multiple pre-processing operations of each line in a single statement.
		 * 
		 * Few of these features:
		 * 		1. map( )      [changes a line to the result of the applied function. Mathematically, line = operation(line)]
		 * 			-  trim all the lines
		 * 			-  convert all the lines to UpperCase
		 * 			-  example takes each of the lines one by one and apply the function toString on them as line.toString() 
		 * 			   and returns the Stream:
		 * 			        streamOfLines = streamOfLines.map(String::toString) 
		 * 
		 * 		2. filter( )   [keeps only lines which satisfy the provided condition]  
		 *      	-  can be used to only keep non-empty lines and drop empty lines
		 *      	-  example below removes all the lines from the Stream which do not equal the string "apple" 
		 *                 and returns the Stream:
		 *      			streamOfLines = streamOfLines.filter(x -> x != "apple");
		 *      			 
		 * 		3. collect( )  [collects all the lines into a java.util.List object]
		 * 			-  can be used in the function which will invoke this method to convert Stream<String> of lines to List<String> of lines
		 * 			-  example below collects all the elements of the Stream into a List and returns the List:
		 * 				List<String> listOfLines = streamOfLines.collect(Collectors::toList); 
		 * 
		 * Note: since map and filter return the updated Stream objects, they can chained together as:
		 * 		streamOfLines.map(...).filter(a -> ...).map(...) and so on
		 */
	    Stream<String> in = Files.lines(Paths.get(filepath))
	                    .map(String::trim)
	                    .map(String::toUpperCase)
	                    .filter(x -> x!=null && !x.equals(""));

				return in;
	}
	
	/**
	 * Adjacency between word1 and word2 is defined by:
	 * if the difference between word1 and word2 is of
	 * 	1 char replacement
	 *  1 char addition
	 *  1 char deletion
	 * then 
	 *  word1 and word2 are adjacent
	 * else
	 *  word1 and word2 are not adjacent
	 *  
	 * Note: if word1 is equal to word2, they are not adjacent
	 * 
	 * @param word1 first word
	 * @param word2 second word
	 * @return true if word1 and word2 are adjacent else false
	 */
	public static boolean isAdjacent(String word1, String word2) {
	    int mismatches = 0;
	    //if a word is 2 characters longer it will never be adjacent
	    if(Math.abs(word1.length()-word2.length()) > 1)
	    {
	    	return false;
	    }
	    //replacement
	    if(word1.length() == word2.length())
	    {
	        //if they are equal it's false
	        if(word1.equals(word2))
	        {
	            return false;
	        }
	        //loop through each character and check for mismatches, if there is more than one
	        //they are not adjacent by replacement.
	        for(int i = 0; i < word1.length(); i++ )
	        {

	            if(!word1.substring(i, i+1).equals(word2.substring(i, i+1)))
	            {
	                mismatches++;
	                if(mismatches > 1)
	                {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	    
	    //addition/deletion (pretty much the same)
	    else
	    {
	        //In addition and deletion one string is longer than the other, it is useful to know
	        //which string is longer and which one is shorter
	        String longer;
	        String shorter;
	        if(word1.length() > word2.length())
	        {
	            longer = word1;
	            shorter = word2;
	        }
	        else
	        {
	            longer = word2;
	            shorter = word1;
	        }
	        
	        for(int i = 0; i < shorter.length(); i++)
	        {
	            //Mismatch
	            if(!longer.substring(i, i+1).equals(shorter.substring(i, i+1)))
	            {
                    shorter = shorter.substring(0, i)  //first part of shorter
                                    + longer.substring(i, i+1) //adding in the missing character
                                    + shorter.substring(i); //second part of shorter
                    
                    //Now that we have added the missing character that appears in longer,
                    //but not in shorter. If the strings started out adjacent, they should now
                    //be equal.
                    //Note: This works for both addition and deletion because addition can be
                    //viewed as deletion and vice-versa. 
                    if(shorter.equals(longer))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
	                
	                                
	            }
	        }
	    }
		
		
		return true;
	}
}

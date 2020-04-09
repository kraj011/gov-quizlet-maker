import java.util.*;
import java.io.*;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class QuizletMaker
{
	public static void main(String[] args) throws IOException {
		Map<String, String> wordDef = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	    File file = new File("all-vocab.txt");
	    File file2 = new File("vocab.txt");
	    Scanner sc = new Scanner(file);
	    Scanner sc2 = new Scanner(file2);
	    
	    String line;
	    String line2;
	    boolean everyOther = true;
    	
	    while (sc.hasNextLine())
	    {
	    	line = sc.nextLine();
		    line2 = sc.nextLine();
		    	
	    	if (everyOther) {
	    		everyOther = false;
		      
			    if (line.contains("(")) {
			    	line = line.substring(0, line.indexOf("(") - 1);
			    }
	    		wordDef.put(line, line2.substring(0, line2.length() - 1));
	    	}
	      
	    	else {
	    		everyOther = true;
	    	}
	    }
	    
	    sc.close();
	    
	    PrintWriter writer = new PrintWriter("quizlet.txt", "UTF-8");
	    
	    String word;
	    String quizletText = "";
	    while (sc2.hasNextLine()) {
	    	word = sc2.nextLine();
	    	if (wordDef.containsKey(word)) {
			    writer.println(word + "---" + wordDef.get(word));
			    quizletText += word + "---" + wordDef.get(word) + "\n";
	    	}
	    	
	    	else if (wordDef.containsKey(word.substring(0, word.length() - 1)) ) {
	    			writer.println(word + "---" + wordDef.get(word.substring(0, word.length() - 1)));
	    			quizletText += word + "---" + wordDef.get(word.substring(0, word.length() - 1)) + "\n";
	    	}
	    	
	    	else {
	    		System.out.println(word);
	    		quizletText += word + "---" + "DEFINITION NOT FOUND\n";
	    	}
	    }
		
	    sc2.close();
	    writer.close();

//        WebClient webClient = new WebClient();
//        HtmlPage page = webClient.getPage("http://www.quizlet.com/create-set");
//
//        HtmlSubmitInput button = page.getFirstByXPath("//div[contains(@class, 'UILink')]"); 
//        page = button.click();
//        
//        HtmlInput inputBox = page.getFirstByXPath("//div[contains(@class, 'ImportTerms-textarea')]"); 
//        inputBox.setValueAttribute(quizletText);
//	    
//        HtmlInput separatorBox = page.getFirstByXPath("//div[contains(@class, 'UIInput-input')]"); 
//	    separatorBox.setValueAttribute("---");
//
//        HtmlSubmitInput importButton = page.getFirstByXPath("//div[contains(@class, 'UIButton UIButton--hero')]"); 
//        page = importButton.click();
//        
//        HtmlInput titleBox = page.getFirstByXPath("//div[contains(@class, 'AutoExpandTextarea-wrapper')]"); 
//	    titleBox.setValueAttribute("AP Gov: Unit Test Vocab");
        
//        HtmlSubmitInput importButton = page.getFirstByXPath("//div[contains(@class, 'UIButton UIButton--hero')]"); 
//        page = importButton.click();

//        webClient.close();
	}
}

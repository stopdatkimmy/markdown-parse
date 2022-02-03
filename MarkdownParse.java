// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        Pattern p = Pattern.compile(".*\\..*");
        
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextCloseBracket + 1 != openParen){
                currentIndex = closeParen + 1;
                continue;
            }
            if(nextOpenBracket == -1 || nextCloseBracket == -1|| openParen == -1 || closeParen == -1){
                break;
            }
            String link = markdown.substring(openParen + 1, closeParen);
            Matcher m = p.matcher(link);
            if(nextOpenBracket>0 && !markdown.substring(nextOpenBracket -1 , nextOpenBracket).contains("!") && m.matches()){ 
                toReturn.add(link);
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
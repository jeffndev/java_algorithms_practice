package com.jeffndev;

import java.text.NumberFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        testNumFormatting();
    }

    public static void testNumFormatting(){
        final int pennies = 345599;
        Locale locale = Locale.US;
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        System.out.println(format.format((double)pennies/100.0));
    }

    public static void wordWrappingTests(){
        long startDate;
        long endDate;
        //Calendar cal = new GregorianCalendar();

        int lineCharMax = 70;
        String longString = "All across the country, Spotlight divisions are doing great things! Team Gallery is a place featuring some of the great work, collaborations, partnerships and honors from our teams. Click to check out the new ATLAS Team Gallery to see what Spotlight teams are up to. You can submit your photos/captions by clicking the submit footer in each edition of the What’s New newsletter. In this advanced advertising announcement from SVP Comcast Media 360, Kevin Smith, learn more about the CM360 team including organizational updates to best serve our customers and Spotlight as a whole. Click to view the announcement.";
        startDate = System.currentTimeMillis();
        for(int i = 0; i < 1000;i++)
        /*String wrappedString = */wrapTextMethod03(longString, lineCharMax);
        endDate = System.currentTimeMillis();
        System.out.println("..Execution time: " + (endDate-startDate));
        //System.out.print(wrappedString);
    }
    public static String wrapTextMethod02(String originalText, int lineCharMax){
        /*a recursive solution?*/
        return null;
    }
    public static String wrapTextMethod03(String originalText, int lineCharMax){
        //slower, but nicer to look at...
        final int SPACE_WIDTH = 1;
        StringBuilder sb = new StringBuilder();
        int spaceLeft = lineCharMax;
        StringTokenizer st = new StringTokenizer(originalText);
        while(st.hasMoreTokens()){
            String nextWord = st.nextToken();
            if(spaceLeft - nextWord.length() - SPACE_WIDTH > 0 ){
                //add the word and a space
                sb.append(nextWord).append(' ');
                spaceLeft -=(nextWord.length() + SPACE_WIDTH);
            }else{
                //add a line-break, then the word then a space
                sb.append('\n').append(nextWord).append(' ');
                //reset spaceLeft
                spaceLeft = lineCharMax - nextWord.length() - SPACE_WIDTH;
            }
        }
        return sb.toString();
    }
    public static String wrapTextMethod01(String originalText, int lineCharMax){
        //three times as fast as method 03
        StringBuilder sb = new StringBuilder();
        int currLineStartPos = 0;
        int textEndPos = originalText.length() - 1;
        while( currLineStartPos + lineCharMax < textEndPos){
            //increment to first non-white-space char to begin
            while(originalText.charAt(currLineStartPos) == ' ' || originalText.charAt(currLineStartPos) == '\t' )
                currLineStartPos++;
            int cutPos = currLineStartPos + lineCharMax;
            //adjust back to find the word boundary before the cutoff position...
            if( originalText.charAt(cutPos) == ' ' || originalText.charAt(cutPos) == '\t' ){
                //decrement until non-white-space
                cutPos--;
                while(originalText.charAt(cutPos) == ' ' || originalText.charAt(cutPos) == '\t' )
                    cutPos--;
                cutPos++;

            }else{
                //decrement until white space..
                cutPos--;
                while(originalText.charAt(cutPos) != ' ' && originalText.charAt(cutPos) != '\t' )
                    cutPos--;
                cutPos--;
                while(originalText.charAt(cutPos) == ' ' || originalText.charAt(cutPos) == '\t' )
                    cutPos--;
                cutPos++;
            }
            //extract the substring
            sb.append(originalText.substring(currLineStartPos,cutPos)).append('\n');
            currLineStartPos = cutPos;
        }
        //increment to first non-white-space char to begin
        while(originalText.charAt(currLineStartPos) == ' ' || originalText.charAt(currLineStartPos) == '\t' )
            currLineStartPos++;
        sb.append(originalText.substring(currLineStartPos));
        return sb.toString();
    }

    public static void stringReplacementTests(){
        Map<String,String> dictionary = new HashMap<String,String>();
        dictionary.put(" like","");
        dictionary.put(" uh","");
        String badString = "Hi uh this is like a uh string with a uh lot of like uh stuff in it like";
        //System.out.println(badString);
        //String goodString = badString.replaceAll(" like", "");
        //System.out.println(goodString);
        String [] toReplace = {" uh"," like"};
        String [] theReplacements = {"",""};
        //String goodString = replaceMethod01(badString, toReplace, theReplacements);
        String goodString = replaceMethod02(badString, dictionary);
        System.out.println("..original string..");
        System.out.println(badString);
        System.out.println("..fixed-up string..");
        System.out.println(goodString);
    }

    public static String replaceMethod01(String theString, String[] toReplace, String[] theReplacements){
        int i = Math.min(toReplace.length,theReplacements.length);
        for(int j = 0; j < i;j++)
            theString = theString.replaceAll(toReplace[j],theReplacements[j]);
        return theString;
    }
    public static String replaceMethod02(String theString, Map<String,String> replaceMap){
        for( String s : replaceMap.keySet())
            theString = theString.replaceAll(s, replaceMap.get(s));
        return theString;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonenumberconverter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Formatter;
import java.util.NoSuchElementException;
/**
 *
 * @author hea113
 */
public class PhoneNumberConverter {

    /**
     * @param args the command line arguments
     */
    //global variables
    public static int countNumbers = 0;
    public static int phoneNumberArrayCorrectOrder[] = new int[7];
    public static Formatter formatter;
    public static void main(String[] args) throws IOException {
        // local variables
        int phoneNumber;
        int currentDigit;
        int phoneNumberArrayReverseOrder[] = new int[7];
        char[] word = new char[7];
        formatter = new Formatter("Phone.txt");
        
        // phone number is assigned a method that asks the user for input with input validation
        phoneNumber = getInput();
        
        // grabs all the numbers the user inputs one by one and puts it into an array
        int i=0;
        while(phoneNumber > 0){
            phoneNumberArrayReverseOrder[i] = phoneNumber % 10;
            phoneNumber /=10;                
            i++;
        }
       
        // obtains the numbers in reverse order into an array 
        int m=0;
        for(int j=phoneNumberArrayReverseOrder.length-1; j>=0; j--){
            phoneNumberArrayCorrectOrder[m] = phoneNumberArrayReverseOrder[j];
            m++;   
        }

        // reorders the numbers into correct order
        for(int k=0; k <= phoneNumberArrayCorrectOrder.length-1; k++){
            System.out.print(phoneNumberArrayCorrectOrder[k] + ", ");
        }
         
        generateCombinations(-1,word);
        System.out.println(countNumbers);
        formatter.format("%d", countNumbers); // prints the amount of combinations
        
        formatter.close();
    }
    
    // recursive algorithm that splits the words into three each time and generates the different combinations
    public static void generateCombinations(int currentNumber, char[] word){
        char[] combinations = 
        {
        'A',
        'D',
        'G',
        'J',
        'M',
        'P',
        'T',
        'W'
        };
        // if the currentNumber of numbers is below 6 then it is ok because I add one after on the next line
        if(currentNumber < 6){
            currentNumber++;
            // for loop that splits the combinations into three repeatedly
            for(int i=0; i<3; i++){ 
                int currentDigit = phoneNumberArrayCorrectOrder[currentNumber];
                char currentLetter  = combinations[currentDigit-2];
                currentLetter+=i;
                // considers the letters Q and R
                if(currentLetter == 'Q' || currentLetter == 'R'){
                    currentLetter++;
                }
                word[currentNumber] = currentLetter;
                generateCombinations(currentNumber, word);
            }
        }
        else{
            // after it is finished it displays the results
            for(int i=0; i<word.length; i++){
                System.out.print(word[i]);
                formatter.format("%s",word[i]);
                
            }
            System.out.println("");
            formatter.format("\n");
            countNumbers++;
        }
    }
    
    // if the input is invalid the method handles what happens
    public static void inputNotValid(){
        System.out.println("Please enter a valid 7 digit number, and all digits must be between 2 through 9");
    }
    
    // obtains the input from the user
    public static int getInput(){
        String phoneNumber1;
        int phoneNumber;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter phone number (digits greater than 1 only): ");
        
        // validates the input from the user
        try
        {     
            phoneNumber1 = input.next("\\d{7}");
            phoneNumber = Integer.parseInt(phoneNumber1);
            
        }catch(NoSuchElementException e){
            inputNotValid();
            return getInput();
        }
        if(phoneNumber1.contains("0") || phoneNumber1.contains("1")){
            inputNotValid();
            return getInput();
        }
        return phoneNumber;
    }
    
    
}

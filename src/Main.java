/*

Brianna Gonzalez
Homework Two
/*
      A small business owner needs a program written to help verify the transactions to make sure bookkeeping is correct. 
      You will be writing a program ReportGenerator that reads an unknown number of files with unknown number of lines 
      with the following fields separated by a delimiter characteri: Date sold, SKU number, price, discount percentage amount.
      You will be creating your own input files to test, each with at least 5 items. Create a subfolder 'data' and store
      your CSV files into that folder.
      The output text file (the report) has the following elements: 
      Title at the top: “Report from ____ to _____” where the blanks denote start and end dates that are based on the date
      column in the data file.
      Compute the total sum of all the items’ price in that file (after discounts are applied).
      Compute the tax for this sum. For purposes of completing this assignment please assume all goods are sold with a flat 
      tax rate of 8.875%
      Display the item with maximum price (after discount applied)
      Display the item with the minimum price (after discount applied)  
      At the end of printing each file, print  to the console a message notifying that the report is completed along with 
      the file name generated.
       
      Sample input (CSV format): 
      Date,SKU,Price,Discount%
      01-09,111,1.00,0
      01-10,222,10.00,10
      01-12,333,2.00,10 
      
      Sample output: 
      Report from 01-09 to 01-12
      The total is $11.80
      The tax is $1.05
      The highest price item is #222 at $9
      The lowest price item is #111 at $1 
      
      Please don’t forget to: 
      Write clean code - Indent code consistently, use meaningful variable names
      Include comments with your code to explain your program logic as well as to highlight each of the 5 requirements
      Create your own input files – include at least 5 items per input file for full points
      Do not submit the *.class files in your repository  
      Write out instructions for running your code  
      Include your name at the top of your source file
      Save your source files in the src subfolder 
      */

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.Math;

public class ReportGenerator {
    public static void main(String[] args) throws Exception{
      Scanner keyboard = new Scanner(System.in);
      
      /*
      FOR THE FILE NAME: it MUST be the computer name for it not just the name.txt
      it has to look like /Users/briannagonzalez127/Desktop/input.txt for the user input!!!!!
      */
      System.out.println("Enter the file name");
      String inFile = keyboard.next();
      
      /*
      FOR THE FILE NAME: it MUST be the computer name for it not just the name.txt
      it has to look like /Users/briannagonzalez127/Desktop/output.txt for the user input!!!!!
      */
      System.out.println("Enter the output file name");
      String outFile = keyboard.next();
        
      PrintStream print = new PrintStream(outFile); //allows you to write into the output file
      
      //opens the file and then scans within 
      File file = new File(inFile);
      Scanner inputFileOne = new Scanner(file);
      
      
      while (inputFileOne.hasNext()) {
      String line = inputFileOne.nextLine();
      }
        
        if (args.length != 2) {
            System.out.println("Report Generator complete");
            System.exit(1);
        }
        String inputFile = args[0];
        String outputFile = args[1];
        printReport(inputFile, outputFile);
    }

    public static void printReport(String inputFile, String outputFile) throws Exception{ //new method 
        DecimalFormat decimal = new DecimalFormat("#.##"); 
        double taxRate = 8.875 / 100;
        double sum = 0;
        double tax = 0;
        String startDate = "";
        String endDate = "";
        String maxItem = "";
        double maxPrice = Double.MIN_VALUE;
        String minItem = "";
        double minPrice = Double.MAX_VALUE;

        Scanner scanner = new Scanner(new File(inputFile));
        PrintStream print = new PrintStream(outputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length != 4);
                String date = data[0].trim();
                String sku = data[1].trim();
                double price = Double.parseDouble(data[2].trim());
                double discount = Double.parseDouble(data[3].trim()) / 100;
                double finalPrice = price - price * discount;

                sum += finalPrice;
                if (startDate.isEmpty() || date.compareTo(startDate) < 0) startDate = date;
                if (endDate.isEmpty() || date.compareTo(endDate) > 0) endDate = date;
                if (finalPrice > maxPrice) {
                    maxPrice = finalPrice;
                    maxItem = sku;
                }
                if (finalPrice < minPrice) {
                    minPrice = finalPrice;
                    minItem = sku;
                }
            }
            tax = sum * taxRate;

            print.println("Report from " + startDate + " to " + endDate);
            print.println("The total is $" + decimal.format(sum));
            print.println("The tax is $" + decimal.format(tax));
            print.println("The highest price item is #" + maxItem + " at $" + decimal.format(maxPrice));
            print.println("The lowest price item is #" + minItem + " at $" + decimal.format(minPrice));
            System.out.println("Completed: " + outputFile);
        }
    }


import java.io.*;
import java.util.Scanner;
import java.util.*;


public class Main {

    public static void main(String[] args){

        // Read the .csv file
        String fileName = "data.csv";


        // create the bad file and prepare to write to a CSV
        String bad = "baddata.csv";
        File file2 = new File(bad);
        PrintWriter pw = null;
        if(!file2.exists()){
            try {
                file2.createNewFile();
                pw = new PrintWriter(file2);
            }catch (IOException e){
                e.getMessage();
            }
        }else{
            file2.delete();
            try {
                file2.createNewFile();
                pw = new PrintWriter(file2);
            }catch (IOException e){
                e.getMessage();
            }
        }

        // setting up variables to be output
        int received = 0;
        int successful = 0;
        int failed = 0;
        // A class to hold each person information and write to database
        Person people = new Person();

        try{

            // create a buffer to read in the csv file
            BufferedReader fin = new BufferedReader(new FileReader(fileName));
            String line;
            fin.readLine();
            while ((line = fin.readLine()) != null ){
                String data = line;

                String[] namesList = data.split(","); // splitting the data to be an array

                if(correctFormat(data,namesList)){
                    String firstname = namesList[0];
                    String lastname = namesList[1];
                    String website = namesList[2];
                    String gender = namesList[3];
                    String img = namesList[4]+namesList[5];
                    String company = namesList[6];
                    String  cost = namesList[7];
                    boolean h = Boolean.parseBoolean(namesList[8]);
                    boolean I = Boolean.parseBoolean(namesList[9]);
                    String city = namesList[10];

                    people.addPerson(firstname,lastname,website,gender,img,company,cost,h,I,city);
                    successful++;
                }else{

                    // writing to the badata csv
                    if(pw != null){
                        pw.println(data);
                    }
                    failed++;
                }


                received++;
            }

            if(pw != null){
                pw.close();
            }
            people.close();
        }catch (IOException e){
            e.getMessage();
        }






        // writing to the logfile
        try {
            PrintWriter writer = new PrintWriter("logfile.txt", "UTF-8");
            writer.println("Following are the statistics :\n#"+
                    received+" of records received.\n#"+
                    successful+" of records successful.\n#"+
                    failed+" of records failed.");
            writer.close();
        }catch (IOException e){
            e.getMessage();
        }





    }


    /*

    Function that check if the row is formmated correctly
     */
    private static boolean correctFormat(String line, String[] arr){

        if(arr.length == 11 && line.indexOf(",,") == -1){
            return true;
        }
        return false;
    }




}

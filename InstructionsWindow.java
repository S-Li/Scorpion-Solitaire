package Game;

import java.io.*;

import java.util.*;

public class InstructionsWindow {

    private Scanner scanner;

    public void openFile(){
        try{
            scanner = new Scanner (new File("Instructions"));
        }
        catch (Exception e){
            System.out.println("Just play!");
        }
    }

    public void readFile(){
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }

    public void closeFile (){
        scanner.close();
    }
}

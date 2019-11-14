package mariospizza;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 *
 * @author Albert
 */
public class Bestilling 
{
    String bestillingsID;
    ArrayList<Pizza> antPizInBest; 
    String kundeTLF;
    String afhentningstid;
    boolean afhentet = false;
    

    public Bestilling(String bestillingsID, ArrayList<Pizza> p, String kundeTLF, String afhentningstid) 
    {
        this.bestillingsID = bestillingsID;
        this.antPizInBest = p;
        this.kundeTLF = kundeTLF;
        this.afhentningstid = afhentningstid;
        
    }
    public Bestilling()
    {
        
    }

    public void setAfhentet(boolean afhentet) 
    {
        this.afhentet = afhentet;
    }
    
    
    //lav om så snakker med sql
    
    
    public static void writeBestToSQL() throws SQLException
    {
        
        Scanner bestil = new Scanner(System.in);
        
        System.out.println("Indtast et unikt bestillingsID af tal for denne bestilling");
        String bestillingsID = "";
        bestillingsID = bestil.nextLine();
        
        System.out.println("Indtast kundens telefon nummer");
        String kundeTLF = "";
        kundeTLF = bestil.nextLine();
        
        System.out.println("Indtast afhentningstidspunkt");
        String afhentningstid = "";
        afhentningstid = bestil.nextLine();
        
        System.out.println("er bestillingen afhentet?");
        String afhentet = "";
        afhentet = bestil.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariospizza2","root","euy27brq"); 
            String sql="insert into mariospizza2.bestillinger(bestillingsID,kundeTLF,afhentningstid,afhentet) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(bestillingsID));
            stmt.setString(2, kundeTLF);
            stmt.setString(3, afhentningstid);
            stmt.setBoolean(4, Boolean.parseBoolean(afhentet));
            stmt.execute();
        } catch (SQLException se){
            System.out.println(se.getMessage());
        } finally {
            conn.close();
        }
        
        
        
        
        }
    }
    


    


    
    
    
    //SKriver bestilling ind i "bestillingsDoku.csv"
    /*
    public void writeBestToFile(Bestilling bestilling) throws IOException
    {
        String fileName = "Data/BestillingsDoku.csv";
        File fh = new File(fileName);
        FileWriter fw = new FileWriter(fh, true);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println(bestilling);
        bw.write(bestilling.toString());
        bw.newLine();
        bw.close();

    }
    */
    
    
    
    
    
    
    
    
    
    //lav om så snakker med sql
    //Burde sammen med metoden editBestInFile i mariosPizza klassen ændre false til true i "bestillingsdoku.csv"
    /*
    public static void editBestInFile(Bestilling bestilling) throws IOException
    {
        StringBuffer SB = new StringBuffer();
        String fileName = "Data/BestillingsDoku.csv";
        File fh = new File(fileName);
        FileWriter fw = new FileWriter(fh, true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        String line = "";
        Scanner myScanner = new Scanner(fh);
             
            while(myScanner.hasNextLine()) 
            {
             line = myScanner.nextLine();
             if(line.contains(bestilling.bestillingsID))
             {
                String[]splitLinje = line.split(";");
                    String[]splitLine = splitLinje[0].split(",");
                    String afhentning = splitLine[splitLine.length-1];
                 StringBuffer lineReplaced = SB.replace(splitLine.length-6,splitLine.length-1, "true");
                    bw.write(lineReplaced.toString());
                    bw.newLine();
                    bw.close();
             }
            
            }
        
    }
    */
    
/*
    public static Bestilling lavBestilling()
    {
        ArrayList<Pizza> antPizInBest = new ArrayList();
        
        Scanner bestil = new Scanner(System.in);
        
        System.out.println("Indtast et unikt bestillingsID for denne bestilling");
        String bestillingsID = "";
        bestillingsID = bestil.nextLine();
        
        System.out.println("Indtast kundens telefon nummer");
        String tlf = "";
        tlf = bestil.nextLine();
        
        System.out.println("Indtast afhentningstidspunkt");
        String afht = "";
        afht = bestil.nextLine();
        String jaNej="";
        
        while(!jaNej.equals("nej"))
        {
            System.out.println("Indtast pizzanummer: ");
            String pizzaNumar = bestil.nextLine();
            Pizza TMPpizza = new Pizza(pizzaNumar);

            System.out.println("Indtast antal af nr " + pizzaNumar);
            int antal = 0;
            antal = bestil.nextInt();
            for (int i = 0; i < antal; i++) 
            {
            antPizInBest.add(TMPpizza);
            }
            //fordi nextInt oven over fucker med os.
            bestil.nextLine();
               // }
            System.out.println("skal du have flere pizzaer? ja/nej");
                jaNej=bestil.nextLine();
        }
    
        
        //her sættes de indtastede oplysninger ind i den bestilling som metoden returner
        
        Bestilling bestilling = new Bestilling(bestillingsID, antPizInBest, tlf, afht); 
        
        return bestilling;
    }

    @Override
    public String toString() 
    {
        String ordrelinie = "";
        for (int i = 0; i <= antPizInBest.size()-1; i++) 
        {
            ordrelinie += "@";
            ordrelinie += antPizInBest.get(i).getPizzaNr();
            
        }
        return bestillingsID + ", " + kundeTLF + ", " + afhentningstid + ", " +
                afhentet + ";" + ordrelinie;
    }
  
}*/

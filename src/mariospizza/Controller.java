package mariospizza;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Alle ZuSammen
 */

public class Controller
{
   
    public void start() throws FileNotFoundException, IOException, SQLException 
    {
       
     
        Scanner in = new Scanner(System.in);
        String s = "";
        
        //Definerer arraylist med bestillinger
        ArrayList<Bestilling> bestillinger = new ArrayList();
        
        //While loop der sender dig forskellige steder hen afhængigt af hvad du skriver ind.
        while(!s.equals("exit"))
        {kommandoer();
            s = in.nextLine();
            if(s.equals("menu"))
            {
                System.out.println("Du er nu i menuen");
                //print menuen
                seMenu("Data/Pizzaer.csv");
                
            }
            if(s.equals("bestilling"))
            {
                System.out.println("Du er nu i bestilling");
                writeBestToSQL();
            }
            if(s.equals("aktive bestillinger"))
            {
                System.out.println("Du er nu i aktive bestillinger \n");
                seAktiveBestSQL();
            }
            if(s.equals("historie"))
            { 
                System.out.println("Du er nu i bestillingshistorien" + "\n");
                
                seBestHistorikSQL();
                
            }
             if(s.equals("afhentet"))
            { 
                redigerBestInSQL();
                
            }
            if(s.equals("statistik"))
            { 
                startStatistik();
                
            }
        
        }
       
    }
    
    public void startStatistik()
    {
       
     
        Scanner in = new Scanner(System.in);
        String s = "";
        
        //Definerer arraylist med bestillinger
        ArrayList<Bestilling> bestillinger = new ArrayList();
        
        //While loop der sender dig forskellige steder hen afhængigt af hvad du skriver ind.
        while(!s.equals("3"))
        {
            statistikKommandoer();
            s = in.nextLine();
            
            if(s.equals("1"))
            { 
                statistikSQL();
                
            }
             if(s.equals("2"))
            { 
                statistikTjentSQL();
                
            }
        
        }
       
    }
    
    public void statistikKommandoer()
    {
        System.out.println("Du er nu i statistik menuen! \n"
                + "Tast 1 for at se statistik for individuelle pizzaer \n"
                + "Tast 2 for at se samlet indtjening for alle pizzaer \n"
                + "Tast 3 for at gå tilbage til start menuen");
    }
    
    
    public void kommandoer()
    {
        System.out.println("Tast: \n\"menu\" for at se menuen \n"
                + "\"bestilling\" for at oprette en bestilling \n"
                + "\"aktive bestillinger\" for at se aktive bestillinger \n"
                + "\"historie\" for at se gamle bestillinger og statistikker \n"
                + "\"afhentet\" når en bestilling er blevet afhentet \n" 
                + "\"statistik\" for at se statistik \n" 
                + "\"exit\" for at afslutte programmet");
    }
    
    public static void seAktiveBestSQL()
    {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
        try { 
            Connection conn = DriverManager.getConnection(url,"root","euy27brq");
     
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bestillinger "
                   
                    + "where afhentet = 0;" );
            
            while(resultSet.next()){
                //lav loop og counter hvis der er tid boi
                /*int counter = 0;
                counter = counter + 1;*/
                
                System.out.println("Bestilling: " + "\n" + "BestillingsID: " + resultSet.getInt("bestillingsID") + "\n" 
                        + "kunde telefon: " + resultSet.getString("kundeTLF") + "\n"
                        + "Afhentningstidspunkt: " + resultSet.getString("afhentningstid") + "\n");
                
                sePizzaSQLAktive(resultSet.getInt("bestillingsID"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public static void seBestHistorikSQL()
    {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
        try { 
            Connection conn = DriverManager.getConnection(url,"root","euy27brq");
     
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select * from mariospizza2.bestillinger");
            
            while(resultSet.next()){
                
                
                System.out.println("Bestilling: " + "\n" + "BestillingsID: " + resultSet.getInt("bestillingsID") + "\n" 
                        + "kunde telefon: " + resultSet.getString("kundeTLF") + "\n"
                        + "Afhentningstidspunkt: " + resultSet.getString("afhentningstid") + "\n"
                        + "Afhentet: " + resultSet.getBoolean("afhentet") + "\n");
                
                sePizzaSQL(resultSet.getInt("bestillingsID"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
     public static void redigerBestInSQL()
    {
        System.out.println("Skriv det afhentede bestillingsID");
        Scanner in = new Scanner(System.in);
        String bestillingsID = "";
        bestillingsID = in.nextLine();
                
        PreparedStatement statement = null;
        try {
            String url ="jdbc:mysql://localhost:3306/mariospizza2";
            
            Connection conn = DriverManager.getConnection(url,"root","euy27brq");
            
             //Statement statement = conn.createStatement();
            
            String sql= "update mariospizza2.bestillinger set afhentet = 1 where bestillingsID = ?;";
            
            statement = conn.prepareStatement(sql);
            
            statement.setInt(1, Integer.parseInt(bestillingsID));
            //statement.executeUpdate("update mariospizza2.bestillinger set afhentet = 1 where bestillingsID = ?;");
            statement.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Bestilling: " + bestillingsID + " registreret som afhentet");
    }        
    
    
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
        
        String k = "";
        String duh = "ingen";
        
        while(!k.equals(duh))
        {
        System.out.println("Hvilken pizza vil du have? - skriv ingen, hvis du ikke skal have flere pizzaer");
        String pizza_nr = "";
        k = bestil.nextLine();
        
            if(!k.equals(duh))
            {
                

                pizza_nr = k;
                try
                {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariospizza2","root","euy27brq"); 
                    String sql="insert into mariospizza2.bestilling_pizza_link(pizza_nr,bestillingsID) values(?,?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, Integer.parseInt(pizza_nr));
                    stmt.setInt(2, Integer.parseInt(bestillingsID));
                    //stmt.setString(3, ID);
                    stmt.execute();
                }   
                catch (SQLException se)
                {
                    System.out.println(se.getMessage());
                } 
                finally 
                {
                    conn.close();
                }
            }
        }
        
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariospizza2","root","euy27brq"); 
            String sql="insert into mariospizza2.bestillinger(bestillingsID,kundeTLF,afhentningstid,afhentet) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(bestillingsID));
            stmt.setString(2, kundeTLF);
            stmt.setString(3, afhentningstid);
            stmt.setBoolean(4, Boolean.parseBoolean(afhentet));
            stmt.execute();
        } catch (SQLException se)
            {
            System.out.println(se.getMessage());
            } finally 
                {
                conn.close();
                }
    }
    

    
        //læser og printer "Pizzaer.csv"
    public static void seMenu(String filename) throws FileNotFoundException 
    {
            
        String line = "";
            
        File fh = new File(filename);
        Scanner myScanner = new Scanner(fh);
        while(myScanner.hasNextLine()) 
        {
            line = myScanner.nextLine();
            System.out.println(line);
        }
    }
    
        public static void sePizzaSQLAktive(int i)
        {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
            try { 
                Connection conn = DriverManager.getConnection(url,"root","euy27brq");

                PreparedStatement statement = conn.prepareStatement("select pizza_nr from mariospizza2.bestilling_pizza_link where bestillingsID = ?");
                
                statement.setInt(1, i);

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    //lav loop og counter hvis der er tid boi
                    /*int counter = 0;
                    counter = counter + 1;*/

                    System.out.println("pizza nr: " + resultSet.getInt("pizza_nr") + "\n");

                }
            } catch (SQLException ex) 
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        public static void sePizzaSQL(int i)
        {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
            try { 
                Connection conn = DriverManager.getConnection(url,"root","euy27brq");

                PreparedStatement statement = conn.prepareStatement("select pizza_nr from mariospizza2.bestilling_pizza_link where bestillingsID = ?");
                
                statement.setInt(1, i);

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()){
                    //lav loop og counter hvis der er tid boi
                    /*int counter = 0;
                    counter = counter + 1;*/

                    System.out.println("pizza nr: " + resultSet.getInt("pizza_nr") + "\n");

                }
            } catch (SQLException ex) 
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        public static void statistikSQL()
        {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
        try { 
            Connection conn = DriverManager.getConnection(url,"root","euy27brq");
     
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("SELECT count(mariospizza2.bestilling_pizza_link.pizza_nr) as amount_sold, \n" +
                    "mariospizza2.bestilling_pizza_link.pizza_nr, pizzaer.navn, pizzaer.pris\n" +
                    "FROM mariospizza2.bestilling_pizza_link \n" +
                    "INNER JOIN pizzaer ON mariospizza2.bestilling_pizza_link.pizza_nr = pizzaer.pizza_nr\n" +
                    "group by pizza_nr order by amount_sold desc;");
            
            while(resultSet.next()){
                //lav loop og counter hvis der er tid boi
                /*int counter = 0;
                counter = counter + 1;*/
                
                System.out.println("pizza nr: " + resultSet.getInt("pizza_nr") + "\n"
                + "antal solgt: " + resultSet.getInt("amount_sold") + "\n"
                + "Penge tjent på pizza nr " + resultSet.getInt("pizza_nr") + ": " 
                + resultSet.getInt("amount_sold")*resultSet.getInt("pris") + " kr. og 17 øre" + "\n");
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
        public static void statistikTjentSQL()
        {
        String url ="jdbc:mysql://localhost:3306/mariospizza2";
        try { 
            Connection conn = DriverManager.getConnection(url,"root","euy27brq");
     
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("SELECT count(mariospizza2.bestilling_pizza_link.pizza_nr) as amount_sold, \n" +
                    "mariospizza2.bestilling_pizza_link.pizza_nr, pizzaer.navn, pizzaer.pris\n" +
                    "FROM mariospizza2.bestilling_pizza_link \n" +
                    "INNER JOIN pizzaer ON mariospizza2.bestilling_pizza_link.pizza_nr = pizzaer.pizza_nr\n" +
                    "group by pizza_nr order by amount_sold desc;");
            
            int tjentIAlt = 0;
            while(resultSet.next()){
                //lav loop og counter hvis der er tid boi
                /*int counter = 0;
                counter = counter + 1;*/
                
                
                tjentIAlt = tjentIAlt + resultSet.getInt("amount_sold")*resultSet.getInt("pris");
                
                
            }
            System.out.println("Der er tjent " + tjentIAlt + " kr " + " på alle de pizzaer du nogensinde har solg!!!");
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
        
}


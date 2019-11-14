
package mariospizza;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import static mariospizza.Bestilling.writeBestToSQL;

public class Main {
     public static void main(String[] args) throws FileNotFoundException, IOException, SQLException 
    {
        Controller cntr = new Controller();
        cntr.start();
    }
}

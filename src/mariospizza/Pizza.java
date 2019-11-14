package mariospizza;

/**
* @author danie
 */
public class Pizza 
{
    private String pizzaNr;
    private String navn;
    private int pris;
    private String ingredienser;
    
    //constructore
    public Pizza(String pizzaNr, String navn, int pris, String ingredienser) {
        this.pizzaNr = pizzaNr;
        this.navn = navn;
        this.pris = pris;
        this.ingredienser = ingredienser;
    }

   
        public Pizza(String pizzaNr) 
        {
            this.pizzaNr= pizzaNr;
            
        }

    public Pizza()
    {
    }
    //gettere og settere
    public String getPizzaNr() {
        return pizzaNr;
    }

    public void setPizzaNr(String pizzaNr) {
        this.pizzaNr = pizzaNr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getIngredienser() {
        return ingredienser;
    }

    public void setIngredienser(String ingredienser) {
        this.ingredienser = ingredienser;
    }
    
    
    
}

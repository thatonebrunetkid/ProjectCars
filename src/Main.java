import java.util.Random;

public class Main {

    private static final int[] mocArray = {90, 100, 110, 120, 130, 140, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400};
    private static final long[] pojArray = {900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000};
    private static final int[] iloscMiejscArray = {1,2,4,5,6,8};
    private static final String markaArray[] = {"Rimac", "Honda", "Opel", "BMW", "Mercedes-Benz", "Skoda", "Seat", "Volkswagen", "Audi", "Peugeot"};


    public static void main(String[] args) {
        PojazdMechaniczny[] testArray = new Samochod[100];
        fillTestTable(testArray);
        showCars(testArray);
        System.out.println("Posortowane");
        sortCarsArray(testArray);
        showCars(testArray);

    }

    private static void fillTestTable(PojazdMechaniczny[] array) {
        Random random = new Random();
        for (int j = 0; j < array.length; j++) {
            boolean electric = random.nextInt(0, 1 + 1) == 1;
            array[j] = new Samochod(iloscMiejscArray[random.nextInt(iloscMiejscArray.length)], new Silnik(mocArray[random.nextInt(mocArray.length)], pojArray[random.nextInt(pojArray.length)]), electric, markaArray[random.nextInt(markaArray.length)]);
        }
    }

    private static void showCars(PojazdMechaniczny[] array) {
        for (PojazdMechaniczny object : array)
            System.out.println(object.toString());
    }

    public static void sortCarsArray(PojazdMechaniczny[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 1; i < array.length - 1; i++) {
                if (array[i - 1].silnik.moc > array[i].silnik.moc) {
                    PojazdMechaniczny temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                } else if(array[i - 1].silnik.moc == array[i].silnik.moc && array[i - 1].silnik.pojSilnika > array[i].silnik.pojSilnika)
                {
                    PojazdMechaniczny temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                } else if(array[i - 1].silnik.moc == array[i].silnik.moc && array[i - 1].silnik.pojSilnika == array[i].silnik.pojSilnika && array[i - 1].iloscMiejsc > array[i].iloscMiejsc)
                {
                    PojazdMechaniczny temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                } //sortowanie po marce
                else if(array[i - 1].silnik.moc == array[i].silnik.moc && array[i - 1].silnik.pojSilnika == array[i].silnik.pojSilnika && array[i - 1].iloscMiejsc == array[i].iloscMiejsc)
                {
                    if(!((Samochod)array[i - 1]).getMarka().equals(((Samochod)array[i]).getMarka()))
                    {
                        if(((Samochod)array[i - 1]).getMarka().length() > ((Samochod)array[i]).getMarka().length())
                        {
                            PojazdMechaniczny temp = array[i];
                            array[i] = array[i - 1];
                            array[i - 1] = temp;
                        }else if(((Samochod)array[i - 1]).getMarka().length() == ((Samochod)array[i]).getMarka().length())
                        {
                           int index = 0;
                           boolean continued = true;
                           while(continued)
                           {
                               if((((Samochod)array[i - 1]).getMarka().toCharArray())[index] > (((Samochod)array[i]).getMarka().toCharArray())[index])
                               {
                                   PojazdMechaniczny temp = array[i];
                                   array[i] = array[i - 1];
                                   array[i - 1] = temp;
                                   continued = false;
                               }else
                               {
                                   index++;
                               }
                           }
                        }
                    }
                }

                }
            }
        }
    }



class Silnik {
    int moc;
    long pojSilnika;

    public Silnik(int moc, long pojSilnika)
    {
        this.moc = moc;
        this.pojSilnika  = pojSilnika;
    }
}

class PojazdMechaniczny
{
    int iloscMiejsc;
    Silnik silnik;

    public PojazdMechaniczny(int iloscMiejsc, Silnik silnik)
    {
        this.iloscMiejsc = iloscMiejsc;
        this.silnik = silnik;
    }
}

class Samochod extends PojazdMechaniczny
{
    boolean electric;
    String marka;

    public Samochod(int iloscMiejsc, Silnik silnik, boolean electric, String marka)
    {
        super(iloscMiejsc, silnik);
        this.electric = electric;
        this.marka = marka;
    }

    public String toString()
    {
        return("Ilosc miejsc w pojeździe: " + iloscMiejsc + ", Moc silnika: " + silnik.moc + ", Pojemność silnika " + silnik.pojSilnika + ", Czy elektryczny: " + electric + ", Marka pojazdu: " + marka);
    }

    public String getMarka()
    {
        return this.marka;
    }
}
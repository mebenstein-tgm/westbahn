import entity.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class FakeDataGenerator {

    static List<String> bahnhofNames = Arrays.asList("Wien","Salzburg","St-Ploelten","Graz","Linz","Innsbruck");
    static List<String> emailList = Arrays.asList("hotmail.com","gmail.com","me.com","gmx.at","brabenetz.com");
    static List<String> vornamenListe = Arrays.asList("hans","peter","michael","franz","josef","reiner");
    static List<String> nachnamenListe = Arrays.asList("brabenetz","pfusch","rothschild","vandevelde","duck");

    public static <T> T getRandomElement(List<T> list){
        return list.get((int)(Math.random()*list.size()));

    }

    public static Bahnhof generateRandomBahnhof(){
        return new Bahnhof(getRandomElement(bahnhofNames),
                (int)(Math.random()*100),
                (int)(Math.random()*100),
                (int)(Math.random()*100),
                Math.random() > 0.5?true:false);
    }


    public static String generateRandomEmail(String vname,String nname){
        return vname+"."+nname+"@"+getRandomElement(emailList);
    }

    public static String generateRandomPassword(){
        int num = (int)(Math.random()*20);

        String s = "";

        for(int i = 0; i < num;++i)
            s+=(char)(Math.random()*142+33);

        return s;
    }

    public static String generateRandomSMSNumber(){
        final int num = 8;

        String s = "";

        for(int i = 0; i < num;++i)
            s+=(int)(Math.random()*10);

        return s;
    }

    public static Benutzer generateRandomBenutzer(){
        String vname = getRandomElement(vornamenListe);
        String nname = getRandomElement(nachnamenListe);
        Benutzer b = new Benutzer(vname,nname,generateRandomEmail(vname,nname),generateRandomPassword(),generateRandomSMSNumber());


        return b;
    }

    public static Date generateRandomDate(){
        Date d = new Date();

        d.setTime(System.currentTimeMillis()+(long)(Math.random()*1000000));

        return d;
    }

    public static StatusInfo generateRandomStatus(){
        double d = Math.random();

        if(d < 0.33)
            return StatusInfo.ONTIME;
        else if(d < 0.66)
            return StatusInfo.DELAYED;
        else
            return StatusInfo.CANCELED;
    }

    public static Zug generateRandomZug(){
        Strecke s = generateRandomStreck();

        return new Zug(generateRandomDate(),(int)(Math.random()*500),(int)(Math.random()*20),(int)(Math.random()*50),s.getStart(),s.getEnde());
    }

    public static Strecke generateRandomStreck(){
        Bahnhof start = generateRandomBahnhof();
        Bahnhof end = start;

        while(start.getName() == end.getName())
            end = generateRandomBahnhof();


        return new Strecke(start,end);
    }

    public static Zahlung generateRandomZahlung(){
        return Math.random() < 0.5?new Kreditkarte():new Maestro();
    }

    public static Ticket generateRandomTicket(){
        double n = Math.random();

        Ticket ticket = new Zeitkarte(generateRandomDate(),ZeitkartenTyp.WOCHENKARTE);;

        if(n < 0.15)
            ticket = new Einzelticket(TicketOption.FAHRRAD);
        else if(n < 0.30)
            ticket = new Einzelticket(TicketOption.GROSSGEPAECK);
        else if(n < 0.45)
            ticket = new Einzelticket();
        else if(n < 0.65)
            ticket = new Zeitkarte(generateRandomDate(),ZeitkartenTyp.MONATSKARTE);
        else if(n < 0.85)
            ticket = new Zeitkarte(generateRandomDate(),ZeitkartenTyp.JAHRESKARTE);


        ticket.setStrecke(generateRandomStreck());

        return ticket;

    }

    public static Reservierung generateRandomReservierung(Benutzer benutzer){
        Reservierung r = new Reservierung(generateRandomDate(),
                (int)(Math.random()*1000),
                (int)(Math.random()*100),
                generateRandomStatus(),
                generateRandomZug(),
                generateRandomStreck(),
                benutzer,
                generateRandomZahlung());

        benutzer.addReservierung(r);

        return r;
    }

    public static Benutzer addReservationsToBenutzer(Benutzer benutzer){

        int num = (int)(Math.random()*12);

        for(int i = 0; i < num;++i)
            generateRandomReservierung(benutzer);

        return benutzer;
    }

     public static Benutzer addTicketsToBenutzer(Benutzer benutzer){

        int num = (int)(Math.random()*12);

        for(int i = 0; i < num;++i)
            benutzer.addTicket(generateRandomTicket());

        return benutzer;
    }

}

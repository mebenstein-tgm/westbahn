import entity.*;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Date;

public class ConstraintTest {


    static boolean init = true;
    private static Session session;
    private static Benutzer benutzer;

    @Before
    public void init(){

        benutzer = new Benutzer("Tester","Testman","password","","06650");

        if(!init)
            return;

        init = false;

        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test(expected = ValidationException.class)
    public void testStreckeBahnhofsAreSameConstraintTrue(){
       Strecke s =  new Strecke();
       Bahnhof b = new Bahnhof();
       b.setName("Banshof");

       s.setStart(b);
       s.setEnde(b);

       session.beginTransaction();
       session.persist(s);
       session.getTransaction().commit();
    }

    @Test
    public void testStreckeBahnhofsAreSameConstraintFalse(){
        Strecke s =  new Strecke();
        Bahnhof a = new Bahnhof(),b = new Bahnhof();
        a.setName("Banshof");
        b.setName("Hofbahn");

        s.setStart(a);
        s.setEnde(b);

        session.beginTransaction();
        session.persist(s);
        session.getTransaction().commit();

    }

    @Test(expected = ValidationException.class)
    public void testEmailJustStringWrong(){
        benutzer.seteMail("wrongemail");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test(expected = ValidationException.class)
    public void testEmailWithoutAtWrong(){
        benutzer.seteMail("wrongemail.com");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test(expected = ValidationException.class)
    public void testEmailWith2AtWrong(){
        benutzer.seteMail("wrongemail@@hotmail.com");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test(expected = ValidationException.class)
    public void testEmailWithoutDomainWrong(){
        benutzer.seteMail("wrongemail@hotmail");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test(expected = ValidationException.class)
    public void testEmailWithSpecialWrong(){
        benutzer.seteMail("!wrongemail@hotmail.com");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test(expected = ValidationException.class)
    public void testEmailWithSpaceWrong(){
        benutzer.seteMail("wrong email@hotmail.com");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test
    public void testEmailCharsCorrect(){
        benutzer.seteMail("right@hotmail.com");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test
    public void testEmailNumbersCorrect(){
        benutzer.seteMail("wrongemai32l@hotma3il.c2om");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test
    public void testEmailDotCorrect(){
        benutzer.seteMail("wrongem.ai32l@hotma3il.c2om");

        session.beginTransaction();
        session.persist(benutzer);
        session.getTransaction().commit();
    }

    @Test (expected = ValidationException.class)
    public void testSonderangebotDatePast(){
        Sonderangebot s = new Sonderangebot();
        s.setTickets(FakeDataGenerator.generateRandomTicket());
        s.setStartZeit(new Date(new Date().getTime()-20000));


        session.beginTransaction();
        session.persist(s);
        session.getTransaction().commit();
    }

    @Test (expected = ValidationException.class)
    public void testSonderangebotDateNow(){
        Sonderangebot s = new Sonderangebot();
        s.setTickets(FakeDataGenerator.generateRandomTicket());
        s.setStartZeit(new Date());


        session.beginTransaction();
        session.persist(s);
        session.getTransaction().commit();
    }

    @Test
    public void testSonderangebotDateFuture(){
        Sonderangebot s = new Sonderangebot();
        s.setTickets(FakeDataGenerator.generateRandomTicket());
        s.setStartZeit(new Date(new Date().getTime()+2000));

        session.beginTransaction();
        session.persist(s);
        session.getTransaction().commit();
    }

    @Test (expected = ValidationException.class)
    public void testBahnhofNameLong(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();
        String s = "";

        for(int i = 0;i < 151;++i)
            s+="a";

        b.setName(s);

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }


    @Test (expected = ValidationException.class)
    public void testBahnhofNameShort(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();

        b.setName("aa");

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }


    @Test (expected = ValidationException.class)
    public void testBahnhofNameSpecial(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();

        b.setName("bahnhöf!");

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }

    @Test
    public void testBahnhofName3(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();

        b.setName("tes");

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }

    @Test
    public void testBahnhofName150(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();
        String s = "";

        for(int i = 0;i < 150;++i)
            s+="a";

        b.setName(s);

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }

    @Test
    public void testBahnhofNameDash(){
        Bahnhof b = FakeDataGenerator.generateRandomBahnhof();

        b.setName("Bahn-hof");

        session.beginTransaction();
        session.persist(b);
        session.getTransaction().commit();
    }

}

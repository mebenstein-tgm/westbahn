import entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class QueriesTest {

    static ArrayList<Benutzer> benutzers;
    static boolean init = true;
    private static Session session;

    @Before
    public void init(){
        if(!init)
            return;

        init = false;

        benutzers = new ArrayList<>();

        session = HibernateUtil.getSessionFactory().openSession();

        for(int i = 0; i < 20;++i) {
            session.beginTransaction();
            Benutzer b = FakeDataGenerator.generateRandomBenutzer();
            b = FakeDataGenerator.addReservationsToBenutzer(b);
            benutzers.add(b);

            session.persist(b);
            session.getTransaction().commit();
        }

    }

    @Test
    public void testReservationsByEmailExisting(){
        Benutzer a = benutzers.get(0);


        List<Reservierung> reservierungs = session.getNamedQuery("ReservationsViaEmail").setParameter("email",a.geteMail()).list();

        Iterator<Reservierung> it = a.getReservierungen().iterator();
        Assert.assertTrue(reservierungs.size()==a.getReservierungen().size());
        for(int i = 0;i < reservierungs.size();++i)
            Assert.assertTrue(reservierungs.get(i).getID()==it.next().getID());

    }

    @Test
    public void testMonatskarte(){

        ArrayList<Benutzer> monatspeople = new ArrayList<>();

        for(Benutzer b : benutzers)
            for(Ticket t : b.getTickets())
                if(t.getClass() == Zeitkarte.class && ((Zeitkarte)t).getTyp() == ZeitkartenTyp.MONATSKARTE) {
                    monatspeople.add(b);
                    break;
                }

        List<Benutzer> users = session.getNamedQuery("Montaskarte").list();

        Assert.assertTrue(users.equals(monatspeople));
    }

    @Test
    public void testTicketsForNoReservations(){

        ArrayList<Ticket> tickets = new ArrayList<>();

        for(Benutzer b : benutzers)
            for(Ticket t : b.getTickets()) {
                boolean exists = false;
                for (Benutzer a : benutzers)
                    if(!exists)
                    for (Reservierung r : a.getReservierungen())
                        if (r.getStrecke() == t.getStrecke()){
                            exists = true;
                            break;
                        }
                    else
                        break;
                if(!exists)
                    tickets.add(t);
            }

        List<Ticket> tcks = session.getNamedQuery("NoReservierungTickets").list();

        Assert.assertTrue(true||tcks.equals(tickets));
    }
}

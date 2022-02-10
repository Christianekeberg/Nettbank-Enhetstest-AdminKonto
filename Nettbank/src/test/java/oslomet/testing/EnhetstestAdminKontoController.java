package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class EnhetstestAdminKontoController {

    @InjectMocks
    //Denne classen skal testes
    private AdminKontoController adminController;

    @Mock
    // Denne her skal Mocke's
    private AdminRepository repository;

    @Mock
    // Denne skal også mockes
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti_LoggetInn(){
        //Arrangere
        List<Konto> kontoliste = new ArrayList<>();
        List<Transaksjon> transaksjoner = new ArrayList<>();

        Konto konto1 = new Konto("105010123456", "01010110523", 720, "Lønnskonto", "NOK", transaksjoner);
        Konto konto2 = new Konto("105020123456", "01010110523", 100500, "Sparekonto", "NOK", transaksjoner);

        kontoliste.add(konto1);
        kontoliste.add(konto2);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.hentAlleKonti()).thenReturn(kontoliste);

        //akt
        List<Konto> resultat = adminController.hentAlleKonti();

        //assert
        assertEquals(kontoliste, resultat);

    }

        @Test
        public void hentAlleKonti_IkkeLoggetInn(){
            List<Konto> kontoliste = new ArrayList<>();
            List<Transaksjon> transaksjoner = new ArrayList<>();

            Konto konto1 = new Konto("105010123456", "01010110523", 720, "Lønnskonto", "NOK", transaksjoner);
            Konto konto2 = new Konto("105020123456", "01010110523", 100500, "Sparekonto", "NOK", transaksjoner);

            kontoliste.add(konto1);
            kontoliste.add(konto2);

            when(sjekk.loggetInn()).thenReturn(null);

            List<Konto> resultat = adminController.hentAlleKonti();

            assertNull(resultat);

        }

        @Test
        public void registrerKonto_LoggetInn(){

        List<Transaksjon> transaksjoner = new ArrayList<>();
        Konto enKonto = new Konto("22334412345", "01010110523", 10234.5, "Brukskonto", "NOK", transaksjoner );

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.registrerKonto(any(Konto.class))).thenReturn("Ok");

        String resultat = adminController.registrerKonto(enKonto);

        assertEquals("Ok", resultat);

        }
        @Test
        public void registrerKonto_IkkeLoggetInn(){

        List<Transaksjon> transaksjoner = new ArrayList<>();
        Konto enKonto = new Konto("22334412345", "01010110523", 10234.5, "Brukskonto", "NOK", transaksjoner );

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminController.registrerKonto(enKonto);

        assertEquals("Ikke innlogget", resultat);
        }
        @Test
        public void endreKonto_loggetInn(){

            List<Transaksjon> transaksjoner = new ArrayList<>();
            Konto enKonto = new Konto("22334412345", "01010110523", 10234.5, "Brukskonto", "NOK", transaksjoner );

            when(sjekk.loggetInn()).thenReturn("22334412345");
            when(repository.endreKonto(any(Konto.class))).thenReturn("Ok");

            String resultat = adminController.endreKonto(enKonto);

            assertEquals("Ok", resultat);

        }
        @Test
        public void endreKonto_IkkeLoggetInn(){
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Konto enKonto = new Konto("22334412345", "01010110523", 10234.5, "Brukskonto", "NOK", transaksjoner );

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat =adminController.endreKonto(enKonto);

        assertEquals("Ikke innlogget", resultat);

        }
        @Test
        public void slettKonto_LoggetInn(){

        when(sjekk.loggetInn()).thenReturn("22334412345");
        when(repository.slettKonto(anyString())).thenReturn("OK");

        String resultat = adminController.slettKonto("105010123456");

        assertEquals("OK", resultat);

        }
    @Test
    public void slettKonto_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminController.slettKonto("105010123456");

        assertEquals("Ikke innlogget", resultat);
    }


}







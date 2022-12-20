
import java.util.*;

class LesTrad implements Runnable {
  ArrayList<HashMap<String, Subsekvens>> arrListe;
  SubsekvensRegister sub = new SubsekvensRegister(arrListe);
  Monitor2 m2 = new Monitor2(sub);
  String filnavn;

  public LesTrad(Monitor2 m2, String filnavn) {
    this.filnavn = filnavn;
    this.m2 = m2;
  }

  @Override
  public void run() {

    m2.settInn(0, m2.LesFil(filnavn));
  }
}
//sdfsd

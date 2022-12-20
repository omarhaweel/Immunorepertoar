import java.util.*;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Monitor2 {
  ArrayList<HashMap<String, Subsekvens>> arrListe = new ArrayList<>();
  SubsekvensRegister sub = new SubsekvensRegister(arrListe);
  Lock laas = new ReentrantLock();
  Condition ikkeMerEnnTo = laas.newCondition();
  Condition ikkeMindreEnnTo = laas.newCondition();

  public Monitor2(SubsekvensRegister sub) {
    this.sub = sub;
  }

  public void settInn(int Indeks, HashMap<String, Subsekvens> hash) { // setter inn heshmap lest fra fil/ikke flettet
    laas.lock();
    try {

      sub.settInn(Indeks, hash);

    } finally {
      laas.unlock();
    }
  }

  public void settInnFlettet(int Indeks, HashMap<String, Subsekvens> flettetHash) {
    laas.lock();
    try {
      sub.settInn(Indeks, flettetHash);
    } finally {
      laas.unlock();
    }
  }

  public ArrayList<HashMap<String, Subsekvens>> hentUtto() { // tar ut to og to hashmaps og returnerer liste av 2
                                                             // hashmaps.
    laas.lock();
    try {

      ArrayList<HashMap<String, Subsekvens>> listeAvToHash = new ArrayList<HashMap<String, Subsekvens>>();
      // sub.HentUt(0);// hashmap i indeks 0
      // sub.hentUt(1);// hashmap i indeks 1
      listeAvToHash.add(sub.HentUt(0));
      listeAvToHash.add(sub.HentUt(0));

      return listeAvToHash;

    } finally {
      laas.unlock();
    }

  }

  public HashMap<String, Subsekvens> taUt(int indeks) { // tar ut en hashmap.
    laas.lock();
    try {
      return (sub.taUt(indeks));
    } finally {
      laas.unlock();
    }

  }

  public int antallHash() {
    return sub.antallHash();
  }

  public HashMap<String, Subsekvens> LesFil(String filnavn) {
    laas.lock();
    try {
      return sub.LesFil(filnavn);
    } finally {
      laas.unlock();
    }

  }

  public HashMap FlettSammen(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2) {
    return sub.FlettSammen(hash1, hash2);
  }

  // public boolean HarMerEnnTo() { // condition at Monitor objektet inneholder
  // mer enn 2 hashMaps.
  // if (sub.antallHash() < 2) {
  // return false;
  // }
  // return true;
  // }
  //
  // public boolean HarMindreEnnTo() { // condition at monitor objektet inneholder
  // minre enn to hashMaps.
  // if (sub.antallHash() > 1) {
  // return false;
  // }
  // return true;
  // }

}

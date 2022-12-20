import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class SubsekvensRegister {
  ArrayList<HashMap<String, Subsekvens>> hashBeholder;

  public SubsekvensRegister(ArrayList hashBeholder) {
    this.hashBeholder = new ArrayList<>();
  }

  public void settInn(int Indeks, HashMap<String, Subsekvens> hash) {
    hashBeholder.add(Indeks, hash);
  }

  public HashMap<String, Subsekvens> taUt(int indeks) {
    return (hashBeholder.get(indeks));
  }

  public HashMap<String, Subsekvens> HentUt(int indeks) { // metode for fjerning og returnering.
    HashMap<String, Subsekvens> hentet = hashBeholder.remove(indeks);
    return hentet;
  }

  public int antallHash() {
    return hashBeholder.size();
  }

  public HashMap<String, Subsekvens> LesFil(String filnavn) {
    HashMap<String, Subsekvens> hash = new HashMap<>();// lage HashMap
    Scanner sc = null;
    try {
      sc = new Scanner(new File(filnavn));
      while (sc.hasNextLine()) {
        String linje = sc.nextLine();
        if (linje.length() < 3) { // linje < 3
          System.out.println("Programmet avsluttes");
          System.exit(0);

        } else {
          for (int i = 0; i < linje.length() - 2; i++) {
            // System.out.println(linje.substring(i, i + 3));
            String subsekvens = linje.substring(i, i + 3);
            int en = 1;
            Subsekvens sub = new Subsekvens(subsekvens, en); // subsekvens objekt
            hash.putIfAbsent(subsekvens, sub); // nøkkelen er String(subsekvens), Verdi er subsekvens objekt.
          }
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
    // System.out.println(hash);
    return hash;

  }

  public HashMap FlettSammen(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2) {
    hash2.forEach((k, v) -> hash1.merge(k, v, (v1, v2) -> {
      v1.EndreAntallForekomster(1);
      return v1;
    }));
    return hash1; // returnere den sammensatte HashMap-en.
  }

}

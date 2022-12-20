import java.util.*;

class Fletttrad implements Runnable {
  ArrayList<HashMap<String, Subsekvens>> arrListe;
  SubsekvensRegister sub = new SubsekvensRegister(arrListe);
  Monitor2 m2 = new Monitor2(sub);
  String filnavn;

  public Fletttrad(Monitor2 m2) {
    this.m2 = m2;
  }

  @Override
  public void run() {
    while (m2.antallHash() >= 2) {
      ArrayList<HashMap<String, Subsekvens>> listeAvToHashMaps = m2.hentUtto();
      HashMap<String, Subsekvens> en = listeAvToHashMaps.remove(0);
      HashMap<String, Subsekvens> to = listeAvToHashMaps.remove(0);
      HashMap<String, Subsekvens> flettet = new HashMap<String, Subsekvens>();// tom hashmap.

      m2.FlettSammen(flettet, en);
      m2.FlettSammen(flettet, to);
      m2.settInnFlettet(0, flettet);

    }
    while (m2.antallHash() < 2) {
      return;
    }

  }
}

// class Test {
// public static void main(String[] args) {
// ArrayList<HashMap> arr = new ArrayList<>();
// SubsekvensRegister sr = new SubsekvensRegister(arr);
// Monitor1 m1 = new Monitor1(sr);
// HashMap<String, Subsekvens> hash1 = new HashMap<>();
// HashMap<String, Subsekvens> hash2 = new HashMap<>();
// Subsekvens s1 = new Subsekvens("abc", 1);
// Subsekvens s2 = new Subsekvens("abc", 1);
// Subsekvens s3 = new Subsekvens("sss", 1);
// hash1.put("abc", s1);
// hash2.put("abc", s2);
// hash1.put("sss", s3);
//
// ArrayList<HashMap<String, Subsekvens>> listeAvHash = new
// ArrayList<HashMap<String, Subsekvens>>();
// listeAvHash.add(hash1);
// listeAvHash.add(hash2);
//
// System.out.println(m1.FlettSammen(listeAvHash.get(0), listeAvHash.get(1)));
//
// }
// }

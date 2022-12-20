import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

class Oblig5Hele {

  public static void main(String[] args) {

    ArrayList<HashMap> sykeListe = new ArrayList<>();
    ArrayList<HashMap> friskeListe = new ArrayList<>();
    SubsekvensRegister registerSyke = new SubsekvensRegister(sykeListe);
    SubsekvensRegister registerFriske = new SubsekvensRegister(friskeListe);

    Monitor2 sykeMonitor = new Monitor2(registerSyke);
    Monitor2 friskeMonitor = new Monitor2(registerFriske);

    Scanner sc = null;
    File mappe = new File(args[0]); // Mappe navn
    File[] listeAvFiler = mappe.listFiles(); // liste av filer i mappen
    for (File f : listeAvFiler) {

      if (f.getName().equals("metadata.csv")) { // if en fil i mappe kalt metadata
        // System.out.println("fant metadata");
        try {
          sc = new Scanner(new File(args[0] + "/" + "metadata.csv"));// aksessering av directory av metadata.csv
          ArrayList<String> filnavnenesyke = new ArrayList<>(); // lager liste av filnavn lest fra metadata
          ArrayList<String> filnavnenefriske = new ArrayList<>();
          ArrayList<String> filenavneneAlle = new ArrayList<>();

          while (sc.hasNextLine()) {

            filenavneneAlle.add(sc.nextLine()); // legge til filnavnene i den listen
            // System.out.println("adding true files");

          }
          // while (sc.hasNextLine() &&
          // sc.nextLine().trim().split(",")[1].equals("False")) {
          // filnavnenefriske.add(sc.nextLine()); // legge til filnavnene i den listen
          // System.out.println("adding false files");
          // }
          // System.out.println(filenavneneAlle);
          for (String streng : filenavneneAlle) {
            if (streng.split(",")[1].equals("True")) {
              filnavnenesyke.add(streng.split(",")[0]);

            } else {
              filnavnenefriske.add(streng.split(",")[0]);
            }

          }
          for (String s : filnavnenesyke) {
            Thread t = new Thread(new LesTrad(sykeMonitor, args[0] + "/" + s));
            t.start();
            try {
              t.join();
            } catch (InterruptedException e) {
              System.out.println(e);
            }

          }
          for (String s : filnavnenefriske) {
            Thread t = new Thread(new LesTrad(friskeMonitor, args[0] + "/" + s));
            t.start();
            try {
              t.join();
            } catch (InterruptedException e) {
              System.out.println(e);
            }

          }
          for (int i = 0; i < 8; i++) {
            Thread t1 = new Thread(new Fletttrad(sykeMonitor));
            t1.start();
            try {
              t1.join();
            } catch (InterruptedException e) {
              System.out.println(e);
            }
          }
          for (int i = 0; i < 8; i++) {
            Thread t1 = new Thread(new Fletttrad(friskeMonitor));
            t1.start();
            try {
              t1.join();
            } catch (InterruptedException e) {
              System.out.println(e);
            }
          }

        } catch (FileNotFoundException e) {
          System.out.println("fant ikke filen");
        }

      }

    }

    int differanse = 0;
    Subsekvens maxInsidens = null;

    for (Subsekvens s2 : sykeMonitor.taUt(0).values()) { // looping gjennom verdiene i den store
      for (Subsekvens s1 : friskeMonitor.taUt(0).values()) {

        if (s2.subsekvens.equals(s1.subsekvens)
            && ((s2.hentAntallForekomster() - s1.hentAntallForekomster()) > differanse)) {

          differanse = s2.hentAntallForekomster() - s1.hentAntallForekomster();

          maxInsidens = s2;
          if (differanse >= 7) {

            System.out.println(maxInsidens + " " + differanse);
          }

        }

      }

    }
    System.out.println("************");
    for (Subsekvens s2 : sykeMonitor.taUt(0).values()) {
      if (sykeMonitor.taUt(0).containsKey(s2.subsekvens) && !friskeMonitor.taUt(0).containsKey(s2.subsekvens)) {
        if (s2.hentAntallForekomster() > differanse) {
          differanse = s2.hentAntallForekomster();

          maxInsidens = s2;

          System.out.println(s2);

        }
      }
    }
    System.out.println("***********");

    System.out.println("den som kommer hyppigere hos syke er :" + maxInsidens.toString() + "med antall :" + differanse);
  }

}

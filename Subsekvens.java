class Subsekvens {
  public final String subsekvens;
  private int antallForekomster; // forekomst av subsekvens hos flere personer

  public Subsekvens(String subsekvens, int antallForekomster) {

    this.antallForekomster = antallForekomster;
    this.subsekvens = subsekvens;
  }

  public int hentAntallForekomster() {
    return antallForekomster;
  }

  public void EndreAntallForekomster(int endring) {
    antallForekomster += endring;

  }

  public String toString() {
    return ("(" + subsekvens + "," + antallForekomster + ")");
  }
}

package paket;

import java.util.HashMap;

public class Slika implements Comparable<Slika>{
	int leva = -1;
	int desna = -1;
	int id;
	boolean horizontalna;
	public HashMap<String, String> tagovi = new HashMap<>();
	public String tagoviA[] = new String[100];
	public int brojTagova = 0;
	
	public void dodajTag(String string) {
		tagovi.put(string, string);
		tagoviA[brojTagova++] = string;
	}
	
	public static Slika napraviHorizontalnu(int i,int j) {
		Slika slika = new Slika();
		slika.horizontalna = true;
		slika.leva = i;
		slika.desna = j;
		Slika a = Main.getRadi().getSlikaByID(i);
		Slika b = Main.getRadi().getSlikaByID(j);
		for (int ii = 0 ; ii < a.brojTagova ; ii++) {
			slika.dodajTag(a.tagoviA[ii]);
		}
		
		for (int ii = 0 ; ii < b.brojTagova ; ii++) {
			if (!(slika.tagovi.containsKey(b.tagoviA[ii]))) {
				slika.dodajTag(b.tagoviA[ii]);
			}
		}
		return slika;
	}
	
	public int broj(Slika slika) {
		int br = 0;
		for (int i = 0 ; i < brojTagova ; i++) {
			if (!(slika.tagovi.containsKey(tagoviA[i]))) {
				br++;
			}
		}
		return br;
	}
	
	public int minBroj(Slika slika) {
		int samoThis = 0;
		int oba = 0;
		int samoSlika = 0;
		for (int i = 0 ; i < this.brojTagova ; i++) {
			if (slika.tagovi.containsKey(tagoviA[i])) {
				oba++;
			}else {
				samoThis++;
			}
		}
		for (int i = 0 ; i < slika.brojTagova ; i++) {
			if (this.tagovi.containsKey(slika.tagoviA[i])) {
			}else {
				samoSlika++;
			}
		}
		return Math.min(oba, Math.min(samoThis,samoSlika));
	}
	@Override
	public int compareTo(Slika arg0) {
		return arg0.brojTagova - this.brojTagova;
	}
}
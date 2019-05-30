package paket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Radi {
	Slika[] slike;
	Slika[] samoHoriz;
	String najbolji;
	int najbo = 0;
	int br;
	int brojKojiJeOK;
	
	public void radi1() {
		int ind = brojKojiJeOK;
		int tmp = brojKojiJeOK;
		int prvaID = 0;
		int result = 0;
		Slika prva = samoHoriz[0];
		String s = "";
		Arrays.sort(samoHoriz);
		for (int i = 0 ; i < brojKojiJeOK ; i++) {
			if (prva == samoHoriz[i]) {
				prvaID = i;
				break;
			}
		}
		s += brojKojiJeOK + "\n";
		File file = new File("poz3.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(s);
            while(prva != null && tmp >= 0) {
            	if(tmp%1000 == 0)System.out.println(tmp);
            	int max = 0;
    			int id = -1;
    			int br;
            	for (int j = 0  ; j < ind ; j++) {
    				if (prva != samoHoriz[j] && samoHoriz[j] != null) {
    					br = prva.minBroj(samoHoriz[j]);
    					if (br >= max) {
    						max = br;
    						id = j;
    					}
    				}
    			}
            	result += max;
            	if(id != -1) {
    				if (prva.desna != -1) {
    					s = prva.desna + " " + prva.leva + "\n";
    				}else {
    					s = prva.id + "\n";
    				}
    				fr.write(s);

    				samoHoriz[prvaID] = null;
    				prvaID = id;
    				prva = samoHoriz[id];
    			}else {
    				System.out.println(prva.id + "holly shit");
    			}
            	tmp--;
            }
            if (prva.desna != -1) {
				s = prva.desna + " " + prva.leva + "\n";
			}else {
				s = prva.id + "\n";
			}
			fr.write(s);
            System.out.println(result);
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void napraviHoriz() {
		Slika[] samoVert;
		int ind = 0;
		int ind1 = 0;
		int cntZaHor = 0;
		int cntZaVer = 0;
		for(int i = 0 ; i < br ; i++) {
			if (slike[i].horizontalna) cntZaHor++;
			else cntZaVer++;
		}
		int brojUkupno;
		brojUkupno = cntZaHor + cntZaVer/2;
		brojKojiJeOK = brojUkupno;
		samoHoriz = new Slika[brojUkupno];
		samoVert = new Slika[cntZaVer];
		for(int i = 0 ; i < br ; i++) {
			if (slike[i].horizontalna) samoHoriz[ind++] = slike[i];
			else samoVert[ind1++] = slike[i];
		}
		for (int i = 0 ; i < ind1 ; i++) {
			if (i%1000 == 0)System.out.println(i);
			int max = 0;
			int id = -1;
			int br;
			for (int j = 0  ; j < ind1 ; j++) {
				if (i != j && samoVert[i] != null && samoVert[j] != null) {
					br = samoVert[i].broj(samoVert[j]);
					if (br >= max) {
						max = br;
						id = j;
					}
				}
			}
			if(id != -1) {
				samoHoriz[ind++] = Slika.napraviHorizontalnu(samoVert[i].id, samoVert[id].id);
				samoVert[id] = null;
				samoVert[i] = null;
			}
			
		}
		radi1();
	}
	
	public void ucitaj() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		try {
			Scanner scanner = new Scanner(file);
			int i = 0;
			int broj = Integer.parseInt(scanner.nextLine());
			br = broj;
			slike = new Slika[broj+1];
			System.out.println(broj);
			
			while(scanner.hasNextLine()) {
				slike[i++] = new Slika();
				slike[i-1].id = i-1;
				String s = scanner.nextLine();
				String[] split = s.split(" ");
				if (split[0].equals("H")) {
					slike[i-1].horizontalna = true;
				}else slike[i-1].horizontalna = false;
				int br = Integer.parseInt(split[1]);
				for (int j = 0 ; j < br ; j++) {
					slike[i-1].dodajTag(split[j+2]);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		napraviHoriz();
	}
	
	public Slika getSlikaByID(int i) {
		return slike[i];
	}
}

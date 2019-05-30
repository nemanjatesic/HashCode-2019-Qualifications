package paket;

public class Main {
	public static Radi radi;
	
	public static void main(String[] args) {
		radi = new Radi();
		radi.ucitaj();
	}
	
	public static Radi getRadi() {
		return radi;
	}
}

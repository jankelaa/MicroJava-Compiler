package rs.ac.bg.etf.pp1;

public class Labela {

	private String ime;
	private int dest = 0;
	private int src = 0;

	public Labela(String ime, int dest, int src) {
		super();
		this.ime = ime;
		this.dest = dest;
		this.src = src;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.dest = src;
	}
}

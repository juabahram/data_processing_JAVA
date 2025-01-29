package fp.capsula;

import static fp.capsula.Status.active;
import static fp.capsula.Status.destroyed;
import static fp.capsula.Status.retired;
import static fp.capsula.Status.unknown;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.utiles.Checkers;

public class Capsula implements Comparable<Capsula> {

	//Atributos
	
	private String Id;
	private String Serial;
	private Status status;
	private Boolean Reuse;
	private Integer Water;
	private Integer Land;
	private LocalDate Launch;
	
	//Métodos
	

	//Constructores
	
	public Capsula(String Id, String Serial, Status status, Boolean reuse, Integer Water, Integer Land, LocalDate Launch) {
		
		this.Id = Id;
		this.Serial = Serial;
		this.status = status;
		this.Reuse = reuse;
		this.Water = Water;
		this.Land = Land;
		this.Launch = Launch;
		
		Checkers.check("Serial incorrecto", isSerialOK(Serial));
		Checkers.check("Fecha de lanzamiento corrupta",2006<getLaunch().getYear() || 2022>getLaunch().getYear());
	}
	

	
	public Capsula(String s) {
		
		String[] trozos = s.split(";");
		this.Id = trozos[0].trim();
		this.Serial = trozos[1].trim();
		this.status = Status.valueOf(trozos[2].trim());
		this.Reuse = Boolean.valueOf(trozos[3].trim());
		this.Water = Integer.valueOf(trozos[4].trim());
		this.Land = Integer.valueOf(trozos[5].trim());
		this.Launch = LocalDate.parse(trozos[6].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		Checkers.check("Status fuera de parámetros", contieneEstado(this.status));
	}

	//Getters 
	
	public String getId() {		
		return Id;
	}
	
	public String getSerial() {
		return Serial;
	}

	public Status getStatus() {
		return status;
	}

	public Boolean getReuse() {
		return Reuse;
	}

	public Integer getWater() {
		return Water;
	}

	public Integer getLand() {
		return Land;
	}

	public LocalDate getLaunch() {
		return Launch;
	}
	
	//

	public String toString() {
		return "Capsula[Id=" + Id + ", Serial=" + Serial + ", Status=" + status + ", Reuse=" + Reuse + ", Water="
				+ Water + ", Land=" + Land + ", Launch=" + Launch + "]";
	}
	
	public boolean isSerialOK(String Serial) {
	
		String Seriale= this.Serial;
		Boolean res = false;
		Boolean res2 = true;
		if (Seriale.contains("C")) {
			res = true;
		}
		

		for(int i = 1; i < Serial.length(); i++) {
			if (!Character.isDigit(Seriale.charAt(i))) {
				res2 = false;
			}
		
		}
		return res&&res2;
		
	}

	public boolean contieneEstado(Status status) {
		Boolean res=false;
		if (this.status == retired) {
			res= true;
		}
		else if (this.status == unknown) {
			res=true;
		}
		else if (this.status == active) {
			res=true;
		}
		else if (this.status == destroyed) {
			res=true;
		}
		return res;
	}
	
	public Integer aterrizajesTotales() {
		return Land+Water;
	}
	
	//Criterio de igualdad
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((Serial == null) ? 0 : Serial.hashCode());
		result = prime * result + ((Launch == null) ? 0 : Launch.hashCode());
		result = prime * result + ((Land == null) ? 0 : Land.hashCode());
		result = prime * result + ((Water == null) ? 0 : Water.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((Reuse == null) ? 0 : Reuse.hashCode());
		
		return result;
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if(o instanceof Capsula) {
			Capsula c = (Capsula) o;
			res = getId().equals(c.getId());
			res= getSerial().equals(c.getSerial());
			res= getLaunch().equals(c.getLaunch());
			res= getLand().equals(c.getLand());
			res= getWater().equals(c.getWater());
			res= getStatus().equals(c.getStatus());
			res= getReuse().equals(c.getReuse());
		}
		return res;
	}
	
	//Orden natural
	
	public int compareTo(Capsula c) {
		int res = this.getSerial().compareTo(c.getSerial()) + this.getId().compareTo(c.getId()) + this.getLaunch().compareTo(c.getLaunch())+this.getLand().compareTo(c.getLand()) + this.getWater().compareTo(c.getWater());
		
		return res;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

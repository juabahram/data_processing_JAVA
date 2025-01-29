package fp.capsula;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Archivo {
	
	//Atributos
	
	private Set<Capsula> capsulas;
	
	//Métodos
	
	//Constructores
	
	public Archivo() {
		super();
		capsulas = new HashSet<Capsula>();
	}
	
	public Archivo(Set<Capsula> capsulas) {
		super();
		this.capsulas = capsulas;
	}
	
	//
	
	public void añadirCapsula(Capsula c) {
		capsulas.add(c);
	}
	
	public String toString() {
		return "Archivo [capsulas=" + capsulas + "]";
	}
	
	//tratamientos secuenciales
	
	public Long CalcularStatus(Status status) {
		long res = capsulas.stream().filter(x -> x.getStatus().equals(status)).count();
		return res;
	}
	
	public Boolean Reciclar() {
		return capsulas.stream().allMatch(x -> x.getReuse().equals(true));
	}
	
	public Boolean CalculaIdComun() {
		Predicate<Capsula> pc = x -> x.getId().indexOf("5") == 0;
		return capsulas.stream().allMatch(pc);
	}
	
	public Double CalculaMediaLandings() {
		Supplier<Stream<Capsula>> aux = () -> capsulas.stream();
		Integer num = (int) aux.get().count();
		Double suma = aux.get().mapToDouble(x -> x.getLand() + x.getWater()).sum();
		return suma/num;
	}
	
	public Map<String, Capsula> SalvarPlaneta(){
		Supplier<Stream<Capsula>> g = () -> capsulas.stream().filter(x -> x.getReuse().equals(true));
		Supplier<Stream<Capsula>> g1 = () -> g.get().filter(x -> x.aterrizajesTotales()>=4);
		Map<String, Capsula> map = g1.get().collect(Collectors.toMap(Capsula::getSerial , x -> x));
		return map;
	}
	
	//
	
	public Stream<Capsula> ordenFecha() {
		Function<Capsula, LocalDate> funcionlaunch = x -> x.getLaunch();
		Stream<Capsula> capsulas2 = capsulas.stream().sorted(Comparator.comparing(funcionlaunch));
		capsulas2.forEach(System.out::println);
		return capsulas2;		
	}
	
	public Integer minimotierra(LocalDate Launch) {
		return capsulas.stream().filter(x -> x.getLaunch().isBefore(Launch)).mapToInt(x -> x.getLand()).min().getAsInt();
	}
	
	public Map<Status, Set<Capsula>> AgruparStatus(){
		return capsulas.stream().collect(Collectors.groupingBy(x -> x.getStatus(), Collectors.toSet()));
	}
	
	public Optional<Capsula> calcularCapsulaMayorAterrizajes(){
		return capsulas.stream().collect(Collectors.maxBy(Comparator.comparing(Capsula::aterrizajesTotales)));
	}
	
	public Map<String, Set<Capsula>> lost(){
		return capsulas.stream().filter(x -> x.getStatus().equals(Status.unknown)).collect(Collectors.groupingBy(x -> x.getId(), Collectors.toSet()));
	}
	
	public Map<String, Set<Capsula>> waterfriendly(){
		return capsulas.stream().filter(x -> x.getWater() >= 2).collect(Collectors.groupingBy(x -> x.getSerial(), Collectors.toSet()));
	}
	
	
	
	
	
	
	
}

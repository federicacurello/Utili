
public class SimulazioneMigranti {

	//SIMULATORE
	public class Simulator {
		
		// Tipi di eventi / coda degli eventi
		PriorityQueue<Event> queue ;
		
		// Modello del mondo
		private Map<Country,Integer> stanziali ;
		private Graph graph ;
		
		// Parametri di simulazione
		private int N_MIGRANTI = 1000 ;
		
		// Valori in output
		private int T ; // passi di simulazione
		
		
		public void init(Graph<Country, DefaultEdge> graph, Country partenza) {
			
			this.T = 1 ;
			
			this.stanziali = new HashMap<Country, Integer>() ;
			for(Country c: graph.vertexSet())
				this.stanziali.put(c, 0) ;
			
			this.queue = new PriorityQueue<Event>() ;
			
			this.queue.add(new Event(T, N_MIGRANTI, partenza)) ;
			this.graph = graph ;
		}
		
		public void run() {
			
			Event e ;
			while( (e=queue.poll())!=null) {
				
				this.T = e.getT() ;
				
				int arrivi = e.getNum() ;
				Country stato = e.getDestination() ;
				
				List<Country> confinanti = Graphs.neighborListOf(graph, stato) ;
				
				int migranti = (arrivi / 2) / confinanti.size() ;
				
				if(migranti!=0) {
					for(Country arrivo: confinanti) {
						queue.add(new Event(e.getT()+1, migranti, arrivo)) ;
					}
				}
				
				int rimasti = arrivi - migranti * confinanti.size() ;
				
				this.stanziali.put(stato, this.stanziali.get(stato)+rimasti) ;	
			}
		}

		public Map<Country, Integer> getStanziali() {
			return stanziali;
		}

		public int getT() {
			return T;
		}
		
		//EVENTO
		
		public class Event implements Comparable<Event> {
			
			private int t ;
			
			private int num ; // quante persone si spostano
			private Country destination ; // nazione di destinazione
			
			

			public Event(int t, int num, Country destination) {
				super();
				this.t = t;
				this.num = num;
				this.destination = destination;
			}



			public int getT() {
				return t;
			}



			public int getNum() {
				return num;
			}



			public Country getDestination() {
				return destination;
			}



			@Override
			public String toString() {
				return "Event [t=" + t + ", num=" + num + ", destination=" + destination + "]";
			}



			@Override
			public int compareTo(Event other) {
				return this.t - other.t;
			}

}


public class Ricorsione {

	//RICORSIONE CON GRAFO ORIENTATO E DEVO SEGUIRE IL CAMMINO DEGLI ARCHI
		//TROVANDO QUELLO DI LUNGHEZZA MASSIMA (UFO SIGHTINGS)
		public List<String> getPercorsoMassimo(String partenza){
			this.ottima = new LinkedList<String>();
			List<String> parziale = new LinkedList<String>();
			parziale.add(partenza);
		
			cercaPercorso(parziale);
			
			return this.ottima;
		}

		private void cercaPercorso(List<String> parziale) {
			
					
			//ottengo tutti i candidati
			List<String> candidati = this.getSuccessori(parziale.get(parziale.size()-1));
			for(String candidato : candidati) {
				if(!parziale.contains(candidato)) {
					//è un candidato che non ho ancora considerato
					parziale.add(candidato);
					this.cercaPercorso(parziale);
					parziale.remove(parziale.size()-1);
				}
			}
			
			
			//vedere se la soluzione corrente è migliore della ottima corrente
			if(parziale.size() > ottima.size()) {
				this.ottima = new LinkedList(parziale);
			}
			
			
		}
		
		//RICORSIONE INSIEME MINIMO DI CORSI CHE RACCOLGA TUTTI GLI STUDENTI(GESTIONALE)

		public List<Corso> getPercorsoMinimo(){
			this.ottima = new ArrayList<Corso>(corsi);
			List<Corso> parziale = new ArrayList<Corso>();
			
		
			cercaPercorso(parziale);
			
			return this.ottima;
		}

		private void cercaPercorso(List<Corso> parziale) {
			
			//vedere se la soluzione corrente è migliore della ottima corrente
			
			HashSet<Studente> tutti= new HashSet<Studente>(studenti);
			
			for(Corso corso: parziale) {
				tutti.removeAll(corso.getStudenti());
			}
			
			
			if(tutti.isEmpty()) {
				if(parziale.size() < ottima.size()) {
				this.ottima.clear();
				this.ottima.addAll(parziale);
			}}
			
			//ottengo tutti i candidati
			
			for(Corso candidato : corsi) {
				if(parziale.isEmpty() || candidato.compareTo(parziale.get(parziale.size()-1))>0) { //SENZA QUESTA CONDIZIONE 
					parziale.add(candidato);													   //SI IMPALLLAVA! È IMPORTANTE!
					this.cercaPercorso(parziale);
					parziale.remove(candidato);
				}
			}
			
			
			
			
		}
		//CAMMINO DI PESO MASSIMO AVENTE LUNGHEZZA FISSATA(ARTSMIA)
		public List<ArtObject> getPercorsoPesoMassimo(int LUN, ArtObject partenza){
			this.ottima = new LinkedList<ArtObject>();
			List<ArtObject> parziale = new LinkedList<ArtObject>();
			parziale.add(partenza);
		
			cercaPercorso(0,LUN, parziale, partenza);
			Collections.sort(ottima);
			return this.ottima;
		}

		private void cercaPercorso(int step, int LUN, List<ArtObject> parziale, ArtObject partenza ) {
			
			//vedere se la soluzione corrente è migliore della ottima corrente
			if(step+1==LUN) {
				if(pesoMax(parziale)>pesoMax(ottima)) {
					this.ottima = new LinkedList<ArtObject>(parziale);
				}
			}
					
			//ottengo tutti i candidati
			List<ArtObject> candidati = new LinkedList<ArtObject>(this.trovaCandidati(partenza, parziale));
			
			for(ArtObject candidato : candidati) {
				if(!parziale.contains(candidato)) {
					//è un candidato che non ho ancora considerato
					parziale.add(candidato);
					this.cercaPercorso(step+1, LUN, parziale, partenza);
					parziale.remove(parziale.size()-1);
				}
			}
			
		}

		private LinkedList<ArtObject> trovaCandidati(ArtObject partenza, List<ArtObject> parziale) {
			LinkedList<ArtObject> list= new LinkedList<ArtObject>();
			for(ArtObject a: Graphs.successorListOf(graph, parziale.get(parziale.size()-1))) {
				if(a.getClassification().equals(partenza.getClassification())) {
					list.add(a);
				}
			}
			return list;
		}

		private double pesoMax(List<ArtObject> list) {
			double peso=0;
			for(ArtObject a:list) {
				if(list.indexOf(a) != (list.size()-1) ) {
				peso+=graph.getEdgeWeight(graph.getEdge(a, list.get(list.indexOf(a)+1)));
				}
			}
				
			return peso;
		}
		
		//INSIEME DI K ELEMENTI CON MINORI SCONFITTE(FORMULA1)

		public List<Driver> getDremTeam(int k) {
			bestDreamTeam = new ArrayList();
			bestDreamTeamValue = Integer.MAX_VALUE;
			recursive(0, new ArrayList<Driver>(), k);
			return bestDreamTeam;
		}

		private int evaluate(ArrayList<Driver> tempDreamTeam) {
			int sum = 0;
			
			Set<Driver> in = new HashSet<Driver>(tempDreamTeam);
			Set<Driver> out = new HashSet<Driver>(grafo.vertexSet());
			out.removeAll(in);
			
			for (DefaultWeightedEdge e : grafo.edgeSet()) {
				if (out.contains(grafo.getEdgeSource(e)) && in.contains(grafo.getEdgeTarget(e))) {
					sum += grafo.getEdgeWeight(e);
				}
			}
			return sum;
		}
		
		private void recursive(int step, ArrayList<Driver> tempDreamTeam, int k) {
			
			// condizione di terminazione
			if (step >= k) {
				if (evaluate(tempDreamTeam) < bestDreamTeamValue) {
					bestDreamTeamValue = evaluate(tempDreamTeam);
					bestDreamTeam = new ArrayList<>(tempDreamTeam);
					return;
				}
			}
			
			for (Driver d : grafo.vertexSet()) {
				if (!tempDreamTeam.contains(d)) {
					tempDreamTeam.add(d);
					recursive(step+1, tempDreamTeam, k);
					tempDreamTeam.remove(d);
				}
			}
			
		}
		
		//CAMMINO + LUNGO CON MASSIMA DIFFERENZA DI PESI(SERIE A)
		/*
		 * RICORSIONE
		 * 
		 * Soluzione parziale: Lista di Season (lista di vertici) Livello ricorsione:
		 * lunghezza della lista Casi terminali: non trova altri vertici da aggiungere
		 * -> verifica se il cammino ha lunghezza massima tra quelli visti finora
		 * Generazione delle soluzioni: vertici connessi all'ultimo vertice del percorso
		 * (con arco orientato nel verso giusto), non ancora parte del percorso,
		 * relativi a stagioni consecutive.
		 */

		private void cerca(int livello, List<Season> parziale) {
			boolean trovato = false;

			// genera nuove soluzioni
			Season ultimo = parziale.get(livello - 1);

			for (Season prossimo : Graphs.successorListOf(grafo, ultimo)) {
				if (!parziale.contains(prossimo)) {
					if (stagioniConsecutive.indexOf(ultimo) + 1 == stagioniConsecutive.lastIndexOf(prossimo)) {
						// candidato accettabile -> fai ricorsione
						trovato = true;
						parziale.add(prossimo);
						cerca(livello + 1, parziale);
						parziale.remove(livello);
					}
				}
			}

			// valuta caso terminale
			if (!trovato) {
				if (parziale.size() > percorsoBest.size()) {
					percorsoBest = new ArrayList<Season>(parziale); // clona il best
				}
			}
		}

		
		
		
		
}

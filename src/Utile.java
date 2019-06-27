import java.time.LocalDateTime;

public class Utile {
	//CONTROLLO INPUT PER I BOX
		Airport a1 = cmbBoxAeroportoPartenza.getValue();
		Airport a2 = cmbBoxAeroportoArrivo.getValue();
		
		if(a1 == null || a2 == null) {
			txtResult.setText("Devi selezionare un aeroporto di partenza e uno di arrivo");
			return;
		}
		
		//CONTROLLO INPUT PER I CAMPI DI TESTO(NUMERI INTERI)
		txtResult.clear();
		
		try {
			N = Integer.parseInt(txtN.getText());
		} catch(NumberFormatException e) {
			txtResult.clear();
			txtResult.appendText("Devi inserire un numero N di agenti");
			return;
		}
		
		
		//CONTROLLO INPUT PER UNA DATA
		 try
	     {
	         LocalDate.of(anno, mese, giorno);
	     }
	     catch(DateTimeException e)
	     {
	     	txtResult.clear();
	 		txtResult.appendText("Data non corretta");
	         return;
	     }
		 
		 //QUERY CON DATEDIFF
		 WHERE day(DATEDIFF(s1.DATETIME, s2.DATETIME))<?
		 
		//DISTANZA TRA DUE PUNTI DI COORDINATE LAT E LON
	public double calcolaDistanza(double latMediaV1, double lonMediaV1 ,
			double latMediaV2, double lonMediaV2) {
		Double distanzaMedia = LatLngTool.distance(
				new LatLng(latMediaV1,lonMediaV1), 
				new LatLng(latMediaV2,lonMediaV2),
				LengthUnit.KILOMETER);
		return distanzaMedia;
	}
		 
		 
	//ORDINE DECRESCENTE
	ordinati.sort((a1,a2)->{
		if(a1.getPeso()<a2.getPeso())
			return 1;
		else
			return -1;
		
	});
	
	//DATA POSTERIORE
	public boolean isAfter(LocalDateTime data, LocalDateTime dataRif) {
		return data.isAfter(dataRif);
		
	}
	
	
	//COMPONENTE CONNESSA
	public Set<ArtObject> componenteConnessa(ArtObject input) {
		ConnectivityInspector<ArtObject, DefaultWeightedEdge> c = new ConnectivityInspector<ArtObject, DefaultWeightedEdge>(graph);
		return c.connectedSetOf(input);
	}
	public int numeroConnessi(ArtObject input) {
		return componenteConnessa(input).size();
	}
	
	
	//ITERATOR E TRAVERSAL LISTENER
	public Boolean testConnessione(Integer a1, Integer a2) {
		Set<Airport> visitati = new HashSet<Airport>();
		Airport partenza = aIdMap.get(a1);
		Airport destinazione = aIdMap.get(a2);
		System.out.println("Testo connessione tra " + partenza + " e " + destinazione);
		BreadthFirstIterator<Airport, DefaultWeightedEdge> it = new BreadthFirstIterator<>(this.grafo,partenza);
		
		while (it.hasNext())
			visitati.add(it.next());
		
		if(visitati.contains(destinazione))
			return true;
		else
			return false;
		
	}
	
	public List<Airport> trovaPercorso(Integer a1, Integer a2){
		List<Airport> percorso = new ArrayList<Airport>();
		Airport partenza = aIdMap.get(a1);
		Airport destinazione = aIdMap.get(a2);
		System.out.println("Cerco percorso tra " + partenza + " e " + destinazione);
		BreadthFirstIterator<Airport, DefaultWeightedEdge> it = new BreadthFirstIterator<>(this.grafo,partenza);
		
		visita.put(partenza, null);
		
		it.addTraversalListener(new TraversalListener<Airport,DefaultWeightedEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultWeightedEdge> ev) {
				Airport sorgente = grafo.getEdgeSource(ev.getEdge());
				Airport destinazione = grafo.getEdgeTarget(ev.getEdge());
				
				if(!visita.containsKey(destinazione) && visita.containsKey(sorgente)) {
					visita.put(destinazione, sorgente);
				} else if(!visita.containsKey(sorgente) && visita.containsKey(destinazione)){
					visita.put(sorgente, destinazione);
				}

			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Airport> arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Airport> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		while(it.hasNext())
			it.next();
		
		if(!visita.containsKey(partenza) || !visita.containsKey(destinazione)) {
			return null;
		}
		
		Airport step = destinazione;
		while(!step.equals(partenza)) {
			percorso.add(step);
			step = visita.get(step);
		}
		percorso.add(step);
		
		return percorso;
		
	}
	
	//RAGGIUNGIBILI
	public List<String> getRaggiungibili(String stato){
		List<String> raggiungibili = new LinkedList<>();
		DepthFirstIterator<String,DefaultEdge> dp = 
				new DepthFirstIterator<String,DefaultEdge>(this.grafo,stato);
		
		dp.next();
		
		while (dp.hasNext()) {
			raggiungibili.add(dp.next());
		}
		
		return raggiungibili;
	}
	
	
	
	
	
	
	
}

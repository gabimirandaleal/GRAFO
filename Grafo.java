package grafo;

import java.util.ArrayList;
import Heap.*;

public class Grafo {

	ArrayList<Vertice> v = new ArrayList();
	ArrayList<Vertice> ordemTopologica = new ArrayList();
	int valorV;
	int matrizDijkstra[][];
	ArrayList<Vertice> n = new ArrayList();
	ArrayList<Vertice> simulaHeap = new ArrayList();

	public void ligaVertices(Vertice v1, Vertice v2, int peso) {
		Aresta a1 = new Aresta(v2, peso);
		v1.arestas.add(a1);
		v1.nVizinhos++;
	}

	public void mostrarGrafo(Vertice v1) {

		if (v1.isVisitado == false) {
			v1.isVisitado = true;
			valorV++;
			v1.tempoVisita = valorV;
			for (int j = 0; j < v1.arestas.size(); j++) {
				mostrarGrafo(v1.arestas.get(j).vizinho);
			}
			valorV++;
			v1.tempoPreto = valorV;
			ordemTopologica.add(0, v1);
		}

	}

	public void inicializaDijkstra() {
		for (int i = 0; i < matrizDijkstra.length; i++) {
			matrizDijkstra[i][0] = 0;
			matrizDijkstra[i][1] = -1;
		}
	}

	public void dijkstra(Vertice inicial) {
		inicializaDijkstra();
		inicial.dist = 0;
		int menorDist = 2147483647;
		int cont = 0;
		v.get(inicial.id).dist = 0;
		while (cont < v.size()) {
			Vertice aux = null;
			for (int i = 0; i < v.size(); i++) {
				if (!n.contains(v.get(i))) {
					if (v.get(i).dist < menorDist) {
						aux = v.get(i);
						menorDist = v.get(i).dist;
					}
				}else {
					continue;
				}
			}

			n.add(aux);
			cont++;
			System.out.println(aux.id);
			for (int i = 0; i < aux.arestas.size(); i++) {
				if (aux.arestas.get(i).vizinho.dist == 2147483647) {
					//System.out.println(aux.arestas.get(i).vizinho.id);
					aux.arestas.get(i).vizinho.dist = aux.dist + aux.arestas.get(i).peso;
					aux.arestas.get(i).vizinho.pai = aux.id;
					matrizDijkstra[aux.arestas.get(i).vizinho.id][0] = aux.arestas.get(i).peso + aux.dist;
					matrizDijkstra[aux.arestas.get(i).vizinho.id][1] = aux.id;
					v.get(aux.arestas.get(i).vizinho.id).dist = aux.arestas.get(i).peso + aux.dist;
				} else {
					if (aux.arestas.get(i).vizinho.dist > (aux.dist + aux.arestas.get(i).peso)) {
						//System.out.println(aux.arestas.get(i).vizinho.id);
						aux.arestas.get(i).vizinho.dist = aux.dist + aux.arestas.get(i).peso;
						aux.arestas.get(i).vizinho.pai = aux.id;
						matrizDijkstra[aux.arestas.get(i).vizinho.id][0] = aux.arestas.get(i).peso + aux.dist;
						matrizDijkstra[aux.arestas.get(i).vizinho.id][1] = aux.id;
						v.get(aux.arestas.get(i).vizinho.id).dist = aux.arestas.get(i).peso + aux.dist;
					}
				}
				
			}
			menorDist = 2147483647;

		}
	}

	public static void main(String[] args) {

		Vertice B = new Vertice(0);
		Vertice C = new Vertice(1);
		Vertice D = new Vertice(2);
		Vertice F = new Vertice(3);
		Vertice L = new Vertice(4);
		Vertice P = new Vertice(5);
		Vertice S = new Vertice(6);
		Vertice Y = new Vertice(7);

		Grafo grafo = new Grafo();

		grafo.v.add(B);
		grafo.v.add(C);
		grafo.v.add(D);
		grafo.v.add(F);
		grafo.v.add(L);
		grafo.v.add(P);
		grafo.v.add(S);
		grafo.v.add(Y);

		grafo.ligaVertices(P, D, 18);
		grafo.ligaVertices(P, L, 4);
		grafo.ligaVertices(P, Y, 7);
		grafo.ligaVertices(D, P, 18);
		grafo.ligaVertices(L, P, 4);
		grafo.ligaVertices(Y, P, 7);

		grafo.ligaVertices(L, B, 5);
		grafo.ligaVertices(L, F, 1);
		grafo.ligaVertices(L, C, 8);
		grafo.ligaVertices(B, L, 5);
		grafo.ligaVertices(F, L, 1);
		grafo.ligaVertices(C, L, 8);

		grafo.ligaVertices(C, D, 4);
		grafo.ligaVertices(D, C, 4);

		grafo.ligaVertices(B, F, 7);
		grafo.ligaVertices(B, S, 1);
		grafo.ligaVertices(B, Y, 8);
		grafo.ligaVertices(F, B, 7);
		grafo.ligaVertices(S, B, 1);
		grafo.ligaVertices(Y, B, 8);

		grafo.ligaVertices(F, S, 7);
		grafo.ligaVertices(S, F, 7);

		grafo.ligaVertices(F, D, 12);
		grafo.ligaVertices(D, F, 12);

		grafo.ligaVertices(Y, S, 4);
		grafo.ligaVertices(S, Y, 4);

		grafo.ligaVertices(D, S, 6);
		grafo.ligaVertices(S, D, 6);

		grafo.matrizDijkstra = new int[grafo.v.size()][2];
		grafo.dijkstra(grafo.v.get(5));

		for (int i = 0; i < grafo.matrizDijkstra.length; i++) {
			System.out.print("Matriz[" + i + "] = " + grafo.matrizDijkstra[i][0] + " ");
			System.out.println("Matriz[" + i + "] = " + grafo.matrizDijkstra[i][1] + " ");
		}

//		grafo.mostrarGrafo(grafo.v.get(1));
//		for (int i = 0; i < grafo.v.size(); i++) {
//			if (!grafo.v.get(i).isVisitado) {
//				grafo.mostrarGrafo(grafo.v.get(i));
//			}
//		}
//		for (int j = 0; j < grafo.v.size(); j++) {
//			System.out.println("Visitei o vertice " + grafo.v.get(j).id + " no tempo " + grafo.v.get(j).tempoVisita
//					+ " ficou preto no tempo " + grafo.v.get(j).tempoPreto);
//		}
//		System.out.println("Ordenação topologica: ");
//		for (int j = 0; j < grafo.ordemTopologica.size(); j++) {
//			System.out.println(grafo.ordemTopologica.get(j).id);
//		}
	}
}

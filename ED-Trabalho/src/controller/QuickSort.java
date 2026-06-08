package controller;

import model.Professor;
import otavioolinto.Lista;
public class QuickSort {

	public QuickSort() {
		super();
	}
	
	public Lista<Professor> ordenar(Lista<Professor> listaP, int inicio, int fim)throws Exception{
		
		if(inicio<fim) {
			
			int pivo = dividir(listaP, inicio, fim);
			ordenar(listaP, inicio, pivo-1);
			ordenar(listaP, pivo+1, fim);
		}
		
		return listaP;
	}
	
	private int dividir(Lista<Professor> listaP, int inicio, int fim)throws Exception {
		
		int pivo = listaP.get(inicio).getPontos();
		int pontEsq = inicio+1;
		int pontDir = fim;
		
		while(pontEsq<=pontDir) {
			
			while(pontEsq<=pontDir && listaP.get(pontEsq).getPontos()<=pivo) {
				pontEsq++;
			}
			
			while(pontEsq<=pontDir && listaP.get(pontDir).getPontos()>pivo) {
				pontDir--;
			}
			
			if(pontEsq<pontDir) {
				
				trocar(listaP, pontEsq, pontDir);
				pontEsq++;
				pontDir--;
			}
		}
		
		trocar(listaP, inicio, pontDir);
		
		return pontDir;
	}
	
	private void trocar(Lista<Professor> listaP, int i, int j)throws Exception {
		
		Professor aux = listaP.get(i);
		
		listaP.add(listaP.get(j), i);
		listaP.remove(i+1);
		
		listaP.add(aux, j);
		listaP.remove(j+1);
		
	}
}

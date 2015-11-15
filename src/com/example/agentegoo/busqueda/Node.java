package com.example.agentegoo.busqueda;

public class Node {
	private Evento elem;
	Node leftChild;
	Node rightChild;

	Node(Evento elem, Node left, Node right) {
		this.elem = elem;
		leftChild = left;
		rightChild = right;
	}

	public Evento getElem() {
		return elem;
	}

	public void setElem(Evento elem) {
		this.elem = elem;
	}
}

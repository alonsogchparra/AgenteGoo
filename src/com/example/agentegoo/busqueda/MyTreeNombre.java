package com.example.agentegoo.busqueda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class MyTreeNombre {
	private Node root;

	/**
	 * Construct the tree.
	 */
	public MyTreeNombre() {
		root = null;
	}

	public MyTreeNombre(Evento elem) {
		root = new Node(elem, null, null);
	}

	/**
	 * Insert into the tree.
	 * 
	 * @param elem
	 *            is the item to insert.
	 * @return
	 * @throws IllegalArgumentException
	 *             if elem is already present.
	 */
	public Node add(Evento elem) {
		if (root == null) {
			root = new Node(elem, null, null);
			return root;
		} else {
			Node node = new Node(elem, null, null);
			return (insertNode(root, node));
		}
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param subTree
	 *            is the subtree.
	 * @param newNode
	 *            is the new node to be inserted.
	 * @return the new subtree.
	 * @throws IllegalArgumentException
	 *             if newNode is already present.
	 */
	private Node insertNode(Node subTree, Node newNode) {

		if (subTree == null) {
			subTree = newNode;
		} else if (subTree.getElem().getNombre()
				.compareTo(newNode.getElem().getNombre()) < 0) {
			subTree.rightChild = insertNode(subTree.rightChild, newNode);
		} else if (subTree.getElem().getNombre()
				.compareTo(newNode.getElem().getNombre()) > 0) {
			subTree.leftChild = insertNode(subTree.leftChild, newNode);
		} else {
			if (subTree.getElem().getFecha()
					.compareTo(newNode.getElem().getFecha()) == 0)
				return null;
			else {
				if (subTree.getElem().getFecha()
						.compareTo(newNode.getElem().getFecha()) < 0) {
					subTree.rightChild = insertNode(subTree.rightChild, newNode);
				} else if (subTree.getElem().getFecha()
						.compareTo(newNode.getElem().getFecha()) > 0) {
					subTree.leftChild = insertNode(subTree.leftChild, newNode);
				}
			}
		}
		return subTree;
	}

	/**
	 * Remove from the tree.
	 * 
	 * @param item
	 *            is the item to remove.
	 * @throws IllegalArgumentException
	 *             if item is not found.
	 */
	public void delete(String item) {
		this.deleteNode(root, item);
	}

	/**
	 * Internal method to delete a node.
	 * 
	 * @param subTree
	 *            is the subtree.
	 * @param elem
	 *            is the item to be deleted.
	 * @return the new subtree.
	 * @throws IllegalArgumentException
	 *             if the item is not present.
	 */
	private Node deleteNode(Node subtree, String elem) {
		// Search for the node first
		if (subtree != null) {
			if ((subtree.getElem().getNombre()).compareTo(elem) < 0) {
				subtree.rightChild = deleteNode(subtree.rightChild, elem);
			} else if ((subtree.getElem().getNombre()).compareTo(elem) > 0) {
				subtree.leftChild = deleteNode(subtree.leftChild, elem);
			} else {
				/*
				 * Found a match. There are 3 possibilities: Node is leaf: Easy,
				 * Just delete the node but this is implicitly handled as part
				 * of node has 1 child (see below) Node has 1 child: Delete the
				 * node and put the child node in its place Node has 2 children:
				 * Find the leftmost child in the right subtree, replace the
				 * node to be deleted with this child. Then delete that child
				 * node.
				 */
				if ((subtree.leftChild != null) && (subtree.rightChild != null)) {
					// Node has 2 children
					// Find the leftmost child of the right subtree and
					// make it the current node, then delete the
					// leftmost child of the right subtree
					Node node = findLeftmostChild(subtree.rightChild);
					subtree.setElem(node.getElem());
					subtree.rightChild = deleteNode(subtree.rightChild, node
							.getElem().getNombre());
				} else if (subtree.leftChild != null) {
					// Node has only 1 child i.e. left child
					subtree = subtree.leftChild;
				} else {
					// Node can either have no children or just have 1 right
					// child
					subtree = subtree.rightChild;
				}
			}

		} else {
			// No match
			throw new IllegalArgumentException("No such element");
		}
		return subtree;
	}

	
	/**
	 * Remove from the tree.
	 * 
	 * @param item
	 *            is the item to remove.
	 * @throws IllegalArgumentException
	 *             if item is not found.
	 */
	public void deletefecha(Calendar item) {
		this.deleteNodeFecha(root, item);
	}

	/**
	 * Internal method to delete a node.
	 * 
	 * @param subTree
	 *            is the subtree.
	 * @param elem
	 *            is the item to be deleted.
	 * @return the new subtree.
	 * @throws IllegalArgumentException
	 *             if the item is not present.
	 */
	private Node deleteNodeFecha(Node subtree, Calendar elem) {
		// Search for the node first
		if (subtree != null) {
			if ((subtree.getElem().getFecha()).compareTo(elem) < 0) {
				subtree.rightChild = deleteNodeFecha(subtree.rightChild, elem);
			} else if ((subtree.getElem().getFecha()).compareTo(elem) > 0) {
				subtree.leftChild = deleteNodeFecha(subtree.leftChild, elem);
			} else {
				/*
				 * Found a match. There are 3 possibilities: Node is leaf: Easy,
				 * Just delete the node but this is implicitly handled as part
				 * of node has 1 child (see below) Node has 1 child: Delete the
				 * node and put the child node in its place Node has 2 children:
				 * Find the leftmost child in the right subtree, replace the
				 * node to be deleted with this child. Then delete that child
				 * node.
				 */
				if ((subtree.leftChild != null) && (subtree.rightChild != null)) {
					// Node has 2 children
					// Find the leftmost child of the right subtree and
					// make it the current node, then delete the
					// leftmost child of the right subtree
					Node node = findLeftmostChild(subtree.rightChild);
					subtree.setElem(node.getElem());
					subtree.rightChild = deleteNodeFecha(subtree.rightChild, node
							.getElem().getFecha());
				} else if (subtree.leftChild != null) {
					// Node has only 1 child i.e. left child
					subtree = subtree.leftChild;
				} else {
					// Node can either have no children or just have 1 right
					// child
					subtree = subtree.rightChild;
				}
			}

		} else {
			// No match
			throw new IllegalArgumentException("No such element");
		}
		return subtree;
	}

	
	
	/**
	 * Internal method to find the leftmost child.
	 * 
	 * @param subtree
	 *            is the subtree.
	 * @return the leftmost child.
	 */
	private Node findLeftmostChild(Node subtree) {
		assert (subtree != null);
		while (subtree.leftChild != null) {
			subtree = subtree.leftChild;
		}
		return subtree;
	}

	/**
	 * PROFUNDIDAD Method to traverse the tree in depth first order.
	 * 
	 * @return the List of elements in the tree in depth first order.
	 */
	public List<Evento> depthFirstTraversal() {
		if (root == null)
			return null;
		List<Evento> l = new ArrayList<Evento>();
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		while (!s.isEmpty()) {
			Node node = s.pop();
			l.add(node.getElem());
			if (node.rightChild != null) {
				s.push(node.rightChild);
			}
			if (node.leftChild != null) {
				s.push(node.leftChild);
			}
		}
		return l;
	}

	/**
	 * AMPLITUD Method to traverse the tree in breadth first order
	 * 
	 * @return the List of elements in the tree in breadth first order.
	 */
	public List<Evento> breadthFirstTraversal() {
		if (root == null)
			return null;
		List<Evento> l = new ArrayList<Evento>();
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while (!q.isEmpty()) {
			Node node = q.poll();
			l.add(node.getElem());
			if (node.leftChild != null) {
				q.add(node.leftChild);
			}
			if (node.rightChild != null) {
				q.add(node.rightChild);
			}
		}
		return l;
	}

	/**
	 * Method to find an item in a subtree.
	 * 
	 * @param item
	 *            is item to search for.
	 * @return node containing the matched item.
	 */
	public Node findNode(String item) {
		if (root == null)
			return null;
		if (item == null)
			return null;
		Node current = root;
		while ((current.getElem().getNombre()).compareTo(item) != 0) {
			System.out.println("Nodo Recorrido antes de encontrar: "
					+ current.getElem().getNombre());
			if ((current.getElem().getNombre()).compareTo(item) > 0) {
				current = current.leftChild;
			} else if ((current.getElem().getNombre()).compareTo(item) < 0) {
				current = current.rightChild;
			}
			if (current == null)
				return null;
		}
		return current;
	}
	
	/**
	 * Elimina del arbol Eventos ya pasados
	 * 
	 * @param fecha
	 *            es la fecha desde la cual todos los eventos que ya pasaron a
	 *            partir de esa fecha son eliminados
	 * @throws IllegalArgumentException
	 *             if item is not found.
	 */
	public void delete_eventos_pasados(Calendar elem) {
		List<Evento> l = new ArrayList<Evento>();
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		while (!s.isEmpty()) {
			Node node = s.pop();
			if(node.getElem().getFecha().before(elem)){
				l.add(node.getElem());
			}
			if (node.rightChild != null) {
				s.push(node.rightChild);
			}
			if (node.leftChild != null) {
				s.push(node.leftChild);
			}
		}
		for (Evento t : l) {
			deletefecha(t.getFecha());
		}
	}

	// Test it
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		MyTreeNombre tree = new MyTreeNombre(new Evento("aaa",
				new GregorianCalendar(2013, 2, 1, 9, 45), 1, true));

		tree.add(new Evento("bbb", new GregorianCalendar(2013, 2, 1, 10, 23),
				1, true));
		tree.add(new Evento("ccc", new GregorianCalendar(2013, 2, 1, 10, 49),
				1, true));
		tree.add(new Evento("ddd", new GregorianCalendar(2013, 2, 1, 11, 30),
				1, true));
		tree.add(new Evento("eee", new GregorianCalendar(2013, 2, 1, 11, 50),
				1, true));
		tree.add(new Evento("fff", new GregorianCalendar(2013, 2, 2, 9, 00),
				1, true));

		List<Evento> l = tree.depthFirstTraversal();
		System.out.println("Eventos Insertados");
		printTree(l);

		//Eliminar Eventos pasados
		System.out.println("\nEventos ya pasados (Arbol despues de eliminar");
		tree.delete_eventos_pasados(new GregorianCalendar(2013, 2, 1, 11, 49));
		//Recorre el arbol otra ves para ver el nuevo arbol luego de eliminar los eventos ya pasados
		l = tree.depthFirstTraversal();
		printTree(l);

		// Buscar Evento por nombre
		// System.out.println("\nEvento encontrado: "+
		// tree.findNode("aaa").getElem().getNombre());

		// Eliminar Eventos 
//		tree.delete("aaa");
//		l = tree.depthFirstTraversal();
//		System.out.println("\nBusqueda en PROFUNDIDAD despues de Eliminar");
//		printTree(l);

	}

	// Method to print tree
	public static <T> void printTree(List<Evento> l) {
		for (Evento t : l) {
			System.out.println("Nombre: " + t.getNombre() + "  ,Fecha: "
					+ t.getFechaString() + " ,Hora: " + t.getHoraString());
		}
	}

}
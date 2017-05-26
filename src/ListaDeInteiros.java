
public class ListaDeInteiros {
	
    private class Node {
        public int element;
        public Node next;

        public Node(int element) {
            this.element = element;
            next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int count;

    public ListaDeInteiros() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(int element) {
        Node aux = new Node(element);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
    
    public void add(int index, int element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node n = new Node(element);
        if (index == 0) {
            n.next = head;
            head = n;
            if (tail == null) {
                tail = n;
            }
        } else if (index == count) {
            tail.next = n;
            tail = n;
        } else {
            Node aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            n.next = aux.next;
            aux.next = n;
        }
        count++;
    }

    public int get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
    }

    public int set(int index, int element) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        int tmp = aux.element;
        aux.element = element;
        return tmp;

    }

    public boolean remove(int element) {
        if (count == 0) {
            return false;
        }

        if (head.element == element) { 
        	head = head.next;
            if (count == 1) {
                tail = null;
            }
            count--;
            return true;
        }

        Node ant = head;
        Node aux = head.next;

        for (int i = 1; i < count; i++) {
            if (aux.element == element) {
                if (aux == tail) { 
                    tail = ant;
                    tail.next = null;
                } else { 
                    ant.next = aux.next;
                }
                count--;
                return true;
            }
            ant = ant.next;
            aux = aux.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public int removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        Node aux = head;
        if (index == 0) {
            if (tail == head)
            {
                tail = null;
            }
            head = head.next;
            count--;
            return aux.element;
        }
        int c = 0;
        while (c < index - 1) {
            aux = aux.next;
            c++;
        }
        int element = aux.next.element;
        if (tail == aux.next) {
            tail = aux;
        }
        aux.next = aux.next.next;
        count--;
        return element;
    }

    public int indexOf(int element) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element == element) {
                return (index);
            }
            aux = aux.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int element) {
        Node aux = head;
        while (aux != null) {
            if (aux.element == element) {
                return (true);
            }
            aux = aux.next;
        }
        return false;
    }

    public void addIncreasingOrder(int element) {
        if (count == 0) {
            add(element);
        }
        else if (element < head.element) {
            add(0,element);
        }
        else if (element > tail.element) {
            add(element);
        } 
        else {
            Node ant = head;
            Node aux = head.next;
            Node n = new Node(element);
            while (aux != null) {
                if (element < aux.element) {
                    n.next = aux;
                    ant.next = n;
                    count++;
                    break;
                }
                ant = aux;
                aux = aux.next;
            }
        }
    }
    
    
    public String toString() {
    	String resp = "";
    	Node aux = head;
    	while(aux != null) {
    		resp = resp + "|" +aux.element + "|";
    		aux = aux.next;
    	}
    	return resp;
    }

}
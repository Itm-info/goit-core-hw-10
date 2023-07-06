package itm.three;

public class WordsList {
    public Node first;
    public Node last;
    class Node {
        String key;
        int value;
        Node next, prev;

        // Create new entry (node) and add it to the end of the WordsList.
        // We will only create new entry for words not yet present in the List
        private Node(String key) {
            this.key = key;
            this.value = 1;
            if (first == null) {
                first = this;
            }
            if (last == null) {
                last = this;
            } else {
                this.prev = last;
                last.next = this;
                last = this;
            }
        }
    }

    public Node get(String key) { // get Node object by key
        Node seek = first;
        while(seek != null) {
            if (seek.key.equals(key)) return seek;
            seek = seek.next;
        }
        return seek;
    }

    // Increment word occurrence count and rearrange respective entry position in the WordsList
    public void inc(String key){
        Node node = get(key);
        if( node == null ) {
            new Node(key);
        }
        else {
            ++node.value;
            Node temp;
            while (node.prev != null && node.value > node.prev.value) {
                temp = node.prev;
                if( temp.prev != null) {
                    temp.prev.next = node;
                }
                node.prev = temp.prev;

                if( node.next != null) {
                    node.next.prev = temp;
                }
                temp.next = node.next;

                node.next = temp;
                temp.prev = node;

                if( first==temp ) first = node;
                if( last == node ) last = temp;
            }
        }
    }

    public void printWordsList(){
        Node seek = first;
        while( seek != null ){
            System.out.println(seek.key + " " + seek.value);
            seek = seek.next;
        }
    }
}

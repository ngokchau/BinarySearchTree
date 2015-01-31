class bst
{
    Node root;

    private class Node
    {
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String k){
        	// TODO Instantialize a new Node with keyword k.
            this.keyword = k;
            this.l = null;
            this.r = null;
        }

        private void update(Record r){
        	//TODO Adds the Record r to the linked list of records. You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list
            Record temp = this.record;
            this.record = r;
            this.record.next = temp;
            this.size++;
        }
    }

    /**
     * Constructor
     */
    public bst()
    {
        this.root = null;
    }

    /**
     * Recursive function that adds a record to a list of records for the node associated
     * with keyword. Create a new node if null;
     * @param keyword the keyword associated to record
     * @param fd the data of a record
     */
    public void insert(String keyword, FileData fd)
    {
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);

        this.root = insert(this.root, keyword, recordToAdd);
    }

    /**
     * Internal method
     * @param current
     * @param keyword
     * @param record
     * @return
     */
    private Node insert(Node current, String keyword, Record record)
    {
        if(null == current)
        {
            Node n = new Node(keyword);
            n.update(record);
            current = n;
        }
        else
        {
            int determinant = current.keyword.compareTo(keyword);

            if(determinant > 0)
            {
                current.l = insert(current.l, keyword, record);
            }
            else if(determinant < 0)
            {
                current.r = insert(current.r, keyword, record);
            }
            else
            {
                current.update(record);
            }
        }

        return current;
    }
    
    public boolean contains(String keyword)
    {
    	//TODO Write a recursive function which returns true if a particular keyword exists in the bst
        return contains(this.root, keyword);
    }

    private boolean contains(Node current, String keyword)
    {
        if(null == current)
        {
            return false;
        }

        int determinant = current.keyword.compareTo(keyword);

        if(determinant > 0)
        {
            return contains(current.l, keyword);
        }
        else if(determinant < 0)
        {
            return contains(current.r, keyword);
        }
        else
        {
            return true;
        }
    }

    /**
     * @param keyword
     * @return the first record associated to the keyword, which is linked to other records. Or
     * return null if keyword does not exist in the binary search tree.
     */
    public Record get_records(String keyword)
    {
    	return get_records(this.root, keyword);
    }

    private Record get_records(Node current, String keyword)
    {
        if(null == current)
        {
            return null;
        }

        int determinant = current.keyword.compareTo(keyword);

        if(determinant > 0)
        {
            return get_records(current.l, keyword);
        }
        else if(determinant < 0)
        {
            return get_records(current.r, keyword);
        }
        else
        {
            return current.record;
        }
    }

    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword from the binary search tree.
    	//You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.
    }

    public void print(){
        print(root);
    }

    private void print(Node t){
        if(t!=null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
    

}

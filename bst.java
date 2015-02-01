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

        /**
         * Constructor that instantialize a new Node with keyword k.
         * @param k
         */
        private Node(String k)
        {
            this.keyword = k;
            this.l = null;
            this.r = null;
        }

        /**
         * Adds Record r to linked list structure by appending to the front of the list.
         * @param r
         */
        private void update(Record r)
        {
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

    private Node insert(Node subtree, String keyword, Record record)
    {
        if(null == subtree)
        {
            Node n = new Node(keyword);
            n.update(record);
            subtree = n;
        }
        else
        {
            int determinant = subtree.keyword.compareTo(keyword);

            if(determinant > 0)
            {
                subtree.l = insert(subtree.l, keyword, record);
            }
            else if(determinant < 0)
            {
                subtree.r = insert(subtree.r, keyword, record);
            }
            else
            {
                subtree.update(record);
            }
        }

        return subtree;
    }

    /**
     * @param keyword
     * @return
     */
    public boolean contains(String keyword)
    {
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

    public void delete(String keyword)
    {
        this.root = delete(this.root, keyword);
    	//TODO Write a recursive function which removes the Node with keyword from the binary search tree.
    	//You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.
    }

    private Node delete(Node current, String keyword)
    {
        if(null == current)
        {
            return null;
        }

        int determinant = current.keyword.compareTo(keyword);

        if(determinant > 0)
        {
            current.l = delete(current.l, keyword);
        }
        else if(determinant < 0)
        {
            current.r = delete(current.r, keyword);
        }
        else if(null != current.l && null != current.r)
        {
            Node min = find_min(current.r);

            current.keyword = min.keyword;
            current.record = min.record;
            current.size = min.size;
            current.r = delete(current.r, current.keyword);
        }
        else
        {
            if(null != current.l)
            {
                current = current.l;
            }
            else if(null != current.r)
            {
                current = current.r;
            }
            else
            {
                current = null;
            }
        }

        return current;
    }

    private Node find_min(Node current)
    {
        if(null != current.l)
        {
            return find_min(current.l);
        }
        else
        {
            return current;
        }
    }

    /**
     * Print the tree
     */
    public void print(){
        print(root);
    }

    /**
     * Print a node of the tree
     * @param t
     */
    private void print(Node t)
    {
        if(t!=null)
        {
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;

            while(r != null)
            {
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }

            print(t.r);
        } 
    }
    

}

import sun.reflect.generics.tree.Tree;

import java.awt.print.Book;
import java.io.PrintStream;
import java.util.Comparator;

class ST {

    private class TreeNode {

        int id;
        String city;
        int N;
        List booklist;

        public TreeNode l, parent, r;


        TreeNode(int id, String city) {
            this.id = id;
            this.city = city;
        }




       /* TreeNode (TreeNode COPY) {
            this.id = COPY.id;
            this.booklist = COPY.booklist;
            this.city = COPY.city;
            this.left = COPY.left;
            this.right = COPY.right;
            this.parent = COPY.parent;
            this.N = COPY.N;

        } */



        TreeNode(List booklist) {
            if (booklist.isEmpty()) throw new IllegalArgumentException();{
                this.booklist = booklist;
            }
        }


        public int compareTo(TreeNode t2) {
            return Integer.compare(this.id, t2.id);
        }

        protected void unlink() {
            booklist = null;
            parent = l = r = null;
        }


    }


    private TreeNode head;
    int size;
    protected Comparator cmp;

    String to_print = "";


    public ST() {
        this(new DefaultComparator());
    }

    public ST(Comparator cmp) {
        this.size = 0;
        this.cmp = cmp;
    }

    public int size() {
        return size;
    }



    void insertWarehouse(int nodeid, String name) {

        if (find(nodeid) != null) System.err.println("Warehouse with id: " + nodeid + " already exists and can not be inserted again!");

        head = insertR(head,nodeid,name);
        head.booklist = new List("list",cmp);




    }





    void insertBookAtWarehouse(int nodeid, int isbn, int copies) {

        TreeNode found = find(nodeid);
        if (found == null)System.out.println("Warehouse with id: " + nodeid + " does not exist!");
        else {
            try {
                ListNode current_list = found.booklist.find(isbn);
                current_list.data.setCopies(current_list.data.getCopies()+copies);
            } catch (NullPointerException e) {



                found.booklist.insertAtFront(new BookInfo(isbn,copies));
                found.booklist.sort();
            }



        }



    }



    void searchByWarehouse(int nodeid) {
        TreeNode p = find(nodeid);
        if (p == null) return;
        System.out.println("Warehouse " + nodeid + " located in " + p.city);
        p.booklist.print();
    }


    void searchBookInWarehouse(int nodeid, int isbn) {
        TreeNode found = find(nodeid);
        if (found != null) {
            ListNode book = found.booklist.find(isbn);
            if (book != null) {
                System.out.println("Book exists! Copies: " + book.data.getCopies());
            } else {
                System.out.println("Warehouse " + nodeid + " does not have this book.");
            }
        }

    }

    void searchBook(int isbn) {

        findPostorder(head, isbn);
    }

    void findPostorder(TreeNode h, int isbn)
    {
        if (h == null)
            return;

        findPostorder(h.l, isbn);

        findPostorder(h.r, isbn);

        if (h.booklist.find(isbn) != null) System.out.println("Warehouse id: " + h.id + " City: " + h.city + " Copies: " + h.booklist.find(isbn).data.getCopies());

    }


    void printPreorder(TreeNode h)
    {

        if (h == null)
            return ;

        to_print = to_print + "Warehouse " + h.id + " City :" + h.city+ "\n" + h.booklist.toString();

        printPreorder(h.l);

        printPreorder(h.r);

    }


    void printΤree(PrintStream stream) {
        to_print = "";
        printPreorder(head);
        stream.println(to_print);

    }



    public TreeNode find(int id) {
        TreeNode p = head;
        while (p != null) {
            // Compare element with the element in the current subtree
            int result = cmp.compare(id, p.id);
            if (result == 0) {
                break;
            }
            // Go left or right based on comparison result
            p = result < 0 ? p.l : p.r;
        }

        //if (p == null) System.out.println("Warehouse with id " + id + " could not be found in the database");
        return p;
    }





    void removeWarehouse(int nodeid){
       if ( find(nodeid) == null)System.out.println("Warehouse with id: " + nodeid + " does not exist and can not be deleted");
       else
       removeR(head, nodeid);
    }






    void removeBook(int nodeid, int isbn){
       TreeNode to_put = find(nodeid);

       if (to_put.booklist.delete_by_isbn(isbn) == null) System.out.println("Book with ISBN: " + isbn + " does not exist and can not be deleted");



    }



    private TreeNode removeR(TreeNode h, int id) {
        if (h == null) return null;
        int w = h.id;
        if (less(id,w)) h.l = removeR(h.l,id);
        if (less(w,id)) h.r = removeR(h.r,id);
        if (id==w) h= joinLR(h.l, h.r);
        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {
        int N = a.N + b.N;
        if (a == null) return b;
        if (b == null) return a;
        if (Math.random()*N < 1.0*a.N) {
            a.r = joinLR(a.r, b);
            return a;
        }
        else {
            b.l = joinLR(a, b.l);
            return b;
        }
    }







    private TreeNode insertR(TreeNode h, int id, String city) {
        if (h == null) return new TreeNode(id, city);
        if (Math.random() * (h.N + 1) < 1.0)
            return insertT(h, id, city);
        if (less(id, h.id))
            h.l = insertR(h.l, id, city);
        else
            h.r = insertR(h.r, id, city);
        h.N++;

        return h;

    }



    private TreeNode insertT(TreeNode h, int id, String city) {
        if (h == null) return new TreeNode(id, city);
        if (less(id, h.id)) {
            h.l = insertT(h.l, id, city);
            h = rotateRight(h); }
        else {
            h.r = insertT(h.r, id, city);
            h = rotateLeft(h); }
        return h; }



    private TreeNode rotateLeft(TreeNode pivot) {
        TreeNode parent = pivot.parent;
        TreeNode child = pivot.r;
        if (parent == null) {
            head = child;
        } else if (parent.l == pivot) {
            parent.l = child;
        } else {
            parent.r = child;
        }
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.r = child.l;
        if (child.l != null) child.l.parent = pivot;
        child.l = pivot;
        return child;
    }


    private TreeNode rotateRight(TreeNode pivot) {
        TreeNode parent = pivot.parent;
        TreeNode child = pivot.l;
        if (parent == null) {
            head = child;
        } else if (parent.l == pivot) {
            parent.l = child;
        } else {
            parent.r = child;
        }
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.l = child.r;
        if (child.r != null) child.r.parent = pivot;
        child.r = pivot;
        return child;
    }

    private boolean less(int a, int b) {
        return a < b;
    }





}

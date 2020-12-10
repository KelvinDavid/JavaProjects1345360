//Kelvin David - 1345360
//Daniel Wilson ID: 1345359
//Deque class used for tracking states


public class Deque
{
    //NODE CLASS
    private class Node
    {
        Node prev;
        Node next;
        int state;
        boolean isSCAN = false;

        public Node(int ste)
        {
            state = ste;
            Node prev = null;
            Node next = null;
        }
    }

    private Node head;
    private Node tail;

    //MAIN METHOD
    public Deque()
    {
        head = null;
        tail = null;
        Node SCAN = new Node(-5);
        SCAN.isSCAN = true;

        head = SCAN;
        tail = head;
    }

    //ADD METHODS
    public void addFirst(int s)
    {
        if(head == null)
        {
            head = new Node(s);
            tail = head;
            return;
        }
        Node cur = new Node(s);
        head.prev = cur;
        cur.next = head;
        head = cur;
        return;
    }

    public void addLast(int s)
    {
        if(head == null)
        {
            head = new Node(s);
            tail = head;
            return;
        }
        Node cur = new Node(s);
        tail.next = cur;
        cur.prev = tail;
        tail = cur;
        return;
    }
    //

    //PEEK MEHTODS
    public int peekFirst()
    {
        return head.state;
    }

    //

    //REMOVE METHODS
    public void removeFirst()
    {
        if(head.next == null)
        {
            tail = null;
        }
        head = head.next;
        if(head != null)
        {
            head.prev = null;
        }
        return;
    }

    public void removeAllFirst()
    {
        while(!head.isSCAN)
        {
            removeFirst();
        }
    }
    //

    //DATA ACCESS
    public boolean checkSCAN()
    {
        return head.isSCAN;
    }

    public boolean isEmpty()
    {
        if(head == null)
        {
            return true;
        }
        return false;
    }

    public String getAll()
    {
        String out = "";
        Node cur = head;
        while(cur != null)
        {
            //System.out.println(cur.state);
            out += (cur.state + ", ");
            cur = cur.next;
        }
        return out;
    }

    public int length()
    {
        int count = 0;
        Node cur = head;
        while(cur != null)
        {
            //System.out.println(cur.state);
            count++;
            cur = cur.next;
        }
        return count;
    }

    //STATE CHECKS
    public boolean HasQueueReset()
    {
        System.out.println("Cur head = " + peekFirst());
        Node SCAN = head;
        removeFirst();
        System.out.println("InStack: " + getAll());
        if(isEmpty())
        {
            head = SCAN;
            tail = SCAN;
            return false;
        }
        else
            {
                SCAN.prev = tail;
                SCAN.next = null;
                tail.next = SCAN;
                tail = SCAN;
                System.out.println("new head = " + peekFirst());
                return true;
            }

    }


}

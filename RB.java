/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

/**
 *
 * @author user
 */
class RB
{
        Node root = null;
        void insert(Node x)
        {
            if(root == null)
            {
                root = x;
                x.color = "B";
            }
            else
            {
                Node y=null;
                Node z = root;
                while(z!=null)
                {
                    y = z;
                    if(x.data<z.data)
                        z = z.left;
                    else
                        z = z.right;
                }
                x.parent = y;
                if(y == null)
                    root = x;
                else
                {
                    if(x.data<y.data)
                        y.left = x;
                    else
                        y.right = x;
                }

                x.left = null;
                x.right = null;
                x.color = "R";
                if(x.parent!=root)
                    insert_check(x);
            }
        }


        void insert_check(Node z)
        {
            while(z!=root && z.parent.color.equals("R"))
            {
                if(z.parent==z.parent.parent.left)
                {
                    Node y = z.parent.parent.right;
                    if(y!=null && y.color.equalsIgnoreCase("R"))
                    {
                        z.parent.color = "B";
                        y.color = "B";
                        z.parent.parent.color = "R";
                        z = z.parent.parent;
                    }
                    else
                    {
                            if(z==z.parent.right)
                            {
                                z = z.parent;
                                leftrotate(z);
                            }
                            z.parent.color = "B";
                            z.parent.parent.color = "R";
                            rightrotate(z.parent.parent);
                    }

                }
                else
                {
                        Node y = z.parent.parent.left;
                        if(y!=null && y.color.equalsIgnoreCase("R"))
                        {
                            z.parent.color = "B";
                            y.color = "B";
                            z.parent.parent.color = "R";
                            z = z.parent.parent;
                        }
                        else
                        {
                            if(z==z.parent.left)
                            {
                                z = z.parent;
                                rightrotate(z);
                            }
                            z.parent.color = "B";
                            z.parent.parent.color = "R";
                            leftrotate(z.parent.parent);
                        }

                }
            }
            root.color = "B";
        }


        void delete(int data)
        {
            Node z = search(data);
            if(z==null)
                return;
            Node y = null, x;
            if((z.left==null)&&(z.right==null))
                y = z;
            else
                y = successor(z);
            if(y==null)
                y = predecessor(z);
            if(y.left!=null)
                x = y.left;
            else
                x = y.right;
            if(x!=null)
                x.parent = y.parent;
            if(y.parent==null)
                root=x;
            else
            {
                if(y == y.parent.left)
                    y.parent.left = x;
                else
                    y.parent.right = x;
            }
            if(y!=z)
                z.data = y.data;
            if(y.color.equals("B"))
                delete_check(x);
        }


        void delete_check(Node x)
        {
            Node w;
            while((x!=root)&&(x!=null)&&(x.color.equals("B")))
            {
                if(x == x.parent.left)
                {
                    w = x.parent.right;
                    if(w!=null)
                    {
                        if(w.color.equals("R"))
                        {
                            w.color = "B";
                            x.parent.color = "R";
                            leftrotate(x.parent);
                            w = x.parent.right;
                        }
                        if(((w.left==null)||(w.left.color.equals("B")))&&((w.right==null)||(w.right.color.equals("B"))))
                        {
                            w.color = "R";
                            x = x.parent;
                        }
                        else
                        {
                            if((w.right==null)||(w.right.color.equals("B")))
                            {
                                if(w.left!=null)
                                    w.left.color = "B";
                                w.color = "R";
                                rightrotate(w);
                                w = x.parent.right;
                            }
                            w.color = x.parent.color;
                            x.parent.color = "B";
                            w.right.color = "B";
                            leftrotate(x.parent);
                            x = root;
                        }
                    }
                }
                else
                {
                    w = x.parent.left;
                    if(w!=null)
                    {
                        if(w.color.equals("R"))
                        {
                            w.color = "B";
                            x.parent.color = "R";
                            rightrotate(x.parent);
                            w = x.parent.left;
                        }
                        if(((w.right==null)||(w.right.color.equals("B")))&&((w.left==null)||(w.left.color.equals("B"))))
                        {
                            w.color = "R";
                            x = x.parent;
                        }
                        else
                        {
                            if((w.left==null)||(w.left.color.equals("B")))
                            {
                                if(w.right!=null)
                                    w.right.color = "B";
                                w.color = "R";
                                leftrotate(w);
                                w = x.parent.left;
                            }
                            w.color = x.parent.color;
                            x.parent.color = "B";
                            w.left.color = "B";
                            rightrotate(x.parent);
                            x = root;
                        }
                    }
                }
            }
            if(x!=null)
                x.color = "B";
        }


        public Node search(int data)
        {
            Node n = root;
            while(n != null)
            {
                if(n.data==data)
                    return n;
                else
                {
                    if(data<n.data)
                        n = n.left;
                    else
                        n = n.right;
                }
            }
            System.out.println("Data not found");
            return null;
        }
        public Node successor(Node n)
        {
            Node m = root;
            if(n.right!=null)
            {
                Node c = n.right;
                while(c.left!=null)
                    c = c.left;
                return c;
            }
            else
            {
                Node p = n.parent;
                while(p!=null&&(n==p.right))
                {
                    n = p;
                    p = p.parent;
                }
                return p;
            }
        }


        public Node predecessor(Node n)
        {
            Node m = root;
            if(n.left!=null)
            {
                Node c = n.left;
                while(c.right!=null)
                    c = c.right;
                return c;
            }
            else
            {
                Node p = n.parent;
                while(p!=null&&(n==p.left))
                {
                    n = p;
                    p = p.parent;
                }
                return p;
            }
        }


        public void leftrotate(Node x)
        {
            Node y = x.right;
            x.right = y.left;
            if(y.left!=null)
                y.left.parent = x;
            y.parent = x.parent;
            if(x.parent == null)
                root = y;
            else
            {
                if(x == x.parent.left)
                    x.parent.left = y;
                else
                    x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
            
        }
        public void rightrotate(Node x)
        {
            Node y = x.left;
            x.left = y.right;
            if(y.right!=null)
                y.right.parent = x;
            y.parent = x.parent;
            if(x.parent == null)
                root = y;
            else
            {
                if(x == x.parent.right)
                    x.parent.right = y;
                else
                    x.parent.left = y;
            }
            y.right = x;
            x.parent = y;
        }
 
        


        public void print(Node n)
        {
            if(n==null)
                System.out.println("Empty tree");
            else
            {
                if(n.parent!=null)
                    System.out.println("Node-> "+n.data+" Color-> "+n.color+" Size-> "+n.height+" Parent-> "+n.parent.data);
                else
                    System.out.println("Node-> "+n.data+" Color-> "+n.color+" Size-> "+n.height+" This is root node");    
                if(n.left!=null)
                    print(n.left);
                if(n.right!=null)
                    print(n.right);        }

            }


        public void print_node(Node n)
        {
            if(n.parent!=null)
                System.out.println("Node->  "+n.data+" Color-> "+n.color+" Size-> "+n.height+" Parent: "+n.parent.data);
            else
                System.out.println("Node-> "+n.data+" Color-> "+n.color+" Size-> "+n.height+" This is root");
        }
        Node temp =null;
        void inorder(Node x){
           if(x.left!=null)
                inorder(x.left);
           if(x.right!=null)
               inorder(x.right);
           temp.height+=1;
        }

        void calculateSize(Node x){
              temp=x;
              x.height=0;
               inorder(x);
               x.height=temp.height;
               if(x.left!=null)
                    calculateSize(x.left);
               if(x.right!=null)
                    calculateSize(x.right);
        }
        Node select(Node x,int i){
            int r;
            if(x.left==null)
                return x;
            r=x.left.height+1;

            if(i==r)
                return x;
            else if(i<r)
                return select(x.left,i);
            else
                return select(x.right,i-r);
            
        }
        Node temp1=null;
        void findNode(Node x,int key){
            if(x.data==key)
                    temp1=x;
            if(x.left!=null)
                findNode(x.left,key);
            if(x.right!=null)
                findNode(x.right,key);            
        }
        
        Node y;
        int rank(int key){
            Node z=root;
            findNode(z,key);
            Node x=temp1;
            int r;
            if(x.left!=null)
                r=x.left.height+1;
            else
                r=x.height;
            y=x;
            while(y!=root){
                if(y==y.parent.right)
                    r=r+ y.parent.left.height+1;
                y=y.parent;
            }
            return r;                
        }
        
        void inorderT(Node node) {
            if (node == null) {
              return;
            }

            inorderT(node.left);
            System.out.printf("%s ", node.data);
            inorderT(node.right);
        }

    }

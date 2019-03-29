package rbtree;

import java.util.ArrayList;
import java.util.Scanner;

public class RBtree {
    public static void main(String[] args)
        {
            RB rb = new RB();
            int key,pos;
            Node a;
            Scanner sc=new Scanner(System.in);
            ArrayList<Integer> data=new ArrayList<>();
            System.out.println("Enter node values(enter '-999' to end):");
            while(true){
               key=sc.nextInt();
               a = new Node(key);
               if(key==-999)
                   break;
               data.add(key);
               rb.insert(a);
            }
            rb.calculateSize(rb.root);
            int ch;

            do{
                System.out.println("Options Available:-");
                System.out.println("0.Exit");
                System.out.println("1.Insert");
                System.out.println("2.Delete");
                System.out.println("3.Display");
                System.out.println("4.Find ith smallest element");
                System.out.println("5.Position of node");
                System.out.print("What you want to do? ");
                ch=sc.nextInt();
                System.out.println("");
                switch(ch){
                    case 0:System.out.println("You opted to exit");
                           break;
                    case 1:System.out.print("Enter key to be inserted:");
                           rb.calculateSize(rb.root);
                           key=sc.nextInt();
                           a=new Node(key);
                           rb.insert(a);
                           data.add(key);
                           System.out.println("Node Inserted Successfully.");
                           break;
                    case 2:System.out.print("Enter key to be deleted:");
                           rb.calculateSize(rb.root);
                           key=sc.nextInt();
                           rb.delete(key);
                           System.out.println("Node Deleted Successfully");
                           break;
                    case 3:System.out.println("************Tree Structure***********");
                           rb.calculateSize(rb.root);
                           rb.print(rb.root);
                           System.out.print("Inorder Traversal: ");
                           rb.inorderT(rb.root);
                           System.out.println("");
                           break;
                    case 4:System.out.print("Enter rank of the key:");
                           int k=sc.nextInt();
                           if(k>data.size()){
                               System.out.println("Invalid Input");
                               break;
                           }
                           a=rb.select(rb.root,k);
                           System.out.println("The "+k+"th smallest node is");
                           rb.print_node(a);
                           System.out.print("Inorder Traversal: ");
                           rb.inorderT(rb.root);
                           System.out.println("");
                           System.out.println("");
                           break;
                    case 5:System.out.print("Enter value of node:-");
                           key=sc.nextInt();
                           rb.calculateSize(rb.root);
                           pos=rb.rank(key);
                           System.out.println("The node "+key+" is present at "+pos+"th position");
                           System.out.print("Inorder Traversal: ");
                           rb.inorderT(rb.root);
                           System.out.println("");
                           System.out.println("");
                           break;
                   default:System.out.println("Select appropiate option");
                           break;
               }
           }while(ch!=0);

        }

    }

/*30 15 70 10 20 60 85 5 50 65 80 90 40 55 -999*/
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
class Node
    {
        int data;
        int height;
        String color;
        Node left;
        Node right;
        Node parent;

        public Node(int data)
        {
            this.data = data;
            this.color = "R";
            this.parent = null;
            this.left = null;
            this.right = null;
            this.height=0;
        }
    }
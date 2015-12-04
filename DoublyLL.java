import java.util.*;
import java.io.*;


class SinglyLL
{
public static void main(String args[])
{
	System.out.println(" A singly Linked List =========================");
	boolean proceed=true;
	Scanner sc = new Scanner(System.in);
	LinkedList L = new LinkedList();
	try{
	while(proceed)
	{
		System.out.println("WHat you want to perform");
		
		System.out.println("1 : Insert into the linked list");
		System.out.println("2 : Delete from the linked list");
		System.out.println("3 : Search data in the linked list");
		System.out.println("4 : print LL");
		System.out.println("5 : EXIT");
	int stra= sc.nextInt();
		switch(stra)
		{	
			case 1:
				System.out.println("Please enter the data to insert");
				L.AddLL(sc.nextInt());
				break;
			case 2:
				System.out.println("Please enter the data to delete");
				L.DeleteLL(sc.nextInt());	
				break;
			case 3:
				System.out.println("Please enter the data to search");
				L.SearchLL(sc.nextInt());
				break;
			case 4:
				L.printLL();	
				break;
			default:
				proceed = false;
			
		}

	}
	}
	catch(Exception E)
	{
		System.out.println("Incorrect input entered");
	}
}



}//End of class SIngly LL

class Node
{
	int data;
	Node next;

	public Node()
	{
		data=10;
		next=null;
	}

	public Node(int DATA)
	{
		data=DATA;
		next=null;
	}
	

}//end of class Node 

class LinkedList
{
	Node node;
	Node head;
	int size;

	public LinkedList()
	{
		node=null;
		head=null;
		size=0;
	}
	public void AddLL(int data)
	{
		if(head==null)
		{
			Node n = new Node(data);
			head = n;
			size++;
		}
		else
		{
			Node temp;
			temp=head;
			while(temp.next!=null)
				temp=temp.next;
			Node n= new Node(data);
			temp.next=n;

			size++;
		}	
	}
	
	public void printLL()
	{
		Node temp;
		temp=head;
		while(temp!=null)
		{
			System.out.println("The data of the this node is:" + temp.data);
			temp=temp.next;
		}
		
			System.out.println("Size of the linked lIst is " + size);
	
	}
	public void DeleteLL(int data)
	{
		if(head!=null)
		{
			Node temp;
			temp=head;
			if(head.data==data)
			{
				head=head.next;
				size--;
			}
			
			else
			{
				while(temp.next!=null)
				{
					if(temp.next.data==data)
					{
						temp.next=temp.next.next;
						System.out.println("Data found and will be deleted...");
						size--;
						break;
					}
					else
					temp=temp.next;
				}
				if(temp==null)	
					System.out.println("No Data found");
			}
		}
		else
					System.out.println("No Data found");
	}

	public void SearchLL(int data)
	{
		boolean SearchFlag=false;
		int counter=0;
		Node temp=head;
		while(temp!=null)
		{
			if(temp.data==data)
			{
				SearchFlag=true;
				System.out.print("the data is found in LinkList at location"+counter);
			}
			temp=temp.next;	
			size+=1;
		}
			if(SearchFlag==false)
			System.out.print("The is abscent in the linkList");
	}
}// end of Lined List class

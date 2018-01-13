/*
 * ListNode.java
 *
 */

// class to represent one node in a list
class ListNode
{
	// package access members; List can access these directly
	BookInfo data;
	ListNode nextNode;

	// constructor creates a ListNode that refers to object
	ListNode( BookInfo object )
	{
		this( object, null );
	} // end ListNode one-argument constructor

	// constructor creates ListNode that refers to
	// Object and to next ListNode
	ListNode( BookInfo object, ListNode node )
	{
		data = object;
		nextNode = node;
	} // end ListNode two-argument constructor

	// return reference to data in node
	BookInfo getObject()
	{
		return data; // return Object in this node
	} // end method getObject

	// return reference to next node in list
	ListNode getNext()
	{
		return nextNode; // get next node
	} // end method getNext
} // end class ListNode
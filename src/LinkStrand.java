public class LinkStrand implements IDnaStrand {
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	  
	//create variables
	private Node myFirst,myLast;   
	private long mySize;
	private int myAppends;
	private Node myCurrent;
	private int myIndex;
	private int myLocalIndex;

	public LinkStrand() {
		//call second constructor with empty string as parameter
		this("");
	}
	
	public LinkStrand(String s) {
		//initialize all variables
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		//initialize variables
		myFirst = new Node(source);
		myLast = new Node(source);
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myCurrent = myFirst;
		myLocalIndex = 0;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		//find end of list and then add another link by changing pointer of last element to a new node,
		//and pointing that new node to null
		Node temp = myFirst;
		Node last = new Node(dna);
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = last;
		last.next = null;
		myLast = last;
		myAppends++;
		mySize += dna.length();
		return this;
	}
	
	public String toString() {
		//cycle though all links in list, appending .info strings to a StringBuilder object
		StringBuilder last = new StringBuilder();
		Node temp = myFirst;
		if (myFirst == null) {
			return null;
		}
		while (temp != null) {
			last.append(temp.info);
			temp = temp.next;
		}
		//convert StringBuilder to string and return it
		String newLast = last.toString();
		return newLast;
	}

	@Override
	public IDnaStrand reverse() {
		//reverse direction of current pointer for each link (pointing at null if at first link in list)
		LinkStrand x = new LinkStrand();
		x = this;
		
		Node temp = x.myFirst;	
		Node saved = null;
		while (temp != null) {
			StringBuilder copy = new StringBuilder(temp.info);
			copy.reverse();
			temp.info = copy.toString();
			Node second = temp.next;
			temp.next = saved;
			saved = temp;
			temp = second;
		}
		x.myFirst = saved;
		return x;
		
	}
	
	@Override
	public int getAppendCount() {
		//return number of times list was appended to
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// if index is bigger than size of LinkStrand, break
		if (index > mySize) {
			throw new IndexOutOfBoundsException();
		}
		
		//two pronged if statement to direct search for index
		if (index < myIndex) {
			//if index is less than myIndex, reset myIndex to 0 and myCurrent to first link
			myIndex = 0;
			myCurrent = myFirst;
			while (myIndex<index) {
				//cycle through links adding length of strings to myIndex until myIndex surpasses index
				myIndex += myCurrent.info.length();
				if(myIndex > index) {
					//once myIndex surpasses index:
					//1.	subtract the most recently added quantity
					//2.calculate local index
					//3.create char array of local string and choose char at local index
					myIndex -= myCurrent.info.length();
					myLocalIndex = index - myIndex;
					char[] arr = myCurrent.info.toCharArray();
					char charAtIndex = arr[myLocalIndex];
					return charAtIndex;
				} 
				myCurrent = myCurrent.next;
			}
		} else if (index >= myIndex) {
			//if index is greater than myIndex, start search at myIndex 
			while (myIndex<=index) {
				//cycle through links adding length of strings to myIndex until myIndex surpasses index
				myIndex += myCurrent.info.length();
				if(myIndex > index) {
					//once myIndex surpasses index:
					//1.	subtract the most recently added quantity
					//2.calculate local index
					//3.create char array of local string and choose char at local index
					myIndex -= myCurrent.info.length();
					myLocalIndex = index - myIndex;
					char[] arr = myCurrent.info.toCharArray();
					char charAtIndex = arr[myLocalIndex];
					return charAtIndex;
				}
				myCurrent = myCurrent.next;
			}
		}	
		return ' ';
	}

}


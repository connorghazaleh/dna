public class LinkStrand implements IDnaStrand {
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	   
	private Node myFirst,myLast;   
	private long mySize;
	private int myAppends;
	private Node myCurrent;
	private int myIndex;

	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = new Node(source);
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myCurrent = myFirst;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
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
		StringBuilder last = new StringBuilder();
		Node temp = myFirst;
		if (myFirst == null) {
			return null;
		}
		while (temp != null) {
			last.append(temp.info);
			temp = temp.next;
		}
		String newLast = last.toString();
		return newLast;
	}

	@Override
	public IDnaStrand reverse() {
		
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
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		System.out.println("starting charAt");
		if (index > mySize) {
			throw new IndexOutOfBoundsException();
		}
		System.out.println("all good so far");
		System.out.println("mySize: " + mySize);
		System.out.println("myIndex: " + myIndex);
		System.out.println("index: " + index);
		if (index < myIndex) {
			myIndex = 0;
			myCurrent = myFirst;
			System.out.println("starting while loop for index < myIndex");
			while (myIndex<index) {
				myIndex += myCurrent.info.length();
				if(myIndex > index) {
					myIndex -= myCurrent.info.length();
					int difference = index - myIndex;
					char[] arr = myCurrent.info.toCharArray();
					char charAtIndex = arr[difference];
					return charAtIndex;
				}
				myCurrent = myCurrent.next;
			}
		} else if (index >= myIndex) {
			System.out.println("starting while loop for index >= myIndex");
			while (myIndex<=index) {
				System.out.println("1");
				myIndex += myCurrent.info.length();
				System.out.println("2");
				if(myIndex > index) {
					myIndex -= myCurrent.info.length();
					System.out.println("3");
					int difference = index - myIndex;
					char[] arr = myCurrent.info.toCharArray();
					System.out.println("4");
					char charAtIndex = arr[difference];
					System.out.println("5");
					return charAtIndex;
				}
				myCurrent = myCurrent.next;
			}
		}	
		return ' ';
	}

}


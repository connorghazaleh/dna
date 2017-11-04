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

	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = new Node(source);
		myAppends = 0;
		mySize = source.length();
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return null;
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
		System.out.println(newLast);
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
		// TODO Auto-generated method stub
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

}


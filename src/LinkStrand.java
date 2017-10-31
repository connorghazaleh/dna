
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
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
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
		return null;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return null;
	}

}

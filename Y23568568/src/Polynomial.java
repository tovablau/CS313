import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Polynomial implements PolynomialInterface{

    private HashMap<Integer, Integer> Polynomial = new HashMap<Integer, Integer>();
    
    public Polynomial () {
    	this.Polynomial = new HashMap<Integer, Integer>();
    }

    public Polynomial(String s) {
    	s = s.replaceAll("\\s+","");
        int count = s.length() - s.replace("X", "").length();
    	String [] arr = new String [count];
    	arr = s.split("(?=[+-])");
 
    	
    	String [] temp = new String [2];
    	
    	for (int i=0; i<arr.length; i++) {
    		if (arr[i].contains("^")) {
    			temp = arr[i].split("X\\^");
    			if (temp[0].equals("") || temp[0].equals("+")) this.putNew(Integer.parseInt(temp[1]), Integer.valueOf(1));
    			else if (temp[0].equals("-")) this.putNew(Integer.parseInt(temp[1]), Integer.valueOf(-1));
    			else {
    				this.putNew(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
    			}
    		}
    		
    		else if (arr[i].contains("X")){
    			temp = arr[i].split("X");
    			if (temp.length == 0) this.putNew(Integer.valueOf(1), Integer.valueOf(1));
    			else if (temp[0].equals("+")) this.putNew(Integer.valueOf(1), Integer.valueOf(1));
    			else if (temp[0].equals("-")) this.putNew(Integer.valueOf(1), Integer.valueOf(-1));
    			else this.putNew(Integer.valueOf(1), Integer.parseInt(temp[0]));
    		}
    		else {
    			temp = arr[i].split("X");
    			this.putNew(Integer.valueOf(0), Integer.parseInt(temp[0]));
    		}
    		
    	}
    	
    }
    
    public HashMap<Integer, Integer> getPoly(){
    	return this.Polynomial;
    }
    public void putNew(int a, int b) {
    	if (!this.Polynomial.containsKey(Integer.valueOf(a))) {
    		this.Polynomial.put(Integer.valueOf(a), Integer.valueOf(b));
    	}
    	else {
    		Integer c = this.Polynomial.get(Integer.valueOf(a));
    		c+= b;
    		this.Polynomial.put(Integer.valueOf(a), c);
    	}
    }
    
    @Override
    public Polynomial add(Polynomial p) {
    	Polynomial pn = new Polynomial();
    	for (Entry<Integer, Integer> entry : p.getPoly().entrySet()) {
    	    Integer key = entry.getKey();
    	    Integer value = entry.getValue();
    	    pn.putNew(key, value);
    	}
    	for (Entry<Integer, Integer> entry : this.Polynomial.entrySet()) {
    	    Integer key = entry.getKey();
    	    Integer value = entry.getValue();
    	    pn.putNew(key, value);
    	}
    	return pn;
    }
    @Override
	public Polynomial subtract(Polynomial p) {
    	Polynomial pn = new Polynomial();
    	for (Entry<Integer, Integer> entry : p.getPoly().entrySet()) {
    	    Integer key = entry.getKey();
    	    Integer value = (entry.getValue() * -1);
    	    pn.putNew(key, value);
    	}
    	for (Entry<Integer, Integer> entry : this.Polynomial.entrySet()) {
    	    Integer key = entry.getKey();
    	    Integer value = entry.getValue();
    	    pn.putNew(key, value);
    	}
    	return pn;	
	}
    
    @Override
	public Polynomial multiply(Polynomial p) {
		Polynomial pn = new Polynomial();
		Integer k2 = 0;
		Integer v2 = 0;
		for (Entry<Integer, Integer> entry : this.Polynomial.entrySet()) {
			Integer k = entry.getKey();
    	    Integer value = entry.getValue();
    	    
			for (Entry<Integer, Integer> entry2 : p.getPoly().entrySet()) {
				k2 = entry2.getKey();
				v2 = entry2.getValue();
    	    	pn.putNew(k+k2, value*v2);
			}	
    	}
		return pn;
	}

    public Polynomial divide(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }
    
//    public LinkedList<Term> deepCopy(LinkedList<Term> p)   {
        //implement this!
//    }
//    public Polynomial dividePolynomials(Polynomial a, Polynomial b) {
//    	for (Entry<Integer, Integer> entry : this.Polynomial.entrySet()) {
//			Integer k = entry.getKey();
//    	    Integer value = entry.getValue();
//    	}    
//    	    
//        Polynomial q = new Polynomial();
//        q.getPoly().put(0, 0);
//        //set q to be the polynomial 0
//        LinkedList<Term> r = deepCopy(a);  //want a copy of a, not just reference.      
//        while(!isEmpty(r) && (r.highestDegree() > d.highestDegree()))
//        {
//                int co = r.get(0).getCo()/d.get(0).getCo();
//                int ex = r.get(0).getEx()/d.get(0).getEx();
//                Term t = new Term(co,ex);
//                q = addPolynomials(q,t);
//                r = subtractPolynomials(r,multiplyPolynomials(t,d));
//                //assume subtract/multiplyPolynomial returns LinkedList<Term>
//        }
//        return QRPair(q,r);
//    }
    public Polynomial remainder(Polynomial p) throws Exception{
        throw new UnsupportedOperationException("Not implemented");
    }

    public final String toString() {
    	String s = "";
    	for (Entry<Integer, Integer> entry : this.Polynomial.entrySet()) {
			Integer k = entry.getKey();
    	    Integer value = entry.getValue();
    		if (value >= 0) {
    			s += "+" + Integer.toString(value) + "X^" + Integer.toString(k);
    		}
    		else {
    			s += Integer.toString(value) + "X^" + Integer.toString(k);
    		}
    	}
    	if (s.startsWith("+")) return s.substring(1);
    	return s;
    }
}
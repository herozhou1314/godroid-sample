// Java Package prime is a proxy for talking to a Go program.
//   gobind -lang=java github.com/hkurokawa/godroid-sample
//
// File is generated by gobind. Do not edit.
package go.prime;

import go.Seq;

public abstract class Prime {
    private Prime() {} // uninstantiable
    
    public static long MaxPrime(long n) {
        go.Seq _in = new go.Seq();
        go.Seq _out = new go.Seq();
        long _result;
        _in.writeInt(n);
        Seq.send(DESCRIPTOR, CALL_MaxPrime, _in, _out);
        _result = _out.readInt();
        return _result;
    }
    
    private static final int CALL_MaxPrime = 1;
    private static final String DESCRIPTOR = "prime";
}

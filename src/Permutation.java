import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        final int count = Integer.parseInt(args[0]);
        final RandomizedQueue<String> rs = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rs.enqueue(item);
        }
        for (int i = 0; i < count; i++) {
            StdOut.println(rs.dequeue());
        }
    }
}

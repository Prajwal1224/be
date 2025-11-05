import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncoding {

    // Node for Huffman tree
    static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
            this.left = this.right = null;
        }
    }

    // Generate codes by traversing the tree
    static void generateCodes(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) return;

        // If this is a leaf node
        if (root.left == null && root.right == null) {
            // If tree has only one character, ensure a code exists (use "0")
            if (str.length() == 0) str = "0";
            huffmanCode.put(root.ch, str);
        }

        generateCodes(root.left, str + "0", huffmanCode);
        generateCodes(root.right, str + "1", huffmanCode);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string to encode: ");
        String text = sc.nextLine();

        if (text == null || text.length() == 0) {
            System.out.println("Empty input. Nothing to encode.");
            sc.close();
            return;
        }

        // Count frequency of each character
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // Priority queue (min-heap) ordered by frequency
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.freq, b.freq));

        // Create leaf nodes and add to pq
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            pq.add(new Node(e.getKey(), e.getValue()));
        }

        // Build Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node sum = new Node('\0', left.freq + right.freq);
            sum.left = left;
            sum.right = right;
            pq.add(sum);
        }

        Node root = pq.peek();

        // Generate Huffman Codes
        Map<Character, String> huffmanCode = new HashMap<>();
        generateCodes(root, "", huffmanCode);

        // Print Huffman Codes
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> e : huffmanCode.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }

        // Encode the input string
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(huffmanCode.get(ch));
        }

        System.out.println("\nEncoded string:");
        System.out.println(encoded.toString());

        sc.close();
    }
}

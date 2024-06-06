package MinimumSpanningTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<String, String> parent;
    private Map<String, Integer> rank;

    public UnionFind(List<String> stars) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (String star : stars) {
            parent.put(star, star);
            rank.put(star, 0);
        }
    }

    public String find(String star) {
        if (!star.equals(parent.get(star))) {
            parent.put(star, find(parent.get(star)));
        }
        return parent.get(star);
    }

    public boolean union(String star1, String star2) {
        String root1 = find(star1);
        String root2 = find(star2);

        if (root1.equals(root2)) {
            return false;
        }

        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }

        return true;
    }
}
package com.CK;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}

class Solution {
    public boolean equationsPossible(String[] equations) {
        if (equations.length == 0) return true;

        DSU uf = new DSU();
        for (int i = 0; i < equations.length; i++) {
            if (equations[i].indexOf('!') >= 0)
                continue;
            int a = equations[i].charAt(0) - 'a', b = equations[i].charAt(3) - 'a';
            uf.union(a, b);
        }

        for (int i = 0; i < equations.length; i++) {
            if (equations[i].indexOf('!') < 0)
                continue;
            int a = equations[i].charAt(0) - 'a', b = equations[i].charAt(3) - 'a';
            if (uf.connected(a, b)) return false;
        }
        return true;
    }

    class DSU {
        int[] parents;
        int[] sizes;

        DSU() {
            parents = new int[26];
            sizes = new int[26];
            for (int i = 0; i < 26; i++) {
                parents[i] = i;
                sizes[i] = i;
            }
        }

        private int find(int i) {
            while (i != parents[i]) {
                parents[i] = find(parents[i]);
                i = parents[i];
            }
            return i;
        }

        private boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private void union(int p, int q) {
            int rp = find(p), rq = find(q);
            if (rp == rq) return;
            if (sizes[rp] >= sizes[rq]) {
                parents[rq] = rp;
                sizes[rp] += sizes[rq];
            } else {
                parents[rp] = rq;
                sizes[rq] += sizes[rp];
            }
        }
    }
}
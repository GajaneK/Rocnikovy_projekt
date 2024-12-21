package graphs;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Graphs {

    public static boolean isWalk(DirectedGraph g, List<Integer> vertexSequence) {
        // zistit, ci je dana postupnost vrcholov sledom v grafe g
        // walk definicia: sekvencia vrcholov taká, že každý vrchol je spojený s nasledujúcim, v tomto prípade orientovanými hranami
        if (vertexSequence.isEmpty()) {
            return false;
        }
        for (int vertex : vertexSequence) {
            if (!g.hasVertex(vertex)){
                throw new IllegalArgumentException("Nonexistent vertex.");is
            }
        }
        if (vertexSequence.size() == 1) {
            return true;
        } //ak je vertextSequence prazdna alebo obsahuje len jeden vrchol tak je sledom
        Iterator<Integer> iterator = vertexSequence.iterator();
        int current = iterator.next();

        while (iterator.hasNext()) {
            int next = iterator.next();
            if(!g.hasEdge(current, next)) {
                return false;
            }
            current = next;
        }
        return true;
    }

    public static boolean isPath(DirectedGraph g, List<Integer> vertexSequence) {
        // zistit, ci je dana postupnost vrcholov cestou v grafe g
        //path definicia: konecna alebo nekonecna sekvencia hran a neopakujúcich sa vrcholov (a teda ani hrany sa neopakujú)
        if (vertexSequence.isEmpty()) {
            return false;
        }
        for (int vertex : vertexSequence) {
            if (!g.hasVertex(vertex)){
                throw new IllegalArgumentException("Nonexistent vertex.");
            }
        }
        if (vertexSequence.size() == 1) {
            return true;
        }
        //ak je vertextSequence prazdna alebo obsahuje len jeden vrchol tak je cestou
        Set<Integer> visited = new HashSet<>();
        Iterator<Integer> iterator = vertexSequence.iterator();
        int current = iterator.next();
        visited.add(current);

        while(iterator.hasNext()){
            int next = iterator.next();
            if (!g.hasEdge(current, next) || visited.contains(next)) {
                return false;
            }
            visited.add(next);
            current = next;
        }
        return true;
    }

    public static boolean isDirectedCycle(DirectedGraph g, List<Integer> vertexSequence) {
        // zistit, ci je dana postupnost vrcholov orientovanou kruznicou v grafe g
        if (vertexSequence.isEmpty()) {
            return false;
        }
        for (int vertex : vertexSequence) {
            if (!g.hasVertex(vertex)){
                throw new IllegalArgumentException("Nonexistent vertex.");
            }
        }
        if (vertexSequence.size() == 1) {
            return g.hasEdge(vertexSequence.getFirst(), vertexSequence.getFirst()); //aby vrátilo true, musí existovať slučka na vrchole
        }
        Iterator<Integer> iterator = vertexSequence.iterator();
        int start = iterator.next();
        int current = start;

        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!g.hasEdge(current, next)){
                return false;
            }
            current = next;
        }
        return current == start;
    }

    public static boolean isUndirectedCycle(UndirectedGraph g, List<Integer> vertexSequence) {
        // zistit, ci je dana postupnost vrcholov neorientovanou kruznicou v grafe g
        // (rozdiel oproti orientovanym grafom je hlavne pri kruzniciach dlzky 2)
        if (vertexSequence.isEmpty()) {
            return false;
        }
        for (int vertex : vertexSequence) {
            if (!g.hasVertex(vertex)){
                throw new IllegalArgumentException("Nonexistent vertex.");
            }
        }
        if (vertexSequence.size() == 1) {
            return g.hasEdge(vertexSequence.getFirst(), vertexSequence.getFirst()); //musi obsahovat slucku
        }
        if (vertexSequence.size() == 2) {
            return false; //neorientovany graf nemoze mat cyklus na dvoch vrcholoch
        }

        Iterator<Integer> iterator = vertexSequence.iterator();
        int start = iterator.next();
        int current = start;

        while (iterator.hasNext()){
            int next  = iterator.next();
            if (!g.hasEdge(current, next)) {
                return false;
            }
            current = next;
        }
        return current == start;
    }

    public static boolean isTopologicalOrder(DirectedGraph g, List<Integer> vertexSequence) {
        // zistit, ci dana postupnost vrcholov reprezentuje topologicke usporiadanie grafu g
        // (elementarne overenie podmienky, netreba ziaden algoritmus na hladanie topologickeho usporiadania)
        for (int vertex : vertexSequence) {
            if (!g.hasVertex(vertex)){
                throw new IllegalArgumentException("Nonexistent vertex.");
            }
        }
        Map<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < vertexSequence.size(); i++) {
            if(!g.hasVertex(vertexSequence.get(i)));
            positions.put(vertexSequence.get(i), i);
        }

        for (int i = 0; i < g.getVertexCount(); i++) {
            for (int j : g.outgoingEdgesDestinations(i)) {
                if (positions.get(i) >= positions.get(j)) {
                    return false; //ak vrchol na pozicii j predchadza vrchol na pozicii i tak vertexSequence nie je topologick7m usporiadanim
                }
            }
        }

        return true;
    }
    private static void existsPathDFS(DirectedGraph g, int vertex, List<Boolean> visited) {
        visited.set(vertex, true);
        for (int successor : g.outgoingEdgesDestinations(vertex)) {
            if(!visited.get(successor)) {
                existsPathDFS(g, successor,visited);
            }
        }
    }

    public static boolean existsPath(DirectedGraph g, int from, int to) {
        // prehladavanie do hlbky;
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(g.getVertexCount(), false));
        existsPathDFS(g, from, visited);
        return visited.get(to);
    }


    public static DirectedGraph reflexiveTransitiveClosure(DirectedGraph g) {
        // vrati reflexivno-tranzitivny uzaver grafu g, v ktorom su dva vrcholy spojene hranou, ak su v povodnom spojene sledom
        ArrayList<DirectedEdge> edges = new ArrayList<>();

        for (int i = 0; i < g.getVertexCount(); i++) {
            for (int j = 0; j < g.getVertexCount(); j++) {
                if (existsPath(g, i, j) || i == j) {
                    edges.add(new DirectedEdge(i , j));
                }
            }
        }
        return new AdjacencyMatrixDirectedGraph(g.getVertexCount(), edges);
    }

    public static DirectedGraph transitiveClosure(DirectedGraph g) {
        // vrati tranzitivny uzaver grafu g, v ktorom su dva vrcholy spojene hranou, ak su v povodnom spojene sledom nenulovej dlzky

        return null;
    }

    public static int distance(DirectedGraph g, int from, int to) {
        // dlzka najkratsej cesty z vrcholu from do vrcholu to -- prehladavanie do sirky
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }

        List<Integer> distances = new ArrayList<>(Collections.nCopies(g.getVertexCount(), -1));
        Queue<Integer> queue = new LinkedList<>();
        distances.set(from, 0);
        queue.add(from);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int successor : g.outgoingEdgesDestinations(vertex)) {
                if(distances.get(successor) == -1) {
                    queue.add(successor);
                    distances.set(successor, distances.get(vertex)+1);
                    if (successor == to) {
                        return distances.get(successor);
                    }
                }
            }
        }
        return -1;
    }

    public static List<Integer> shortestPath(DirectedGraph g, int from, int to) {
        // najde lubovolnu najkratsiu cestu; prehladavanie do sirky
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        List<Integer> predecessors = new ArrayList<>(Collections.nCopies(g.getVertexCount(), -1));
        boolean[] visited = new boolean[g.getVertexCount()];
        Queue<Integer> queue = new LinkedList<>();
        visited[from] = true;
        queue.add(from);

        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int successor : g.outgoingEdgesDestinations(vertex)) {
                if (!visited[successor]){
                    visited[successor] = true;
                    predecessors.set(successor, vertex);
                    queue.add(successor);
                    if (successor == to) {
                        break;
                    }
                }
            }
        }
        if (!visited[to]){
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int current = to;
        while (current != -1) {
            path.addFirst(current);
            current = predecessors.get(current);
        }
      return path;
    }
    private static void longestPathBacktrack(DirectedGraph g, int from, int to, ArrayList<Boolean> visited, LinkedList<Integer> currentPath, List<Integer> longestPath) {
        if (currentPath.getLast() == to) {
            if (currentPath.size() > longestPath.size()) {
                longestPath.clear();
                longestPath.addAll(currentPath);
            }
        }
        else {
            for(int successor : g.outgoingEdgesDestinations(from)) {
                if(!visited.get(successor)){
                    visited.set(successor, true);
                    currentPath.add(successor);
                    longestPathBacktrack(g, successor, to, visited, currentPath, longestPath);
                    currentPath.removeLast();
                    visited.set(successor, false);
                }
            }
        }
    }

    public static List<Integer> longestPath(DirectedGraph g, int from, int to) {
        // prehladavanim s navratom najde lubovolnu najdlhsiu cestu
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        if (from == to) {
            return List.of(from);
        }
        ArrayList<Boolean> visited = new ArrayList<>(Collections.nCopies(g.getVertexCount(), false));
        LinkedList<Integer> currentPath = new LinkedList<>();
        List<Integer> longestPath = new ArrayList<>();
        currentPath.add(from);
        visited.set(from, true);

        longestPathBacktrack(g, from, to, visited, currentPath, longestPath);


        if (longestPath.isEmpty()) {
            return null;
        }
        else {
            return longestPath;
        }
    }

    public static boolean isAcyclic(DirectedGraph g) {
        // zistit ci je graf acyklicky; napr. rozsirene prehladavanie do hlbky z prednasky o topologickych usporiadaniach

        return false;
    }

    public static List<Integer> topologicalSort(DirectedGraph g) {
        // ak existuje, vrati nejake topologicke usporiadanie grafu g; v opacnom pripade vrati null
        int n = g.getVertexCount();
        List<Integer> unprocessedPredecessors = new ArrayList<>();
        for (int i = 0; i <= n-1; i++) {
            unprocessedPredecessors.add(0);
        }
        for (int i = 0; i <= n-1; i++) {
            for (int successor : g.outgoingEdgesDestinations(i)) {
                unprocessedPredecessors.set(successor, unprocessedPredecessors.get(successor)+1);
            }
        }
        Queue<Integer> ready = new LinkedList<>();
        for (int i = 0; i <= n-1; i++) {
            if (unprocessedPredecessors.get(i) == 0) {
                ready.add(i);
            }
        }
        List<Integer> result = new LinkedList<>();
        while (!ready.isEmpty()) {
            int vertex = ready.remove();
            result.add(vertex);
            for (int successor : g.outgoingEdgesDestinations(vertex)) {
                unprocessedPredecessors.set(successor, unprocessedPredecessors.get(successor)-1);
                if (unprocessedPredecessors.get(successor) == 0) {
                    ready.add(successor);
                }
            }
        }
        if (result.size() == n) {
            return result;
        } else {
            return null;
        }
    }

    public static boolean isTree(UndirectedGraph g) {
        // g je strom <==> je suvisly a acyklicky <==> je suvisly a pocet hran je o jedna mensi ako pocet vrcholov <==> ...

        return false;
    }

    public static boolean isBipartite(UndirectedGraph g) {
        // prehladavanie do sirky / do hlbky

        return false;
    }

    public static boolean hasColouring(UndirectedGraph g, int colourCount) {
        // pomocou prehladavania s navratom zisti, ci je graf g colourCount-ofarbitelny

        return false;
    }

    public static Map<Integer, Integer> getColouring(UndirectedGraph g, int colourCount) {
        // ak existuje, najde lubovolne spomedzi colourCount-farbeni grafu g (prehladavanim s navratom)

        return null;
    }

    public static Set<Integer> maximumIndependentSet(UndirectedGraph g) {
        // pomocou prehladavania s navratom najde najvacsiu nezavislu mnozinu vrcholov v grafe g (podobne ako hladanie najvacsej kliky z prednasky)

        return null;
    }

    public static Set<Integer> maximumVertexCovering(UndirectedGraph g) {
        // pomocou prehladavania s navratom najde najvacsie vrcholove pokrytie grafu g

        return null;
    }

    public static double cheapestWeightedPathValue(WeightedDirectedGraph g, int from, int to) {
        // najde cenu najlacnejsej cesty v grafe g z vrcholu from do vrcholu to za predpokladu, ze graf neobsahuje kruznice zapornej ceny (napr. Dijkstrov algoritmus)

        return 0;
    }

    public static List<Integer> cheapestWeightedPath(WeightedDirectedGraph g, int from, int to) {
        // najde najlacnejsiu cestu v grafe g z vrcholu from do vrcholu to za predpokladu, ze graf neobsahuje kruznice zapornej ceny (napr. Dijkstrov algoritmus)

        return null;
    }

    public static List<Integer> costliestWeightedPath(WeightedDirectedGraph g, int from, int to) {
        // najde najdrahsiu cestu v grafe g z vrcholu from do vrcholu to (prehladavanie s navratom)

        return null;
    }

}

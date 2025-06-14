package graphs;

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
                return false;
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
                return false;
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
                return false;
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
                return false;
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
        ArrayList<DirectedEdge> edges = new ArrayList<>();

        for (int i = 0; i < g.getVertexCount(); i++) {
            for (int j = 0; j < g.getVertexCount(); j++) {
                if (existsPath(g, i, j)) {
                    edges.add(new DirectedEdge(i , j));
                }
            }
        }
        return new AdjacencyMatrixDirectedGraph(g.getVertexCount(), edges);
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

    private static boolean isAcyclicDFS(DirectedGraph g, int u, Map<Integer, Integer> state) {
        state.put(u, 1);
        for (int v : g.outgoingEdgesDestinations(u)) {
            if (!state.containsKey(v)) {
                if (isAcyclicDFS(g, v, state)) return true;
            } else if (state.get(v) == 1) {
                return true;
            }
        }
        state.put(u, 2);
        return false;
    }

    public static boolean isAcyclic(DirectedGraph g) {
        // zistit ci je graf acyklicky; napr. rozsirene prehladavanie do hlbky z prednasky o topologickych usporiadaniach
        int size = g.getVertexCount();
        Map<Integer, Integer> state = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (!state.containsKey(i) && isAcyclicDFS(g, i, state)) {
                return false;
            }
        }
        return true;
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
    private static boolean isTreeDFS(UndirectedGraph g, int vertex, int parent, boolean[] visited) {
        visited[vertex] = true;
        for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
            if (visited[neighbor] && neighbor != parent) {
                return false;
            }
            if (!visited[neighbor] && !isTreeDFS(g, neighbor, vertex, visited)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTree(UndirectedGraph g) {
        // g je strom <==> je suvisly a acyklicky <==> je suvisly a pocet hran je o jedna mensi ako pocet vrcholov <==> ...
        int vertexCount = g.getVertexCount();
        int edgeCount = g.getUndirectedEdgeCount();

        if (edgeCount != vertexCount -1) {
            return false;
        }
        boolean[] visited = new boolean[vertexCount];
        if (!isTreeDFS(g, 0, -1, visited)) {
            return false;
        }
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBipartiteBFS(UndirectedGraph g, int start, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        colors[start] = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
                if(colors[neighbor] == -1) {
                    colors[neighbor] = 1 - colors[vertex];
                    queue.add(neighbor);
                }
                else if (colors[neighbor] == colors[vertex]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(UndirectedGraph g) {
        // prehladavanie do sirky / do hlbky
        int vertexCount = g.getVertexCount();
        int[] colors = new int[vertexCount];

        Arrays.fill(colors, -1);

        for (int i = 0; i < vertexCount; i++) {
            if(colors[i] == -1){
                if (!isBipartiteBFS(g, i, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean hasColouringBacktrack(UndirectedGraph g, int[] colors, int vertex, int colorCount) {
        if (vertex == g.getVertexCount()) {
            return true;
        }
        for (int i = 0; i < colorCount; i++) {
            boolean safe = true;
            for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
                if (colors[neighbor] == i) {
                    safe = false;
                    break;
                }
            }
            if (safe) {
                colors[vertex] = i;
                if (hasColouringBacktrack(g, colors, vertex +1, colorCount)) {
                    return true;
                }
                colors[vertex] = -1;
            }
        }
        return false;
    }

    public static boolean hasColouring(UndirectedGraph g, int colourCount) {
        // pomocou prehladavania s navratom zisti, ci je graf g colourCount-ofarbitelny
        int vertexCount = g.getVertexCount();
        int[] colors = new int[vertexCount];
        Arrays.fill(colors, -1);
        return hasColouringBacktrack(g, colors, 0, colourCount);
    }

    private static boolean getColouringBacktrack(UndirectedGraph g, int[] colors, int vertex, int colorCount, Map<Integer, Integer> colouring) {
        if (vertex == g.getVertexCount()) {
            for (int i = 0; i < g.getVertexCount(); i++) {
                colouring.put(i, colors[i]);
            }
            return true;
        }
        for (int i = 0; i < colorCount; i++) {
            boolean safe = true;
            for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
                if (colors[neighbor] == i) {
                    safe = false;
                    break;
                }
            }
            if (safe) {
                colors[vertex] = i;
                if (getColouringBacktrack(g, colors, vertex +1, colorCount, colouring)) {
                    return true;
                }
                colors[vertex] = -1;
            }
        }
        return false;
    }

    public static Map<Integer, Integer> getColouring(UndirectedGraph g, int colourCount) {
        // ak existuje, najde lubovolne spomedzi colourCount-farbeni grafu g (prehladavanim s navratom)
        int vertexCount = g.getVertexCount();
        int[] colors = new int[vertexCount];
        Arrays.fill(colors, -1);
        Map<Integer, Integer> colouring = new HashMap<>();

        if (getColouringBacktrack(g, colors, 0, colourCount, colouring)) {
            return colouring;
        } else {
            return null;
        }
    }

    private static void maximumIndependentSetBacktrack(UndirectedGraph g, int vertex, Set<Integer> currentSet, Set<Integer> maxIndependentSet) {
        if (vertex == g.getVertexCount()){
            if (currentSet.size() > maxIndependentSet.size()) {
                maxIndependentSet.clear();
                maxIndependentSet.addAll(currentSet);
            }
            return;
        }
        boolean canAdd = true;
        for (int neighbor : g.outgoingEdgesDestinations(vertex)) {
            if (currentSet.contains(neighbor)) {
                canAdd = false;
                break;
            }
        }
        if(canAdd) {
            currentSet.add(vertex);
            maximumIndependentSetBacktrack(g, vertex+1, currentSet, maxIndependentSet);
            currentSet.remove(vertex);
        }
        maximumIndependentSetBacktrack(g, vertex +1, currentSet, maxIndependentSet);
    }

    public static Set<Integer> maximumIndependentSet(UndirectedGraph g) {
        // pomocou prehladavania s navratom najde najvacsiu nezavislu mnozinu vrcholov v grafe g (podobne ako hladanie najvacsej kliky z prednasky)
        Set<Integer> maxIndependentSet = new HashSet<>();
        Set<Integer> currentSet = new HashSet<>();

        maximumIndependentSetBacktrack(g, 0, currentSet, maxIndependentSet);
        return maxIndependentSet;
    }//jeden test neprechadza = alebo mozno mame viac vysledkov idk

    public static Set<Integer> maximumVertexCovering(UndirectedGraph g) {
        // pomocou prehladavania s navratom najde najvacsie vrcholove pokrytie grafu g
        Set<Integer> independentSet = maximumIndependentSet(g);
        Set<Integer> vertexCover = new HashSet<>();

        for (int i = 0; i < g.getVertexCount(); i++) {
            if (!independentSet.contains(i)) {
                vertexCover.add(i);
            }
        }

        return vertexCover;
    }



    public static double cheapestWeightedPathValue(WeightedDirectedGraph g, int from, int to) {
        // najde cenu najlacnejsej cesty v grafe g z vrcholu from do vrcholu to za predpokladu, ze graf neobsahuje kruznice zapornej ceny (napr. Dijkstrov algoritmus)
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex.");
        }
        int n = g.getVertexCount();

        Map<Integer, Double> distance = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (int i = 0; i < n; i++) {
            distance.put(i, Double.POSITIVE_INFINITY);
        }
        distance.put(from, 0.0);
        pq.add(new AbstractMap.SimpleEntry<>(from, 0.0));

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Double> current = pq.poll();
            int u = current.getKey();
            double d = current.getValue();

            if (u == to) return d;
            if (d > distance.get(u)) continue;
            for (WeightedDirectedEdge edge : g.outgoingWeightedDirectedEdges(u)) {
                int v = edge.getTo();
                double newDist = d + edge.getWeight();

                if (newDist < distance.get(v)) {
                    distance.put(v, newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(v, newDist));
                }
            }
        }
        return distance.get(to) == Double.POSITIVE_INFINITY ? -1.0 : distance.get(to);
    }

    public static List<Integer> cheapestWeightedPath(WeightedDirectedGraph g, int from, int to) {
        // najde najlacnejsiu cestu v grafe g z vrcholu from do vrcholu to za predpokladu, ze graf neobsahuje kruznice zapornej ceny (napr. Dijkstrov algoritmus)
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex");
        }

        int n = g.getVertexCount();
        Map<Integer, Double> distance = new HashMap<>();
        Map<Integer, Integer> predecessor = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (int i = 0; i < n; i++) {
            distance.put(i, Double.POSITIVE_INFINITY);
        }
        distance.put(from, 0.0);
        pq.add(new AbstractMap.SimpleEntry<>(from, 0.0));

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Double> current = pq.poll();
            int u = current.getKey();
            double d = current.getValue();

            if (u == to) break;

            if (d > distance.get(u)) continue;

            for (WeightedDirectedEdge edge : g.outgoingWeightedDirectedEdges(u)) {
                int v = edge.getTo();
                double newDist = d + edge.getWeight();

                if (newDist < distance.get(v)) {
                    distance.put(v, newDist);
                    predecessor.put(v, u);
                    pq.add(new AbstractMap.SimpleEntry<>(v, newDist));
                }
            }
        }
        if (!predecessor.containsKey(to)) {
            return Collections.emptyList();
        }
        List<Integer> path = new ArrayList<>();
        for (int i = to; i != from; i = predecessor.get(i)) {
            path.add(i);
        }
        path.add(from);
        Collections.reverse(path);
        return path;
    }

    private static void costliestWeightedPathBacktrack (WeightedDirectedGraph g, int u, int to , double currentCost, double[] maxCost, List<Integer> currentPath, List<Integer> bestPath) {
        if (u == to) {
            if (currentCost > maxCost[0]) {
                maxCost[0] = currentCost;
                bestPath.clear();
                bestPath.addAll(currentPath);
            }
            return;
        }
        for (WeightedDirectedEdge edge : g.outgoingWeightedDirectedEdges(u)) {
            int v = edge.getTo();
            if (!currentPath.contains(v)) {
                currentPath.add(v);
                costliestWeightedPathBacktrack(g, v, to, currentCost + edge.getWeight(), maxCost, currentPath, bestPath);
                currentPath.removeLast();
            }
        }
    }

    public static List<Integer> costliestWeightedPath(WeightedDirectedGraph g, int from, int to) {
        // najde najdrahsiu cestu v grafe g z vrcholu from do vrcholu to (prehladavanie s navratom)
        if (!g.hasVertex(from) || !g.hasVertex(to)) {
            throw new IllegalArgumentException("Nonexistent vertex");
        }
        List<Integer> bestPath = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        double[] maxCost = {Double.NEGATIVE_INFINITY};

        currentPath.add(from);
        costliestWeightedPathBacktrack(g, from, to, 0, maxCost, currentPath, bestPath);

        return bestPath.isEmpty() ? Collections.emptyList() : bestPath;
    }

}

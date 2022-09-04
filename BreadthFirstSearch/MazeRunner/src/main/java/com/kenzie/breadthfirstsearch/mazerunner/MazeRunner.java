package com.kenzie.breadthfirstsearch.mazerunner;

import com.kenzie.breadthfirstsearch.mazerunner.model.MazePattern;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazeSpace;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Node;
import com.kenzie.breadthfirstsearch.mazerunner.utils.MazeGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static com.kenzie.breadthfirstsearch.mazerunner.SampleMazes.MAZE_ONE_EXIT;

/**
 * Class for running through mazes.
 */
public class MazeRunner {

    /**
     * Private constructor, as all methods are static.
     */
    private MazeRunner() {}

    /**
     * Utility main method, to run MazeRunner methods without adding tests.
     *
     * @param args - Method arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println(MazeRunner.findClosestExit(MAZE_ONE_EXIT));
    }

    /**
     * Finds the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
     */
    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {

        Queue<List<Node<MazeSpace>>> toVisit = new LinkedList<>();
        Set<Node<MazeSpace>> visited = new HashSet<>();

        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        entrance.ifPresent(entranceSpace -> toVisit.add(Collections.singletonList(entranceSpace)));

        while(!toVisit.isEmpty()) {
            List<Node<MazeSpace>> currentPath = toVisit.poll();
            Node<MazeSpace> currentSpace = currentPath.get(currentPath.size() - 1);

            if(currentSpace.getValue().isExit()) {
                return Optional.of(currentSpace.getValue());
            }

            visited.add(currentSpace);

            toVisit.addAll(currentSpace.getNeighbors()
                    .stream()
                    .filter(neighbor -> !visited.contains(neighbor))
                    .map(neighbor -> addToPath(currentPath, neighbor))
                    .collect(Collectors.toList()));
        }

        return Optional.empty();
    }

    /**
     * Finds the path to the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
     */
    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
        List<MazeSpace> answer = new ArrayList<>();
        Queue<List<Node<MazeSpace>>> toVisit = new LinkedList<>();
        Set<Node<MazeSpace>> visited = new HashSet<>();

        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        entrance.ifPresent(entranceSpace -> toVisit.add(Collections.singletonList(entranceSpace)));

        while(!toVisit.isEmpty()) {
            List<Node<MazeSpace>> currentPath = toVisit.poll();
            Node<MazeSpace> currentSpace = currentPath.get(currentPath.size() - 1);

            if(currentSpace.getValue().isExit()) {

                return currentPath;
            }

            visited.add(currentSpace);

            toVisit.addAll(currentSpace.getNeighbors()
                    .stream()
                    .filter(neighbor -> !visited.contains(neighbor))
                    .map(neighbor -> addToPath(currentPath, neighbor))
                    .collect(Collectors.toList()));
        }

//        return Collections.emptyList();

//        return Optional.empty();

        return Collections.emptyList();
    }

//    public static List<MazeSpace> bfs(MazePattern pattern) {
//        Queue<List<Node<MazeSpace>>> toVisit = new LinkedList<>();
//        Set<Node<MazeSpace>> visited = new HashSet<>();
//
//        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
//
//        entrance.ifPresent(entranceSpace -> toVisit.add(Collections.singletonList(entranceSpace)));
//
//        while(!toVisit.isEmpty()) {
//            List<Node<MazeSpace>> currentPath = toVisit.poll();
//            Node<MazeSpace> currentSpace = currentPath.get(currentPath.size() - 1);
//
//            if(currentSpace.getValue().isExit()) {
//                return
//            }
//
//            visited.add(currentSpace);
//
//            toVisit.addAll(currentSpace.getNeighbors()
//                    .stream()
//                    .filter(neighbor -> !visited.contains(neighbor))
//                    .map(neighbor -> addToPath(currentPath, neighbor))
//                    .collect(Collectors.toList()));
//        }
//
//        return Collections.emptyList();
//    }

    private static List<Node<MazeSpace>> addToPath(List<Node<MazeSpace>> currentPath, Node<MazeSpace> newSpace) {
        List<Node<MazeSpace>> newPath = new ArrayList<>(currentPath);
        newPath.add(newSpace);
        return newPath;
    }

}

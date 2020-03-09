import java.util.Arrays;
        import java.util.HashSet;
        import java.util.Set;

public class Solution {
    private static int getShorterFromToTime(int[][] times, int from, int to) {
        int leastTime = times[from][to];
        for (int otherTo = 0; otherTo < times[0].length; ++otherTo) {
            if (otherTo == to)
                continue;
            int otherTime = times[from][otherTo];
            otherTime += times[otherTo][to];
            if (otherTime < leastTime)
                leastTime = otherTime;
        }
        return leastTime;
    }

    private static int[][] getShorterTimes(int[][] times) {
        for (int from = 0; from < times.length; ++from) {
            for (int to = 0; to < times[0].length; ++to) {
                times[from][to] = getShorterFromToTime(times, from, to);
            }
        }
        return times;
    }

    private static int[][] getShortestTimes(int[][] times) {
        for (int bunny = 0; bunny < times.length - 2; bunny++) {
            times = getShorterTimes(times);
        }

        return times;
    }
    private static boolean canLoopInfinite(int[][] times) {
        for (int element = 0; element < times.length; ++element) {
            if (times[element][element] < 0) {
                return true;
            }
        }

        return false;
    }
    private static int[] getBunnyPath(int selectedBunnyCount, int[] bunnies, int[][] times, int timeLeft) {
        int totalBunnyCount = bunnies.length;

        for (int i = 0; i < Math.pow(totalBunnyCount, selectedBunnyCount); i++) {
            int[] permutation = new int[selectedBunnyCount];
            Set<Integer> values = new HashSet<>();
            boolean alreadyContainsDuplicate = false;
            for (int j = 0; j < selectedBunnyCount; j++) {
                int item = (int) Math.floor((double) (i % (int) Math.pow(totalBunnyCount, j + 1)) / (int) Math.pow(totalBunnyCount, j));
                permutation[selectedBunnyCount - 1 - j] = bunnies[item];
                if (values.contains(bunnies[item])) {
                    alreadyContainsDuplicate = true;
                    break;
                }
                values.add(bunnies[item]);
            }

            if (alreadyContainsDuplicate)
                continue;

            if (hasASolution(permutation, selectedBunnyCount, times, timeLeft)) {
                return permutation;
            }
        }
        return new int[]{};
    }

    private static int[] getBunniesThatCanBeSaved(int[] bunnies, int bunnyCount) {
        int[] savedBunnies = Arrays.copyOfRange(bunnies, 0, bunnyCount);

        Arrays.sort(savedBunnies);
        return savedBunnies;
    }
    private static boolean hasASolution(int[] bunnyOrder, int bunnyCount, int[][] times, int timeLeft) {
        timeLeft -= times[0][bunnyOrder[0] + 1];
        for (int index = 0; index < bunnyCount - 1; ++index) {
            timeLeft -= times[bunnyOrder[index] + 1][bunnyOrder[index+1] + 1];
        }
        timeLeft -= times[bunnyOrder[bunnyCount - 1] + 1][times.length - 1];
        return timeLeft >= 0;
    }

    public static int[] solution(int[][] times, int time_limit) {
        int totalBunnies = times.length - 2;
        int[] bunniesThatCanBeSaved = new int[totalBunnies];
        for (int bunny = 0; bunny < totalBunnies;bunny++) {
            bunniesThatCanBeSaved[bunny] = bunny;
        }
        times = getShortestTimes(times);
        if (canLoopInfinite(times)) {
            return bunniesThatCanBeSaved;
        }
        for (int i=totalBunnies; totalBunnies > 0; totalBunnies--) {
            int[] bunnyPath = getBunnyPath(totalBunnies, bunniesThatCanBeSaved, times, time_limit);
            if (bunnyPath.length != 0) {
                bunniesThatCanBeSaved = bunnyPath;
                break;
            }
        }
        return getBunniesThatCanBeSaved(bunniesThatCanBeSaved, totalBunnies);
    }
}
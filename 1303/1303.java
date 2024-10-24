import java.util.Scanner;

import ed.list.ArrayList;

class Team implements Comparable<Team> {
    int p;
    float avg;
    int scored;
    int taken;
    int id;

    public Team(int id) {
        p = 0;
        avg = 0;
        scored = 0;
        taken = 0;
        this.id = id;
    }

    public void handleMatchResult(int scored, int taken) {
        this.scored += scored;
        this.taken += taken;
        avg = this.taken == 0 ? this.scored : (float) this.scored / this.taken;

        p += scored > taken ? 2 : 1;

    }

    @Override
    public int compareTo(Team t) {
        if (this.p != t.p) return this.p - t.p;
        if (this.avg != t.avg) return this.avg > t.avg ? 1 : -1;
        if (this.scored != t.scored) return this.scored - t.scored;

        return t.id - this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        return this.id == ((Team) obj).id;
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int c = 1;
        int n = in.nextInt();
        while (in.hasNextInt()) {
            ArrayList<Team> teams = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                teams.addLast(new Team(i + 1));
            }

            for (int i = 0; i < ((n * (n - 1)) / 2); i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();
                int w = in.nextInt();

                teams.get(x - 1).handleMatchResult(y, w);
                teams.get(z - 1).handleMatchResult(w, y);
            }

            ArrayList<Team> standings = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                Team t = teams.get(i);
                standings.addOrdered(t);
            }

            System.out.printf("Instancia %d\n", c);
            System.out.println(standings);

            n = in.nextInt();
            if (n == 0) break;

            System.out.println();

            c++;
        }
    }
}
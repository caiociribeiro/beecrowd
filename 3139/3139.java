import ed.queue.Queue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Queue<Integer> q = new Queue<>(30);
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            int f = in.nextInt();
            q.enqueue(f);
            sum += f;
        }

        // teto da media dos ultimos 30 dias
        double avg = Math.ceil((double) sum / 30);

        // inicializa contador de dias
        int d = 1;

        // enquanto nao atingir o numero de seguidores
        while (n < m) {
            // incrementa o numero de seguidores com a media dos ultimos 30 dias
            n += (int) avg;

            // se passou o numero de seguidores, pode parar a execucao
            if (n >= m) {
                break;
            }

            d += 1;

            // retira o dia mais distante da fila e tira da soma total
            sum -= q.dequeue();

            // adiciona os seguidores do ultimo dia a soma e a fila
            sum += (int) avg;
            q.enqueue((int) avg);

            // nova media
            avg = Math.ceil((double) sum / 30);
        }

        System.out.println(d);
    }
}

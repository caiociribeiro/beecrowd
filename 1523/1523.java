import ed.stack.NodeStack;

import java.util.Scanner;

public class Main {

    static class Carro {
        int entrada;
        int saida;

        Carro(int entrada, int saida) {
            this.entrada = entrada;
            this.saida = saida;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();

            if (n == 0 && k == 0) break;

            NodeStack<Carro> s = new NodeStack<>();

            boolean isValid = true;

            int h = 0;

            for (int i = 0; i < n; i++) {
                int entrada = in.nextInt();
                int saida = in.nextInt();

                h = entrada;

                // carros podem estar saindo (ou ja terem saido) no momento que o atual esta entrando
                while (!s.isEmpty() && h >= s.peek().saida) {
                    s.pop();
                }

                // antes do carro entrar, verifica se o carro na frente deveria sair antes do atual
                // ou se o estacionamento ja esta cheio
                // se sim, nao funciona
                if ((!s.isEmpty() && saida > s.peek().saida) || s.size() == k) {
                    isValid = false;
                } else {
                    s.push(new Carro(entrada, saida));
                }
            }

            System.out.println(isValid ? "Sim" : "Nao");
        }
    }
}

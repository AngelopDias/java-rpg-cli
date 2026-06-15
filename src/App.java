import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Personagem{
    String nome;
    int vida, ataquemax, ataquemin;

    public Personagem(String nome, int vida, int ataquemax, int ataquemin){
        this.nome = nome;
        this.vida = vida;
        this.ataquemax = ataquemax;
        this.ataquemin = ataquemin;
    }

    public void mostrarStatus(){
        System.out.println(""
        + "O nome do personagem é: " + nome
        + "\nA vida do personagem é: " + vida
        + "\nO ataque máximo do personagem é: " + ataquemax
        + "\nO ataque mínimo do personagem é: " + ataquemin
        );
    }
    
    public int cura(){

        int vidaCurada = ThreadLocalRandom.current().nextInt(15, 26);

        int vidaNova = vida + vidaCurada;

        return vidaNova;
    }

    public int curar(int vidaNova){
        vida = vidaNova;

        return vida;
    }

    public int atacar(){
        Random random = new Random();

        int min = ataquemin;
        int max = ataquemax;

        int numeroGerado = min + random.nextInt((max - min) + 1);

        return numeroGerado;
    }

    public int receberDano(int dano){
        vida -= dano;

        return vida;
    }

    public String getNome(){
        return nome;
    }

    public int getVida(){
        return vida;
    }
}

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        String nome;
        int vida, danomin, danomax;

        System.out.println(""
            + "Bem-vindo!\n"
            + "Este programa é uma CLI que utiliza Java e funciona como um\n"
            + "RPG simples. Você deverá informar as seguintes informações de 2\n"
            + "personagens: nome, vida, ataque máximo e ataque mínimo.\n"
            + "Os personagens poderão se atacar e usar poções de cura.\n"
            + "Cada personagem possui 3 poções, que curam entre 15 e 25 de vida.\n"
            + "Bom jogo..."
        );

        Personagem per1 = null;
        Personagem per2 = null;

        int continuar = 1;

        while(continuar != 0){

            System.out.println("Informe o nome do 1° personagem: ");
                nome = sc.nextLine();

            System.out.println("Informe a vida do 1° personagem: ");
                vida = sc.nextInt();

            do{

                System.out.println("Informe o dano mínimo do 1° personagem: ");
                    danomin = sc.nextInt();

                System.out.println("Informe o dano máximo do 1° personagem: ");
                    danomax = sc.nextInt();

                if(danomin >= danomax){
                    System.out.println(""
                        + "O dano mínimo não deve ser maior ou igual ao dano máximo!"
                    );
                }

            }while(danomin >= danomax);

            per1 = new Personagem(nome, vida, danomax, danomin);

            per1.mostrarStatus();

            System.out.println("Deseja modificar o 1° personagem? (1 para sim e 0 para não): ");
                continuar = sc.nextInt();
                sc.nextLine();
        }

        continuar = 1;

        while(continuar != 0){

            System.out.println("Informe o nome do 2° personagem: ");
                nome = sc.nextLine();

            System.out.println("Informe a vida do 2° personagem: ");
                vida = sc.nextInt();

            do{

                System.out.println("Informe o dano mínimo do 2° personagem: ");
                    danomin = sc.nextInt();

                System.out.println("Informe o dano máximo do 2° personagem: ");
                    danomax = sc.nextInt();

                if(danomin >= danomax){
                    System.out.println(""
                        + "O dano mínimo não deve ser maior ou igual ao dano máximo!"
                    );
                }

            }while(danomin >= danomax);

            per2 = new Personagem(nome, vida, danomax, danomin);

            per2.mostrarStatus();

            System.out.println("Deseja modificar o 2° personagem? (1 para sim e 0 para não): ");
                continuar = sc.nextInt();
                sc.nextLine();
        }

        String ganhador = "sg";

        int op = 0;

        int vidInicialp1 = per1.getVida();
        int vidInicialp2 = per2.getVida();

        int quantPoc1 = 3;
        int quantPoc2 = 3;

        System.out.println(""
            + "Será escolhido aleatoriamente entre o 1° e o 2° personagem\n"
            + "para atacar ou usar poção!"
        );

        while(ganhador.equals("sg")){

            int persAleatorio = new Random().nextInt(2);

            if(persAleatorio == 0){

                System.out.println("\nTurno de " + per1.getNome());
                System.out.println("Curar (1) ou atacar (2): ");
                    op = sc.nextInt();

                if(op == 2){

                    int dano = per1.atacar();

                    System.out.println("Dano causado pelo 1° personagem: " + dano);

                    int vidaNova = per2.receberDano(dano);

                    System.out.println("Vida do 2° personagem: " + vidaNova);

                    if(vidaNova <= 0){

                        int mortes = Math.abs(vidaNova) + 1;

                        System.out.println(per2.getNome() + " morreu " + mortes + " vez(es)!");

                        ganhador = per1.getNome();

                        System.out.println(""
                            + "Ganhador: " + ganhador
                        );

                        sc.close();
                        return;
                    }
                }

                else if(op == 1){

                    if(quantPoc1 == 0){
                        System.out.println("Você não tem mais poções! Perdeu a vez...");
                    }

                    else{

                        quantPoc1 -= 1;

                        System.out.println("Restam " + quantPoc1 + " poções de 3!");

                        if(per1.getVida() < vidInicialp1){

                            int vidaAntes = per1.getVida();

                            int vidaNova = per1.cura();

                            if(vidaNova > vidInicialp1){

                                int vidaObtida = vidInicialp1 - vidaAntes;

                                per1.vida = vidInicialp1;

                                System.out.println("Vida obtida: " + vidaObtida);
                            }

                            else{

                                per1.curar(vidaNova);

                                int vidaObtida = vidaNova - vidaAntes;

                                System.out.println("Vida obtida: " + vidaObtida);
                            }
                        }

                        else{
                            System.out.println("A vida já está no máximo! Perdeu a vez...");
                        }
                    }
                }
            }

            else if(persAleatorio == 1){

                System.out.println("\nTurno de " + per2.getNome());
                System.out.println("Curar (1) ou atacar (2): ");
                    op = sc.nextInt();

                if(op == 2){

                    int dano = per2.atacar();

                    System.out.println("Dano causado pelo 2° personagem: " + dano);

                    int vidaNova = per1.receberDano(dano);

                    System.out.println("Vida do 1° personagem: " + vidaNova);

                    if(vidaNova <= 0){

                        int mortes = Math.abs(vidaNova) + 1;

                        System.out.println(per1.getNome() + " morreu " + mortes + " vez(es)!");

                        ganhador = per2.getNome();

                        System.out.println(""
                            + "Ganhador: " + ganhador
                        );

                        sc.close();
                        return;
                    }
                }

                else if(op == 1){

                    if(quantPoc2 == 0){
                        System.out.println("Você não tem mais poções! Perdeu a vez...");
                    }

                    else{

                        quantPoc2 -= 1;

                        System.out.println("Restam " + quantPoc2 + " poções de 3!");

                        if(per2.getVida() < vidInicialp2){

                            int vidaAntes = per2.getVida();

                            int vidaNova = per2.cura();

                            if(vidaNova > vidInicialp2){

                                int vidaObtida = vidInicialp2 - vidaAntes;

                                per2.vida = vidInicialp2;

                                System.out.println("Vida obtida: " + vidaObtida);
                            }

                            else{

                                per2.curar(vidaNova);

                                int vidaObtida = vidaNova - vidaAntes;

                                System.out.println("Vida obtida: " + vidaObtida);
                            }
                        }

                        else{
                            System.out.println("A vida já está no máximo! Perdeu a vez...");
                        }
                    }
                }
            }
        }

        sc.close();
    }
}
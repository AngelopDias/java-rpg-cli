    import java.util.*;

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
                + "O nome do personagem e: " + nome
                + "\nA vida do personagem e: " + vida
                + "\nO ataque maximo do personagem e: " + ataquemax
                + "\nO ataque minimo do personagem e: " + ataquemin
                );
            }
            
            public int cura(){
                Random random = new Random();

                int vidaCurada = random.nextInt(10) + 1;

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
                int danoCausado;
                
                int numeroGerado = min + random.nextInt((max - min) + 1);

                danoCausado = numeroGerado;

                return danoCausado;
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
                + "Bem vindo!\n"
                + "Este programa e uma CLI que utiliza Java, que funcionara como um\n"
                + "RPG simples, voce devera informa as seguintes informacoes de 2\n"
                + "personagens: nome, vida, ataque maximo e ataque minimo. Que poderao\n"
                + "se atacar! Bom jogo..."
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

                    System.out.println("Informe o dano minimo do 1° personagem: ");
                        danomin = sc.nextInt();

                    System.out.println("Informe o dano maximo do 1° personagem: ");
                        danomax = sc.nextInt();

                    if(danomin >= danomax){
                        System.out.println(""
                            + "O dano minimo nao deve ser maior ou igual que o dano maximo"
                        );
                    }

                }while(danomin >= danomax);

                per1 = new Personagem(nome, vida, danomax, danomin);

                per1.mostrarStatus();
                
                System.out.println("Deseja modificar o 1° personagem? (1 para sim e 0 para nao): ");
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
                    System.out.println("Informe o dano minimo do 2° personagem: ");
                        danomin = sc.nextInt();

                    System.out.println("Informe o dano maximo do 2° personagem: ");
                        danomax = sc.nextInt();

                    if(danomin >= danomax){
                        System.out.println(""
                            + "O dano minimo nao deve ser maior ou igual que o dano maximo"
                        );
                    }
                    
                }while(danomin >= danomax);

                per2 = new Personagem(nome, vida, danomax, danomin);

                per2.mostrarStatus();
                
                System.out.println("Deseja modificar o 2° personagem? (1 para sim e 0 para nao): ");
                    continuar = sc.nextInt();
                    sc.nextLine();
            }


            String ganhador = "sg";
            int op = 0;
            
            System.out.println(""
                + "Sera escolhido de forma aleatoria entre o 1° e 2° personagem para curar ou atacar:"
            );

            int vidInicialp1 = per1.getVida();
            int vidInicialp2 = per2.getVida(); 

            
            while(ganhador.equals("sg")){
                int persAleatorio = new Random().nextInt(2);
                if(persAleatorio == 0){
                    System.out.println("O " + per1.getNome() + " ira se curar (1) ou atacar (2): ");
                        op = sc.nextInt();
                    
                    if(op == 2){
                        int dano = per1.atacar();

                        System.out.println("Dano causado pelo 1° personagem: " + dano);

                        int vidaNova = per2.receberDano(dano);

                        System.out.println("Vida do 2° personagem: " + vidaNova);

                        if(vidaNova <= 0){
                            ganhador = per1.getNome();
                            System.out.println(""
                                + "Ganhador: " + ganhador
                            );
                            sc.close();
                            return;
                        }
                    }
                    else if(op == 1){
                        if(per1.getVida() < vidInicialp1){

                            int vidaNova = per1.cura();

                            if(vidaNova > vidInicialp1){
                                System.out.println("A vida curada e maior que a vida inicial! Perdeu a vez...");
                            }
                            else{
                                per1.curar(vidaNova);
                                System.out.println("Sua vida curada e: " + vidaNova);
                            }
                        }
                        else{
                            System.out.println("A vida ja esta no maximo! Perdeu a vez...");
                        }
                    }
                }

                else if(persAleatorio == 1){
                    System.out.println("O " + per2.getNome() + " ira se curar (1) ou atacar (2): ");
                        op = sc.nextInt();
                    
                    if(op == 2){
                        int dano = per2.atacar();

                        System.out.println("Dano causado pelo 2° personagem: " + dano);

                        int vidaNova = per1.receberDano(dano);

                        System.out.println("Vida do 1° personagem: " + vidaNova);

                        if(vidaNova <= 0){
                            ganhador = per2.getNome();
                            System.out.println(""
                                + "Ganhador: " + ganhador
                            );
                            sc.close();
                            return;
                        }
                    }
                    else if(op == 1){
                        if(per2.getVida() < vidInicialp2){

                            int vidaNova = per2.cura();

                            if(vidaNova > vidInicialp2){
                                System.out.println("A vida curada e maior que a vida inicial! Perdeu a vez...");
                            }
                            else{
                                per2.curar(vidaNova);
                                System.out.println("Sua vida curada e: " + vidaNova);
                            }
                        }
                        else{
                            System.out.println("A vida ja esta no maximo! Perdeu a vez...");
                        }
                    }
                }
            }
            

            sc.close();
        }
    }
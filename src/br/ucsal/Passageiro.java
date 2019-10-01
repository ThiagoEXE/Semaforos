package br.ucsal;

import java.util.concurrent.Semaphore;

public class Passageiro extends Thread {

    //private  String nome;
    public Semaphore passageiro = new Semaphore(5);
    public Semaphore carrinho = new Semaphore(0);
    public Semaphore andando = new Semaphore(0);
    public Semaphore mutex = new Semaphore(1);
    int Npass = 0;


    @Override
    public void run() {

        while(true) {
            try {
                passageiro.acquire();
                entraNoCarrinho();  /* vários passageiros podem entrar “ao mesmo tempo” */
                mutex.acquire();
                Npass++;
                if (Npass == 5) {   /* carrinho lotou */
                    carrinho.release(); /* autoriza carrinho a andar */
                    andando.acquire(); /* espera carrinho parar */
                    mutex.release();
                } else {
                    mutex.release();
                    andando.acquire();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void entraNoCarrinho() {
        System.out.println("Passageiros entrando no carrinho");
    }

}

package br.ucsal;

public class Carrinho extends Thread{

    Passageiro ps = new Passageiro();

    @Override
    public void run() {
        while (true) {
            try {
                ps.carrinho.acquire();
                passeia();
                ps.Npass  = 0;
                for (int i = 0; i < 5; i++) {
                    ps.andando.release();
                    ps.passageiro.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void passeia(){
        System.out.println("PASSEANDO!");
    }
}

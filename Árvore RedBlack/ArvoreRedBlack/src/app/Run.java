package app;

import arvoreRedBlack.ArvoreRedBlack;
import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException {

        ArvoreRedBlack arvore = new ArvoreRedBlack();

        String caminho = "ArvoreRedBlack/src/entrada/dadosRedBlack.txt";

        arvore = arvore.carregarDadosDeArquivo(caminho);

        //arvore.inserirArvoreRedBlack(50);
        //arvore.inserirArvoreRedBlack(30);
        //arvore.inserirArvoreRedBlack(60);
        //arvore.inserirArvoreRedBlack(20);
        //arvore.inserirArvoreRedBlack(35);
        
        //arvore.imprimirArvoreRedBlack();
        
        //System.out.println("\n\n\n");
        //arvore.removerElementoArvoreRedBlack(35);

        System.out.println("kk");

        //arvore.removerArvore(100);
        
        arvore.imprimirArvoreRedBlack();

        System.out.println("kk");

    }
}

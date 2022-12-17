package arvoreRedBlack;

import java.io.IOException;
import java.util.List;
import util.ManipuladorArquivos;

public class ArvoreRedBlack {

    public static No nil = new No(0, 'B');

    private No raiz;

    public ArvoreRedBlack() {
        this.raiz = ArvoreRedBlack.nil;
    }

    public No getRaiz() {
        return raiz;
    }

    //O metodo abaixo retorna verdadeiro (true), caso a árvore não possua nenhum elemento
    private boolean arvoreVazia() {
        return this.raiz == ArvoreRedBlack.nil;
    }

    //O metodo abaixo executa o metodo da impressão da árvore sem que seja necessario atribuir parametros
    public void imprimirArvoreRedBlack() {

        if (arvoreVazia()) {
            System.out.println("A árvore está vazia [!]");
        } else {
            imprimir(this.raiz, 1);
        }

    }

    //Metodo de impressão da Árvore
    private void imprimir(No noAtual, int nivel) {

        if (noAtual != ArvoreRedBlack.nil) {
            imprimir(noAtual.getDireita(), nivel + 1);
            System.out.println();
            System.out.println();

            for (int i = 0; i <= nivel; i++) {
                System.out.print("      ");
            }

            System.out.print(noAtual.getElemento() + "(" + noAtual.getCor() + ")");
            imprimir(noAtual.getEsquerda(), nivel + 1);
        }
    }

    //O metodo abaixo executa o metodo da Busca na árvore sem que seja necessario atribuir a raiz como parametro
    public void buscarRedBlack(int elemento) {

        No elementoBusca = buscarElemento(this.raiz, elemento);

        if (elementoBusca != ArvoreRedBlack.nil) {
            System.out.println("Elemento Encontrado !");
        }
    }

    //O metodo abaixo realiza buscas na Árvore
    private No buscarElemento(No noAtual, int elemento) {

        if (arvoreVazia()) {
            System.out.println("A árvore está vazia [!]");
            return null;
        }
        if (noAtual == ArvoreRedBlack.nil) {
            System.out.println("Elemento não encontrado [!]");
            return null;
        }
        if (noAtual.getElemento() == elemento) {
            return noAtual;
        } else {
            if (elemento > noAtual.getElemento()) {
                return buscarElemento(noAtual.getDireita(), elemento);
            } else {
                return buscarElemento(noAtual.getEsquerda(), elemento);
            }
        }
    }

    //O metodo abaixo executa o metodo da inserção na árvore sem que seja necessario atribuir a raiz como parametro
    public void inserirArvoreRedBlack(int elemento) {
        inserirArvore(this.raiz, elemento);
    }

    /*
    O metodo de inserção na Arvore RED-BLACK é semelhante a inserção em uma arvore binaria de busca comum,
    porem devemos verificar a cada inserção se as propriedades da árvore rubro negra não foram violadas
    O metodo abaixo inseri valores na arvore:
     */
    private void inserirArvore(No noAtual, int elemento) {

        if (arvoreVazia()) { // Caso a árvore esteja vazia, a raiz recebe o novo no com o elemento que deseja inserir
            No novoNo = new No(elemento, 'R');
            novoNo.setCor('B'); // Todo nó por definição é RED, porém a raiz da árvore é sempre BLACK, por isso precisamos mudar a cor do nó raiz para BLACK
            this.raiz = novoNo;
        } else {

            if (elemento >= noAtual.getElemento()) { // Caso o elemento que desejamos inserir é maior que o nó Atual que estamos comparando é necessario percorrer o proximo nó a direita
                if (noAtual.getDireita() == ArvoreRedBlack.nil) { // Caso a direita estaja vazia, inserimos o novo nó com o elemento desejado a direita do nó atual
                    No novoNo = new No(elemento, 'R');
                    noAtual.setDireita(novoNo);
                    novoNo.setPai(noAtual);
                    balancearArvore(novoNo); // Metodo para balancear a árvore apos a inserção

                } else {
                    inserirArvore(noAtual.getDireita(), elemento); // Metodo recursivo para buscar proximo elemento a direita
                }
            } else if (elemento <= noAtual.getElemento()) { // Caso o elemento que desejamos inserir é maior que o nó Atual que estamos comparando é necessario percorrer o proximo nó a esquerda
                if (noAtual.getEsquerda() == ArvoreRedBlack.nil) {
                    No novoNo = new No(elemento, 'R');
                    noAtual.setEsquerda(novoNo);
                    novoNo.setPai(noAtual);
                    balancearArvore(novoNo); // Metodo para balancear a árvore apos a inserção

                } else {
                    inserirArvore(noAtual.getEsquerda(), elemento); // Metodo recursivo para buscar proximo elemento a esquerda
                }
            }
        }

    }

    /*
    O metodo de remoção na Arvore RED-BLACK é semelhante a remoção em uma arvore binaria de busca comum,
    porem devemos verificar a cada remoção se as propriedades da árvore rubro negra não foram violadas
    O metodo abaixo remove valores na arvore:
     */
    public void removerArvore(int elemento) {

        No noAtual = buscarElemento(this.raiz, elemento);
        No noRemovido;
        No aux;

        if (noAtual != null && noAtual.getElemento() == elemento) {

            if (noAtual.getEsquerda() != ArvoreRedBlack.nil && noAtual.getDireita() == ArvoreRedBlack.nil) {
                noRemovido = noAtual.getEsquerda();
                substituir(noAtual, noAtual.getEsquerda());
            }
            if (noAtual.getEsquerda() == ArvoreRedBlack.nil && noAtual.getDireita() != ArvoreRedBlack.nil) {
                noRemovido = noAtual.getDireita();
                substituir(noAtual, noAtual.getDireita());
            } else {

                noRemovido = sucessor(noAtual);
                System.out.println(noRemovido.getElemento());

                noAtual.setElemento(noRemovido.getElemento());
                noAtual.setCor(noRemovido.getCor());

                noRemovido = noRemovido.getEsquerda();
                
            }

            //Metodo de balanceamento
            
            System.out.println(noRemovido.getElemento());
            System.out.println(noRemovido.getPai().getElemento());
            //balancearArvoreRemocao(noRemovido);
            
        }

    }

    //O metodo abaixo encontra o nó de maior valor em comparação com o nó qu desejamos excluir
    private No sucessor(No noAtual) {

        No noAux = noAtual.getEsquerda();

        if (noAux.getDireita() == ArvoreRedBlack.nil) {

            substituir(noAux, noAux.getEsquerda());

            return noAux;
            
        }else {

            while (noAux.getDireita() != ArvoreRedBlack.nil) {
                noAux = noAux.getDireita();
            }

            substituir(noAux, noAux.getEsquerda());

            return noAux;
        }

    }

    //O metodo abaixo realiza o balanceamento de cores na remoção
    private void balancearArvoreRemocao(No noAtual) {
        
        No pai = noAtual.getPai();
        No tio;
        while (noAtual != this.raiz && noAtual.getCor() == 'B') {
            if (noAtual == pai.getEsquerda()) {
                tio = pai.getDireita();

                if (tio.getCor() == 'R') {
                    tio.setCor('B');
                    pai.setCor('R');
                    rotacaoEsquerda(pai);
                    tio = pai.getDireita();
                }
                if (tio.getEsquerda().getCor() == 'B' && tio.getDireita().getCor() == 'B') {
                    tio.setCor('R');
                    noAtual = pai;
                } else {
                    if (tio.getDireita().getCor() == 'B') {
                        tio.getEsquerda().setCor('B');
                        tio.setCor('R');
                        rotacaoDireita(tio);
                        tio = pai.getDireita();
                    }

                    tio.setCor(pai.getCor());
                    pai.setCor('B');
                    tio.getDireita().setCor('B');
                    rotacaoEsquerda(pai);
                    noAtual = this.raiz;
                }
            } else {
                tio = pai.getEsquerda();

                if (tio.getCor() == 'R') {
                    tio.setCor('B');
                    pai.setCor('R');
                    rotacaoDireita(pai);
                    tio = pai.getEsquerda();
                }
                if (tio.getEsquerda().getCor() == 'B' && tio.getDireita().getCor() == 'B') {
                    tio.setCor('R');
                    noAtual = pai;
                } else {
                    if (tio.getDireita().getCor() == 'B') {
                        tio.getDireita().setCor('B');
                        tio.setCor('R');
                        rotacaoEsquerda(tio);
                        tio = pai.getEsquerda();
                    }

                    tio.setCor(pai.getCor());
                    pai.setCor('B');
                    tio.getEsquerda().setCor('B');
                    rotacaoDireita(pai);
                    noAtual = this.raiz;
                }

            }
        }
        noAtual.setCor('B');
    }

    //O metodo abaixo executa o balanceamento de cores na inserção
    private void balancearArvore(No noAtual) {

        if (noAtual.getPai() != null && noAtual.getCor() == 'R' && noAtual.getPai().getCor() == 'R') {

            No tio = new No(0, 'R'); // O tio é o irmao do pai do no atual, ele está do lado contrario da arvore
            No avo = noAtual.getPai().getPai(); // O avo é o pai do pai do no atual
            No pai = noAtual.getPai();

            if (pai == avo.getEsquerda()) { // Vericando se o pai do nó atual é filho esquerdo
                tio = avo.getDireita();

                if (tio != ArvoreRedBlack.nil && tio.getCor() == 'R') { // Verificando se o tio é Rubro
                    pai.setCor('B');
                    tio.setCor('B');
                    avo.setCor('R');
                    balancearArvore(avo);
                } else {

                    if (noAtual == pai.getDireita()) {
                        rotacaoEsquerda(pai);
                        noAtual = noAtual.getEsquerda(); //Precisamos informar que o nó atual agora é aquele mais a esquerda, pois apos a rotação o no atual ocupa o lugar que era do seu pai
                    }

                    rotacaoDireita(avo); // Rotacionamos o avo para balancear a arvore
                    avo.setCor('R');
                    noAtual.getPai().setCor('B');
                    balancearArvore(pai);
                }

            }

            if (pai == avo.getDireita()) { // Verificando se o pai é filho direito
                tio = avo.getEsquerda();

                if (tio != ArvoreRedBlack.nil && tio.getCor() == 'R') {
                    pai.setCor('B');
                    tio.setCor('B');
                    avo.setCor('R');
                    balancearArvore(avo);
                } else {

                    if (noAtual == pai.getEsquerda()) {
                        rotacaoDireita(pai);
                        noAtual = noAtual.getDireita(); //Precisamos informar que o nó atual agora é aquele mais a direita, pois apos a rotação o no atual ocupa o lugar que era do seu pai
                    }

                    rotacaoEsquerda(avo);
                    avo.setCor('R');
                    noAtual.getPai().setCor('B');
                    balancearArvore(pai);

                }
            }

        }

        this.raiz.setCor('B');
    }

    //O metodo abaixo rotaciona o nó para a esquerda
    private void rotacaoEsquerda(No noAtual) {

        No noAux = noAtual.getDireita();
        No pai = noAtual.getPai();

        noAtual.setDireita(noAux.getEsquerda()); // A direita do nó Atual vai receber os elementos que estavam a esquerda do nó auxiliar
        noAux.setEsquerda(noAtual); // A esquerda do nó auxiliar ficara o nó atual

        if (pai == ArvoreRedBlack.nil) {
            this.raiz = noAux;
        } else if (pai.getDireita() == noAtual) {
            pai.setDireita(noAux);
        } else {
            pai.setEsquerda(noAux);
        }

        noAtual.setPai(noAux);
        noAux.setPai(pai);

    }

    //O metodo abaixo rotaciona o nó para a direita
    private void rotacaoDireita(No noAtual) {

        No noAux = noAtual.getEsquerda();
        No pai = noAtual.getPai();

        noAtual.setEsquerda(noAux.getDireita()); //A esquerda do nó Atual vai receber os elementos que estavam a direita  do nó auxiliar
        noAux.setDireita(noAtual); // A direita do nó Auxiliar ficara o nó Atual

        if (pai == ArvoreRedBlack.nil) {
            this.raiz = noAux;
        } else if (pai.getDireita() == noAtual) {
            pai.setDireita(noAux);
        } else {
            pai.setEsquerda(noAux);
        }

        noAtual.setPai(noAux);
        noAux.setPai(pai);
    }

    public ArvoreRedBlack carregarDadosDeArquivo(String caminho) throws IOException {

        ArvoreRedBlack arvore = new ArvoreRedBlack();

        ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
        List<String> dadosArquivo = manipuladorArquivos.lerArquivo(caminho);

        for (int i = 0; i < dadosArquivo.size(); i++) {

            String elementoInteiro = dadosArquivo.get(i);
            String elementoSeparado[] = elementoInteiro.split(";");

            arvore.inserirArvoreRedBlack(Integer.parseInt(elementoSeparado[0]));
        }

        return arvore;
    }

    public void substituir(No noAtual, No novoNo) {

        if (noAtual.getPai() == ArvoreRedBlack.nil) {
            this.raiz = novoNo;
        } else if (noAtual == noAtual.getPai().getEsquerda()) {
            noAtual.getPai().setEsquerda(novoNo);
        } else {
            noAtual.getPai().setDireita(novoNo);
        }

        novoNo.setPai(noAtual.getPai());

    }

}

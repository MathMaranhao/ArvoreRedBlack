package arvoreRedBlack;

public class No {

    private int elemento;
    private No esquerda;
    private No direita;
    private No pai;
    private char cor;
    
    
    public No(int elemento, char cor) {
        this.elemento = elemento;
        this.esquerda = ArvoreRedBlack.nil;
        this.direita = ArvoreRedBlack.nil;
        this.pai = ArvoreRedBlack.nil;
        this.cor = cor;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public char getCor() {
        return cor;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

}

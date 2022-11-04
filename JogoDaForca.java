/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogodaforca;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author aluno
 */
public class JogoDaForca {
    /**
     * @param args the command line arguments
     */
    
    static char[][] ilustracao = 
    {
         //  0   1   2   3   4   5   6   7   8
     /*0*/ {'+','-','-','-','-','+',' ',' ',' '},
     /*1*/ {'|',' ',' ',' ',' ','|',' ',' ',' '},
     /*2*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*3*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*4*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*5*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*6*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*7*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*8*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
     /*9*/ {'|',' ',' ',' ',' ',' ',' ',' ',' '},
    };
    
    static String ConteudoSorteado;
    
    static Scanner leia = new Scanner(System.in);
    
    static int Jogadas = 0;
    
    static char[] LetrasErradas = new char[20];
    
    public static void main(String[] args) {
        // TODO code application logic here
        InicioDoJogo();
        EscolheTema();
        char VetorEscondido[] = DestrinchaPalavra();
        
        for(int i = 0; i < 10; i++){
            System.out.println("");
            ConstroiBoneco();
            char caracter = PedeJogada();
            ConfereJogadas(VetorEscondido, caracter);
            MostraVetor(VetorEscondido);
        }
    }
    
    public static void MostraVetor(char[] vetor){
        for(int i = 0; i < ConteudoSorteado.length(); i++){
            System.out.print(vetor[i] + " ");
        }
    }
    
    public static void InicioDoJogo(){
        System.out.println("!#*JOGO DA FORCA*#!");
        ConstroiBoneco();
        System.out.println("");
    }
    
    public static void ConfereJogadas(char[] vetor, char caracter){
        char[] palavra = ConteudoSorteado.toCharArray();
        for(int i = 0; i < palavra.length; i++){
            if(palavra[i] == caracter){
                vetor[i] = caracter;
            } else {
                Jogadas++;
                AtualizaForcaMostraErros(caracter);
            }
        }
    }
    
    public static void AtualizaForcaMostraErros(char letra){
        if(Jogadas == 1){ // coloca a cabeca
            ilustracao[2][5] = 'O';
        }
        else if(Jogadas == 2){ // coloca a primeira metade do corpo
            ilustracao[3][4] = '_';
            ilustracao[3][5] = '-';
            ilustracao[3][6] = '_';
            ilustracao[4][5] = '|';
        }
        else if(Jogadas == 3){ // segunda metade do corpo
            ilustracao[6][4] = '_';
            ilustracao[5][5] = '-';
            ilustracao[6][6] = '_';
            ilustracao[5][5] = '|';
        }
        else if(Jogadas == 4){ // braco esquerdo
            ilustracao[4][3] = '/';
            ilustracao[5][2] = '/';
        }
        else if(Jogadas == 5){ // braco direito
            ilustracao[4][7] = '\\';
            ilustracao[5][8] = '\\';
        }
        else if(Jogadas == 5){ // perna esquerda
            ilustracao[7][3] = '/';
            ilustracao[8][2] = '/';
        }
        else if(Jogadas == 5){ // perna direita
            ilustracao[7][7] = '\\';
            ilustracao[8][8] = '\\';
        }
        for(int i = 0; i < Jogadas; i++){
            LetrasErradas[i] = letra;
        }
    }
    
    public static char PedeJogada(){
        boolean erro = false;
        char letra = ' ';
        
        do{
            try{
                System.out.println("Digite uma letra!");
                String l = leia.nextLine();
                letra = l.charAt(0);
                if(!"".equals(letra)){
                    erro = false;
                } else {
                    erro = true;
                }
            } catch(Exception e){
                erro = true;
            }
        } while(erro);
        
        return letra;
    }
    
    public static char[] DestrinchaPalavra(){
        char palavra[] = new char[ConteudoSorteado.length()];
        
        for(int i = 0; i < palavra.length; i++){
            palavra[i] = '_';
            System.out.print(palavra[i] + " ");
        }
        return palavra;
    }
    
    public static void EscolheTema(){
        int num;
        //vetores dos temas
        String animais[] = {"lontra", "guepardo", "macaco", "lobo"};
        String paises[] = {"Brasil", "China", "Holanda", "Noruega"};
        String famosos[] = {"Anitta", "Neymar", "Eliana", "Casimiro"};
        String esportes[] = {"volei", "futebol", "basquete", "corrida"};
        
        System.out.println("Escolha qual dos temas deseja jogar.");
        System.out.println("Temas disponíveis: animais, países, famosos e esportes");
        String TemaEscolhido = leia.nextLine();
        
        if("animais".equals(TemaEscolhido)){
            num = PegarAleatorio();
            ConteudoSorteado = animais[num];
            System.out.println(ConteudoSorteado);
        }
        if("paises".equals(TemaEscolhido)){
            num = PegarAleatorio();
            ConteudoSorteado = paises[num];
            System.out.println(ConteudoSorteado);
        }
        if("famosos".equals(TemaEscolhido)){
            num = PegarAleatorio();
            ConteudoSorteado = famosos[num];
            System.out.println(ConteudoSorteado);
        }
        if("esportes".equals(TemaEscolhido)){
            num = PegarAleatorio();
            ConteudoSorteado = esportes[num];
            System.out.println(ConteudoSorteado);
        }
    }
    
    public static int PegarAleatorio(){
        
        int Resultado = 0;
            
        Random sorteia = new Random();
        Resultado = sorteia.nextInt(4);
        
        return Resultado;
            
    }
    
    public static void ConstroiBoneco(){
        for(int linha = 0; linha < 10; linha++){
            System.out.println("");
            for(int coluna = 0; coluna < 9; coluna++){
                System.out.print(ilustracao[linha][coluna]);
            }
        }
    }
    
    /*
         0   1   2   3   4   5   6   7   8
     0 {'+','-','-','-','-','+',' ',' ',' '},
     1 {'|',' ',' ',' ',' ','|',' ',' ',' '},
     2 {'|',' ',' ',' ',' ','O',' ',' ',' '},
     3 {'|',' ',' ',' ','_','-','_',' ',' '},
     4 {'|',' ',' ','/',' ','|',' ','\\',' '},
     5 {'|',' ','/',' ',' ','|',' ',' ','\\'},
     6 {'|',' ',' ',' ','_','-','_',' ',' '},
     7 {'|',' ',' ','/',' ',' ',' ','\\',' '},
     8 {'|',' ','/',' ',' ',' ',' ',' ','\\'},
     9 {'|',' ',' ',' ',' ',' ',' ',' ',' '},
    */
}

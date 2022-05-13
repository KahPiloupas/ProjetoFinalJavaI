package com.letscode;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VencedoresOscar {
    public static void main(String args[]) throws IOException {
        AtoresOscar();
        AtrizesOscar();
        AtrizesVencedoras();
        AtrizesEAtores();
        System.out.println("Digite o nome de um ator ou o nome de uma atriz: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String name = reader.readLine();
        AtrizesEAtoresInput(name);
    }

    private static void AtoresOscar() throws IOException {

            FileReader ler = new FileReader("resources/AtoresOscar");
            BufferedReader leitor = new BufferedReader(ler);

            String linha;
            String nomeAtorMenorIdade ="";
            Integer idadeAtual = 1000;
//            Não há ator com idade igual a 1000
       leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while((linha = leitor.readLine())!= null) {
                String[] linhaArray=linha.split(";");
                if(Integer.parseInt(linhaArray[2].trim()) < idadeAtual) {
                    nomeAtorMenorIdade = linhaArray[3];
                    idadeAtual = Integer.parseInt(linhaArray[2].trim());
                }
            }
        System.out.println(nomeAtorMenorIdade);
        System.out.println(idadeAtual);
        System.out.println("------------");

    }
    private static void AtrizesOscar() throws IOException {

        FileReader ler = new FileReader("resources/AtrizesOscar");
        BufferedReader leitor = new BufferedReader(ler);


        String linha;
        List<String>listaAtrizes = new ArrayList<>();

        leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            listaAtrizes.add(linhaArray[3]);
        }
        String maisOcorrencias = listaAtrizes.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(listaAtrizes, o1) -
                        Collections.frequency(listaAtrizes, o2))).orElse(null);
        System.out.println(maisOcorrencias);
        System.out.println("------------");
    }
    private static void AtrizesVencedoras() throws IOException {

        FileReader ler = new FileReader("resources/AtrizesOscar");
        BufferedReader leitor = new BufferedReader(ler);


        String linha;
        List<String>listaAtrizes = new ArrayList<>();

        leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            if(Integer.parseInt(linhaArray[2].trim())>20 && Integer.parseInt(linhaArray[2].trim())<30){
//                System.out.println(linhaArray[2]);
                listaAtrizes.add(linhaArray[3]);
            }
        }
        String maisOcorrencias = listaAtrizes.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(listaAtrizes, o1) -
                        Collections.frequency(listaAtrizes, o2))).orElse(null);
        System.out.println(maisOcorrencias);
        System.out.println("------------");
    }
    private static void AtrizesEAtores() throws IOException {

        FileReader ler = new FileReader("resources/AtrizesOscar");
        BufferedReader leitor = new BufferedReader(ler);


        String linha;
        List<String>listaVencedores = new ArrayList<>();
        List<String>listaAtores = new ArrayList<>();

        leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha = leitor.readLine()) != null) {
            String[] linhaArray = linha.split(";");
            listaAtores.add(linhaArray[3]);
        }
        FileReader ler2 = new FileReader("resources/AtoresOscar");
        BufferedReader leitor2 = new BufferedReader(ler2);


        String linha2;

        leitor2.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha2 = leitor2.readLine()) != null) {
            String[] linhaArray = linha2.split(";");
            listaAtores.add(linhaArray[3]);
        }
        List<String>maisOcorrencias2 = listaAtores.stream()
                .filter(i -> Collections.frequency(listaAtores, i) > 1)
                .distinct()
                .collect(Collectors.toList());


        System.out.println(maisOcorrencias2);
        System.out.println("------------");
    }
    private static void AtrizesEAtoresInput(String atorEscolhido) throws IOException {

        FileReader ler = new FileReader("resources/AtrizesOscar");
        BufferedReader leitor = new BufferedReader(ler);


        String linha;
        List<String>listaVencedores = new ArrayList<>();
        List<String>listaAtores = new ArrayList<>();

        leitor.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha = leitor.readLine()) != null) {
            listaAtores.add(linha);
        }
        FileReader ler2 = new FileReader("resources/AtoresOscar");
        BufferedReader leitor2 = new BufferedReader(ler2);


        String linha2;

        leitor2.readLine(); //pulando a primeira linha do cabeçalho!
        while ((linha2 = leitor2.readLine()) != null) {
            listaAtores.add(linha2);
        }
        List<String>listaAtorEscolhido = new ArrayList<>();
        for(
                String linhaAtor: listaAtores
            ) {
            if(linhaAtor.toUpperCase().contains(atorEscolhido.toUpperCase())){
                listaAtorEscolhido.add(linhaAtor);
            }

        }
        System.out.println("Quantidade de Oscars: " + listaAtorEscolhido.size());
        System.out.println(listaAtorEscolhido);
        System.out.println("------------");
    }
}

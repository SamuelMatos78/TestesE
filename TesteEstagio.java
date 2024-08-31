import java.util.*;
public class TesteEstagio
{
	public static void main(String[] args) {
	    Scanner Leitor = new Scanner(System.in);
	    byte Escolha = 0; //menor unidade dispnivel para utilizar como menu
	    System.out.println("\t Boa tarde!");
	    
	    do{
	        System.out.print("Por favor selecione o digito do exercicio que deseja corrigir\n");
		    System.out.print("1 - Media, 2 - String, 3 - Ordenador, 4- Caixa E.\n Para sair digite 0\n");
		    
		    //tratamento de erros graves para garantir a segurança do codigo
		    if(!Leitor.hasNextByte()){
		        System.out.println("Entrada incorreta"); 
		        Leitor.next();
		        continue; //retorna ao inicio do looping para fornecer novamente as informações
		    }

		    Escolha = Leitor.nextByte();
		    Leitor.nextLine(); //Limpa o buffer de um possivel "\n"
		    switch(Escolha){
		        case 0:
		            System.out.println("encerrando");
					Leitor.close();
					break;
		        case 1:
		            float Nota, NotasPonderadas = 0.0f,Peso, SomaPesos = 0.0f;
		            for(int i = 1; i < 4; i++){
		                System.out.println("Digite a nota da prova " + i);
		                Nota = Leitor.nextFloat();
		                System.out.println("Digite o peso da prova " + i);
		                Peso = Leitor.nextFloat();
		                NotasPonderadas += (Nota * Peso);
		                SomaPesos += Peso;
		                
		                
		            }
		            System.out.printf("A média ponderada das provas é: %.2f%n \n",(NotasPonderadas/SomaPesos));
		                break;
		        case 2:
		            String Linha;
		            
					System.out.println("Digite uma String");
					Linha = Leitor.nextLine();
					Map<Character, Integer> contador1 = new HashMap<>();
        
					// Percorre cada caractere da string
					for (char c : Linha.toCharArray()) {
						// Se o caractere já está no mapa, incrementa sua contagem
						if (contador1.containsKey(c)) {
							contador1.put(c, contador1.get(c) + 1);
						} else {
							// Caso contrário, adiciona o caractere ao mapa com contagem 1
							contador1.put(c, 1);
						}
					}
					System.out.println("Caracteres repetidos e suas contagens:");
					for (Map.Entry<Character, Integer> item : contador1.entrySet()) {
						if (item.getValue() > 1) {
							System.out.println(item.getKey() + ": " + item.getValue());
						}
					}

					


					break;
				case 3:
					Random Aleatorio = new Random();
					List<Integer> numeros = new ArrayList<>();
					int num, nums;
					System.out.println("Deseja digitar valores manualmente ou gerar uma lista aleatoria?");

					System.out.print("1 - Digitar Manualmente \n 2 - Gerar Lista\n Qualquer outro numero para sair\n");
					num = Leitor.nextInt();
					if(num == 1){
						System.out.println("Para parar insire uma entrada invalida");
						while(true){ // permite o usuario digitar enquanto a entrada for valida
							try{
								 nums = Leitor.nextInt();
								 numeros.add(nums);
							}catch (InputMismatchException e) {
								Leitor.next();
								break;
							} 
						}
						

					}
					else if(num == 2){
						for (int i = 0; i < 5; i++) {
							
							nums = Aleatorio.nextInt(100); // Numero de 0 - 99
							
							// Adicionar o número à lista
							numeros.add(nums);
						}
					}
					else{
						System.out.println("Sair foi selecionado");
						break;
					}

					System.out.println("Lista Antes de ser ordenada");
					for(int n : numeros)System.out.print(n + " ");
					System.out.println();
					System.out.print("Selecione algum metodo para ordenar\n 1- Selection sort, 2- Quick Sort, 3- Heap sort\n");
					num = Leitor.nextInt();//reutilização da variável de escolhas do exercicio 
					if(num == 1) Selection(numeros);
					else if(num == 2) quickSort(numeros, 0, numeros.size() - 1);
					else if(num == 3) heapSort(numeros);
					else break; //caso erro encerra o sistema

					System.out.println("Lista ordenada");
					for(int n : numeros)System.out.print(n + " ");
					System.out.println();

					


					break;
				case 4:
					int Valor;
					do{
						System.out.println("Digite o valor inteiro valido que deseja sacar");
						Valor = Leitor.nextInt();
					}while(Valor < 1); // Tratamento de erro simples que obriga o usuario a digitar um valor valido
					
					int Cedulas[] = {100,50,20,10};
					int contador = 0;
					
					for(int i = 0; i < 4; i++){
						
						contador = (Valor/Cedulas[i]);
						Valor %= Cedulas[i];
						if(contador > 0)System.out.println(contador + "x " + Cedulas[i]);
						if(Valor == 0)break; // impede de continuar caso já tenha encontrado a combinação
					}
					break;
				default ://tratamento de erros simples
					System.out.println("Invalido");
					break;
					

		            
		        
		            
		    }
		    
		
	    }while( Escolha != 0);

		
		
		
		
		
	}





	public static void Selection (List<Integer> lista){
		int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            // Encontrar o índice do menor elemento na parte não ordenada
            int IDmenor = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j) < lista.get(IDmenor)) {
                    IDmenor = j;
                }
            }
            // Trocar o elemento encontrado com o primeiro elemento da parte não ordenada
            if (IDmenor != i) {
                int temp = lista.get(i);
                lista.set(i, lista.get(IDmenor));
                lista.set(IDmenor, temp);
            }
        }
	}


	public static void quickSort(List<Integer> lista, int esquerda, int direita) {
        if (esquerda < direita) {
            int pivoIndex = partition(lista, esquerda, direita); //seleciona um indice pivot e divide a lista apartir dele
			//chamada recursiva para o restante da lista
            quickSort(lista, esquerda, pivoIndex - 1);
            quickSort(lista, pivoIndex + 1, direita);
        }
    }

	//Escolhe um pivot e segue o modelo de maiores que ele pra direita e menores para esquerda
	private static int partition(List<Integer> lista, int esquerda, int direita) {
        int pivo = lista.get(direita);
        int i = esquerda - 1;

        for (int j = esquerda; j < direita; j++) {
            if (lista.get(j) <= pivo) {
                i++;
                
                int temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
        
        int temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(direita));
        lista.set(direita, temp);

        return i + 1;
    }


	//Um classico de algoritmo e estruturação de dados 2
	public static void heapSort(List<Integer> lista) {
        int n = lista.size();

        // Construir o heap máximo usando mecanismo de arvore B
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(lista, n, i);
        }

        // Extrair elementos 
        for (int i = n - 1; i >= 0; i--) {
            
            int temp = lista.get(0);
            lista.set(0, lista.get(i));
            lista.set(i, temp);
            heapify(lista, i, 0);
        }
    }
	private static void heapify(List<Integer> lista, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
		//busca
        if (esquerda < n && lista.get(esquerda) > lista.get(maior)) {
            maior = esquerda;
        }

        if (direita < n && lista.get(direita) > lista.get(maior)) {
            maior = direita;
        }
		//////////////////////////////////////////////////////
        if (maior != i) {
            int temp = lista.get(i);
            lista.set(i, lista.get(maior));
            lista.set(maior, temp);

            
            heapify(lista, n, maior);
        }
    }
}

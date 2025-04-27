import java.util.ArrayList;
import java.util.Scanner;
 
abstract class Transporte{
    protected int id;
    protected String marca;
    protected int ano;
 
    public Transporte(int id, String marca, int ano){
        this.id = id;
        this.marca = marca;
        this.ano = ano;
    }
 
    public int getId(){
        return id;
    }
 
    public abstract void exibirDados();
}
 
class Onibus extends Transporte{
    protected int capacidadePassageiros;
    protected String tipo;
 
    public Onibus(int id, String marca, int ano, int capacidadePassageiros, String tipo){
        super(id, marca, ano);
        this.capacidadePassageiros = capacidadePassageiros;
        this.tipo = tipo;
    }
 
    public void abrirPortas(){
        System.out.println("As portas do ônibus foram abertas!");
    }
 
    public void fecharPortas(){
        System.out.println("As portas do ônibus foram fechadas!");
    }
 
    @Override
    public void exibirDados(){
        System.out.println("Id do ônibus: " + id);
        System.out.println("Marca do ônibus: " + marca);
        System.out.println("Ano do ônibus: " + ano);
        System.out.println("Capacidade de passageiros: " + capacidadePassageiros);
        System.out.println("Tipo do ônibus: " + tipo);
    }
}
 
class Metro extends Transporte{
    protected int numeroVagoes;
    protected boolean temArCondicionado;
    protected int quantidadePassageiros;
 
    public Metro(int id, String marca, int ano, int numeroVagoes, boolean temArCondicionado, int quantidadePassageiros){
        super(id, marca, ano);
        this.numeroVagoes = numeroVagoes;
        this.temArCondicionado = temArCondicionado;
        this.quantidadePassageiros = quantidadePassageiros;
    }
 
    public void embarcarPassageiros(int quantidade){
        quantidadePassageiros += quantidade;
        System.out.println(quantidade + " passageiros embarcaram no metrô.");
    }
 
    public void desembarcarPassageiros(int quantidade){
        if (quantidadePassageiros >= quantidade){
            quantidadePassageiros -= quantidade;
            System.out.println(quantidade + " passageiros desembarcaram do metrô.");
        } else {
            System.out.println("Erro: Não há passageiros suficientes para desembarcar.");
        }
    }
 
    @Override
    public void exibirDados(){
        System.out.println("Id do metrô: " + id);
        System.out.println("Marca do metrô: " + marca);
        System.out.println("Ano do metrô: " + ano);
        System.out.println("Número de vagões: " + numeroVagoes);
        System.out.println("Ar Condicionado: " + (temArCondicionado ? "Sim" : "Não"));
        System.out.println("Passageiros a bordo: " + quantidadePassageiros);
    }
}
 
class Garagem {
    private ArrayList<Transporte> veiculos;
 
    public Garagem() {
        veiculos = new ArrayList<>();
    }
 
    public void adicionarVeiculo(Transporte v){
        veiculos.add(v);
        System.out.println("Veículo adicionado à garagem.");
    }
 
    public void listarTodos(){
        if (veiculos.isEmpty()){
            System.out.println("A garagem está vazia.");
        } else{
            for (Transporte veiculo : veiculos){
                veiculo.exibirDados();
                System.out.println("----------------------------");
            }
        }
    }
 
    public void listarPorTipo(Class<?> tipo){
        boolean encontrado = false;
        for (Transporte veiculo : veiculos){
            if (tipo.isInstance(veiculo)){
                veiculo.exibirDados();
                System.out.println("----------------------------");
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("Nenhum veículo do tipo " + tipo.getSimpleName() + " encontrado.");
        }
    }
 
    public int contarVeiculos(){
        return veiculos.size();
    }
 
    public int contarVeiculosPorTipo(Class<?> tipo){
        int count = 0;
        for (Transporte veiculo : veiculos){
            if (tipo.isInstance(veiculo)){
                count++;
            }
        }
        return count;
    }
 
    public Transporte buscarPorId(int id){
        for (Transporte veiculo : veiculos){
            if (veiculo.getId() == id){
                return veiculo;
            }
        }
        return null;
    }
}
 
public class SistemaTransporteApp{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Garagem garagem = new Garagem();
 
        int opcao;
        do {
            System.out.println("\n ===== Sistema de Transporte =====");
            System.out.println("1. Cadastrar ônibus.");
            System.out.println("2. Cadastrar metrô.");
            System.out.println("3. Listar todos os veículos.");
            System.out.println("4. Listar veículos por tipo.");
            System.out.println("5. Executar ação específica.");
            System.out.println("6. Mostrar total de veículos por tipo.");
            System.out.println("7. Sair.");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
 
            switch(opcao){
                case 1:
                    System.out.print("ID do ônibus: ");
                    int idO = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Marca do ônibus: ");
                    String marcaO = scanner.nextLine();
                    System.out.print("Ano do ônibus: ");
                    int anoO = scanner.nextInt();
                    System.out.print("Capacidade de passageiros: ");
                    int cap = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo (articulado, convencional, micro-ônibus): ");
                    String tipo = scanner.nextLine();
                    garagem.adicionarVeiculo(new Onibus(idO, marcaO, anoO, cap, tipo));
                    break;
 
                case 2:
                    System.out.print("ID do metrô: ");
                    int idM = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Marca do metrô: ");
                    String marcaM = scanner.nextLine();
                    System.out.print("Ano do metrô: ");
                    int anoM = scanner.nextInt();
                    System.out.print("Número de vagões: ");
                    int vag = scanner.nextInt();
                    System.out.print("Tem ar condicionado (true/false): ");
                    boolean ar = scanner.nextBoolean();
                    System.out.print("Quantidade de passageiros: ");
                    int qtd = scanner.nextInt();
                    garagem.adicionarVeiculo(new Metro(idM, marcaM, anoM, vag, ar, qtd));
                    break;

                case 3:
                    garagem.listarTodos();
                    break;
 
                case 4:
                    System.out.print("Tipo (Onibus/Metro): ");
                    String tipoBusca = scanner.nextLine();
                    if(tipoBusca.equalsIgnoreCase("Onibus")){
                        garagem.listarPorTipo(Onibus.class);
                    } else if(tipoBusca.equalsIgnoreCase("Metro")){
                        garagem.listarPorTipo(Metro.class);
                    } else{
                        System.out.println("Tipo inválido.");
                    }
                    break;
 
                case 5:
                    System.out.print("ID do veículo: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();
                    Transporte veiculo = garagem.buscarPorId(idBusca);
                    if(veiculo == null){
                        System.out.println("Veículo não encontrado!");
                        break;
                    }
                    System.out.print("Ação (abrirPortas/embarcarPassageiros/desembarcarPassageiros): ");
                    String acao = scanner.nextLine();
 
                    if(veiculo instanceof Onibus && acao.equalsIgnoreCase("abrirPortas")){
                        ((Onibus) veiculo).abrirPortas();
                    } else if(veiculo instanceof Metro){
                        if(acao.equalsIgnoreCase("embarcarPassageiros")){
                            System.out.print("Quantidade: ");
                            int q = scanner.nextInt();
                            ((Metro) veiculo).embarcarPassageiros(q);
                        } else if(acao.equalsIgnoreCase("desembarcarPassageiros")){
                            System.out.print("Quantidade: ");
                            int q = scanner.nextInt();
                            ((Metro) veiculo).desembarcarPassageiros(q);
                        } else{
                            System.out.println("Ação inválida.");
                        }
                    } else{
                        System.out.println("Ação inválida para o tipo de veículo.");
                    }
                    break;
 
                case 6:
                    System.out.print("Tipo (Onibus/Metro): ");
                    String tipoCount = scanner.nextLine();
                    if (tipoCount.equalsIgnoreCase("Onibus")){
                        System.out.println("Total de ônibus: " + garagem.contarVeiculosPorTipo(Onibus.class));
                    } else if(tipoCount.equalsIgnoreCase("Metro")){
                        System.out.println("Total de metrôs: " + garagem.contarVeiculosPorTipo(Metro.class));
                    } else{
                        System.out.println("Tipo inválido.");
                    }
                    break;
 
                case 7:
                    System.out.println("Saindo...");
                    break;
 
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
        scanner.close();
    }
}
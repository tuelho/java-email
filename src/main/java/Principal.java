
/**
 *
 * @author tuelho
 */
public class Principal {

    

    public static void main(String args[]) {
        String mensagem = "Teste de mensagem de email";
        EnvioEmail.enviaEmailSimples("eduardo.jordano@qualirede.com.br", "Eduardo Jordano", "Titulo Email Maven", mensagem);
        
    }
}

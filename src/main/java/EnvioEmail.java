import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EnvioEmail {

    public static void enviaEmailSimples(String destinatario, String nomeDestinatario, String assunto, String mensagem) {
        SimpleEmail email = new SimpleEmail();
        // configura envio

        try {
            email.setHostName(Configuracao.getProperties("servidor")); // o servidor SMTP para envio do e-mail
            email.setFrom(Configuracao.getProperties("email"), Configuracao.getProperties("nome")); // remetente
            email.setAuthentication(Configuracao.getProperties("login"), Configuracao.getProperties("senha"));
            email.setSmtpPort(Integer.valueOf(Configuracao.getProperties("porta")));
            email.setSSL(true);
            email.setTLS(true);
            // configura mensagem
            email.addTo(destinatario, nomeDestinatario); //destinatário
            email.setSubject(assunto); // assunto do e-mail
            email.setMsg(mensagem); //conteudo do e-mail
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnvioEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void enviaEmailComAnexo(String destinatario, String nomeDestinatario, String assunto, String mensagem, List<String> anexos) {
        // configura o email
        MultiPartEmail email = new MultiPartEmail();
        // configura envio
        try {
            email.setHostName(Configuracao.getProperties("servidor")); // o servidor SMTP para envio do e-mail
            email.setFrom(Configuracao.getProperties("email"), Configuracao.getProperties("nome")); // remetente
            email.setAuthentication(Configuracao.getProperties("login"), Configuracao.getProperties("senha"));
            email.setSmtpPort(Integer.valueOf(Configuracao.getProperties("porta")));
            email.setSSL(true);
            email.setTLS(true);
            // configura mensagem 
            email.addTo(destinatario, nomeDestinatario); //destinatário
            email.setSubject(assunto); // assunto do e-mail
            email.setMsg(mensagem); //conteudo do e-mail
            //email.send();

            //configura anexos
            int cont = 1;
            for (String anexo : anexos) {
                String caminho = new File(".").getCanonicalPath() + "\\" + anexo + ".png";
                EmailAttachment anexo1 = new EmailAttachment();
                anexo1.setPath(caminho); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)
                anexo1.setDisposition(EmailAttachment.ATTACHMENT);
                anexo1.setDescription("Cupom " + 1);
                anexo1.setName("cupom" + cont);
                cont++;
                email.attach(anexo1);
            }
            // envia o email
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnvioEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnvioEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviaEmailFormatoHtml() throws EmailException, MalformedURLException {
        HtmlEmail email = new HtmlEmail();
        // adiciona uma imagem ao corpo da mensagem e retorna seu id</div>
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");
        // configura a mensagem para o formato HTML
        email.setHtmlMsg("<html>Logo do Apache - <img ></html>");
        // configure uma mensagem alternativa caso o servidor não suporte HTML
        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo("teste@gmail.com", "Eduardo Jordano"); //destinatário
        email.setFrom("teste@gmail.com", "Eu"); // remetente</div>
        email.setSubject("Teste -> Html Email"); // assunto do e-mail
        email.setMsg("Teste de Email HTML utilizando commons-email"); //conteudo do e-mail
        email.setAuthentication("teste", "xxxxx");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        // envia email
        email.send();
    }
}

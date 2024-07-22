package br.com.dbc.vemser.projetoTelecomunicacoes.service;

import br.com.dbc.vemser.projetoTelecomunicacoes.entity.Cliente;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    //@Value("${mail.pessoa.criar}")
    private String pessoaCriada;
    private Cliente cliente;
    private String templateString;

    @Value("${spring.mail.username}")
    private String de;

    private String para = "pehewo8192@ikangou.com";
    private final JavaMailSender emailSender;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(de);
        message.setTo(para);
        message.setSubject("Assunto");
        message.setText("Teste\n minha mensagem \n\nAtt,\nSistema.");
        emailSender.send(message);
    }

    public void sendWithAttachment() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                true);

        helper.setFrom(de);
        helper.setTo(para);
        helper.setSubject("Assunto");
        helper.setText("Teste\n minha mensagem \n\nAtt,\nSistema.");

        File file1 = new File("imagem.jpg");
        FileSystemResource file
                = new FileSystemResource(file1);
        helper.addAttachment(file1.getName(), file);

        emailSender.send(message);
    }

    public void sendEmail(Cliente clienteEntity, String templateString) {
        this.cliente = clienteEntity;
        this.templateString = templateString;
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(de);
            mimeMessageHelper.setTo(clienteEntity.getEmail());

            switch (templateString) {
                case "cp":
                    mimeMessageHelper.setSubject("Suporte Telecom.");
                    break;
                case "ue":
                    mimeMessageHelper.setSubject("Suporte PessoaAPI: Nova atualização de endereço.");
                    break;
                case "de":
                    mimeMessageHelper.setSubject("Suporte PessoaAPI: Delete de endereço.");
                    break;
            }

            mimeMessageHelper.setText(geContentFromTemplate(), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate() throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", cliente.getNome());
//        dados.put("id", cliente.getIdPessoa());
        dados.put("email", cliente.getEmail());
//        dados.put("datanascimento", cliente.getDataNascimento());
        dados.put("cpf", cliente.getCpf());

        String html = "";

        Template template;

        switch (templateString) {
            case "cp":
                template = fmConfiguration.getTemplate("email-templateCreate.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
                break;
            case "ue":
                template = fmConfiguration.getTemplate("email-templateUpdate.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
                break;
            case "de":
                template = fmConfiguration.getTemplate("email-templateDelete.ftl");
                html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
                break;
        }
        return html;
    }
}
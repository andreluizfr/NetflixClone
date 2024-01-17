package com.example.MessagingService.Email.Models;

@Getter
@Setter
@NoArgsConstructor
public class NewUserEmail {
    String to;
    String subject;
    String body;

    public NewUserEmail(String to) {
        this.to = to;
        this.subject = "Bem-vindo ao sistema Netflix";
        this.body = new StringBuilder()
            .append("<div>")
            .append("   <p>")
            .append("       Bem-vindo ao sistema Netflix. Sinta-se livre para desfrutar dos nossos serviços.")
            .append("   </p>")
            .append("</div>")
            .toString();
    }
}

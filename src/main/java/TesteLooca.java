import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;



import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import static javax.swing.JOptionPane.showInputDialog;

public class TesteLooca {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Random random = new Random();
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Scanner email = new Scanner(System.in);
        Scanner senha = new Scanner(System.in);
        Scanner aleatorio = new Scanner(System.in);

        System.out.println("Digite seu email: ");
        String emailUser = email.next();
        System.out.println("Digite sua senha: ");
        String senhaUser = senha.next();

        Integer numeroAleatorio = random.nextInt(100000);
        System.out.println("Digite o seguinte número: " + numeroAleatorio);
        String Inserido = aleatorio.next();

        List<Usuario> usuariosBanco = con.query("SELECT email, senha FROM usuario " +
                        "WHERE email = '%s' AND senha = '%s'".formatted(emailUser, senhaUser),
                new BeanPropertyRowMapper<>(Usuario.class));

        if (numeroAleatorio.equals(Integer.parseInt(Inserido)) && usuariosBanco.size() < 1) {
            System.out.println("Informações inválidas");
        } else {
            System.out.println("Bem-vindo Usuário!");
            JanelaGrupo windows = new JanelaGrupo(new SystemInfo());
            SystemInfo sistema = new SystemInfo();

            List<Janela> janelas = windows.getJanelas();

            Scanner leitor = new Scanner(System.in);
            Integer escolha;
            do {
                System.out.println("Bem vindo Usuário!");
                System.out.println("""
                        *-----------------------------------------------*
                        |       Monitoramento de Janelas da NOCT.U      |  
                        *-----------------------------------------------*
                        | 1 - Visualizar quantidade de janelas visiveis |
                        | 2 - Verificar Janelas Visiveis                |
                        | 3 - Verificar Sistema Operacional             |
                        | 0 - Sair                                      |
                        *-----------------------------------------------*            
                        """);

                escolha = leitor.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.println("Total das janelas: " + windows.getTotalJanelas());
                        break;


                    case 2:
                        System.out.println("Janelas visiveis: " + windows.getJanelasVisiveis());
                        break;

                    case 3:

                        System.out.println("Sobre o sistema: " + sistema.getOperatingSystem());
                        break;
                }

            } while (escolha != 0);


        }
    }

}

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

import static javax.swing.JOptionPane.showInputDialog;

public class TesteLooca {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        JanelaGrupo windows = new JanelaGrupo(new SystemInfo());
        SystemInfo sistema  = new SystemInfo();

        List<Janela> janelas = windows.getJanelas();

        Scanner leitor = new Scanner(System.in);

        String email = String.valueOf(new Scanner(System.in));
        String senha = String.valueOf(new Scanner(System.in));

        JOptionPane.showInputDialog(null, "Área de Login");

        email = showInputDialog("Digite seu email: ");

        senha = showInputDialog("Digite sua senha: ");

        List<Usuario> usuariosBanco = con.query("SELECT email, senha FROM usuario " +
                        "WHERE email = '%s' AND senha = '%s'".formatted(email, senha),
                new BeanPropertyRowMapper<>(Usuario.class));

        if (usuariosBanco.size() < 1) {
            JOptionPane.showInputDialog(null, "Email e/ou senha inválidos. Inicie novamente o programa.", "ERRO", JOptionPane.ERROR_MESSAGE);

        } else {
            Integer escolha;
            do {

                        showInputDialog("""
                        *---------------------------------------------------------------*
                        |      Monitoramento de Janelas da NOCT.U     |   
                        *---------------------------------------------------------------*
                        | 1 - Visualizar quantidade de janelas visiveis |
                        | 2 - Verificar Sistema Operacional                    |
                        | 0 - Sair                                                                 |
                        *---------------------------------------------------------------*
                        Insira também no sistema para validação da opção            
                        """);

                escolha = leitor.nextInt();

                switch (escolha){
                    case 1:
                    System.out.println("Total das janelas: " + windows.getTotalJanelas());
                    break;

                    case 2:
                    System.out.println("Sobre o sistema: " + sistema.getOperatingSystem());
                    break;
                }

            } while (escolha != 0);


        }
    }

}

package primeirob.atvtres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClienteSocketSwing extends JFrame {

    private static final long serialVersionUID = -5261903818373181455L;
    private JTextArea taEditor = new JTextArea("Digite aqui sua mensagem");
    private JTextArea taVisor = new JTextArea();
    private JList<String> liUsuarios = new JList<>();
    private PrintWriter escritor;
    private BufferedReader leitor;
    private JScrollPane scrollTaVisor = new JScrollPane(taVisor);

    public ClienteSocketSwing() {
        // Configuração da janela principal
        setTitle("Chat com Sockets - MAGEDDO.COM");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        liUsuarios.setBackground(Color.GRAY);
        taEditor.setBackground(Color.CYAN);

        taEditor.setPreferredSize(new Dimension(400, 40));
        taVisor.setEditable(false);
        liUsuarios.setPreferredSize(new Dimension(100, 140));

        add(taEditor, BorderLayout.SOUTH);
        add(scrollTaVisor, BorderLayout.CENTER);
        add(new JScrollPane(liUsuarios), BorderLayout.WEST);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] usuarios = new String[] { "elvis", "maria", "joao", "jose", "renata", "marizete", "mateus", "robson" };
        preencherListaUsuarios(usuarios);

        iniciarEscritor();
        iniciarChat();
    }

    private void preencherListaUsuarios(String[] usuarios) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        liUsuarios.setModel(modelo);
        for (String usuario : usuarios) {
            modelo.addElement(usuario);
        }
    }

    private void iniciarEscritor() {
        taEditor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String mensagem = taEditor.getText().trim();
                    if (!mensagem.isEmpty()) {
                        String usuario = liUsuarios.getSelectedValue();
                        if (usuario != null) {
                            // Envia a mensagem para o visor e para o servidor
                            taVisor.append("Eu: " + mensagem + "\n");
                            escritor.println(Comandos.MENSAGEM + " " + usuario);
                            escritor.println(mensagem);

                            // Limpa o editor
                            taEditor.setText("");
                            e.consume(); // Evita o beep do Enter
                        } else {
                            JOptionPane.showMessageDialog(ClienteSocketSwing.this, "Selecione um usuário");
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public void iniciarChat() {
        try {
            final Socket cliente = new Socket("127.0.0.1", 9999);
            escritor = new PrintWriter(cliente.getOutputStream(), true);
            leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            System.out.println("Conectado ao servidor com sucesso!"); // Log de sucesso na conexão

            // Thread para leitura das mensagens do servidor
            new Thread(() -> {
                try {
                    String mensagemRecebida;
                    while ((mensagemRecebida = leitor.readLine()) != null) {
                        taVisor.append(mensagemRecebida + "\n"); // Exibe a mensagem recebida no visor
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao ler mensagens do servidor");
                    e.printStackTrace();
                }
            }).start();

        } catch (UnknownHostException e) {
            System.out.println("O endereço passado é inválido");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("O servidor pode estar fora do ar ou indisponível.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClienteSocketSwing();
    }
}

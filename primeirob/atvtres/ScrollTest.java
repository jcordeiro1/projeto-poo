package primeirob.atvtres;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollTest extends JFrame {
    JTextArea txt = new JTextArea();
    JScrollPane scroll = new JScrollPane(txt);
    JButton botaoDescer = new JButton("Descer");
    JButton botaoColocarDados = new JButton("Encher");

    public ScrollTest() {
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(botaoDescer, BorderLayout.SOUTH);
        add(botaoColocarDados, BorderLayout.NORTH);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        txt.setEditable(false);

        // Ação do botão "Descer"
        botaoDescer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move o caret do JTextArea para a última posição
                txt.setCaretPosition(txt.getDocument().getLength());
                System.out.println("Descendo");
            }
        });

        // Ação do botão "Encher"
        botaoColocarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 2; i++) {
                    txt.append(i + "\n"); // Adiciona número e quebra de linha
                }
                System.out.println("Colocando dados");
            }
        });
    }

    public static void main(String[] args) {
        new ScrollTest();
    }
}

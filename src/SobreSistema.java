import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Janela Sobre o PDialogMaker (about)
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */

public class SobreSistema extends JFrame {

	private static final long serialVersionUID = -4382585669442621767L;
	
	private Atributo vari;
    
	private JLabel lbTitulo;
	private JLabel lbVersao;
	private JTextArea txSobre;
	private JLabel lbSite;
	private JLabel lbSiteLink;
	private JLabel lbEmail;
	private JLabel lbEmailLink;
	private JLabel lbGitHub;
	private JLabel lbGitHubLink;
	private JLabel lbCafeteira;
	private JLabel lbCafeteiraLink;
	private JLabel lbIcones;
	private JLabel lbIconesLink;
	private String textAbout;
	
    
    /** Cria a janela dos dados */
    public SobreSistema() {
    	Font labelFont = new JLabel().getFont();
        vari = new Atributo();
        this.setLayout(null);
        this.setSize(390, 460);
        this.setTitle("Sobre - PDialogMaker - " + vari.CFVERSAO);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        lbTitulo = new JLabel("PDialogMaker");
        lbTitulo.setForeground(Color.RED);
        lbTitulo.setFont(new Font(labelFont.getName(), Font.BOLD, 30));
        lbTitulo.setBounds(3, 5, 300, 40);
        add(lbTitulo);
        
        lbVersao = new JLabel(vari.CFVERSAO + " - " + vari.REVISAO);
        lbVersao.setForeground(Color.RED);
        lbVersao.setBounds(3, 35, 300, 30);
        add(lbVersao);
        
        textAbout = "Sistema de criação de Dialogs para ser usado em programas com a linguagem AdvPL (Advanced Protheus Language).\n\n"
        		+ "Muitos dos meus alunos tinham dúvidas em como os componentes se comportam em AdvPL, por isso foi desenvolvido esse sistema para auxilio.\n\n"
        		+ "Ele foi baseado no projeto Cafeteira do grande Fernando Anselmo.";
        txSobre = new JTextArea(textAbout);
        txSobre.setBounds(3, 75, 367, 160);
        txSobre.setEditable(false);
        txSobre.setLineWrap(true);
        txSobre.setWrapStyleWord(true);
        add(txSobre);
        
        lbSite = new JLabel("Acesse nosso Site:");
        lbSite.setBounds(3, 235, 300, 15);
        add(lbSite);
        
        lbSiteLink = new JLabel("atiliosistemas.com");
        lbSiteLink.setForeground(Color.BLUE);
        lbSiteLink.setBounds(13, 250, 300, 15);
        goWebsite(lbSiteLink, "https://atiliosistemas.com", "");
        add(lbSiteLink);
        
        lbEmail = new JLabel("Nosso e-Mail para contato:");
        lbEmail.setBounds(3, 275, 300, 15);
        add(lbEmail);
        
        lbEmailLink = new JLabel("contato@atiliosistemas.com");
        lbEmailLink.setForeground(Color.BLUE);
        lbEmailLink.setBounds(13, 290, 300, 15);
        goWebsite(lbEmailLink, "mailto:contato@atiliosistemas.com", "");
        add(lbEmailLink);
        
        lbGitHub = new JLabel("GitHub do projeto:");
        lbGitHub.setBounds(3, 315, 300, 15);
        add(lbGitHub);
        
        lbGitHubLink = new JLabel("github.com/dan-atilio/PDialogMaker");
        lbGitHubLink.setForeground(Color.BLUE);
        lbGitHubLink.setBounds(13, 330, 300, 15);
        goWebsite(lbGitHubLink, "https://github.com/dan-atilio/PDialogMaker", "");
        add(lbGitHubLink);
        
        lbCafeteira = new JLabel("Veja o projeto Cafeteira do Fernando Anselmo:");
        lbCafeteira.setBounds(3, 350, 300, 15);
        add(lbCafeteira);
        
        lbCafeteiraLink = new JLabel("fernandoanselmo.orgfree.com/wordpress/?page_id=7");
        lbCafeteiraLink.setForeground(Color.BLUE);
        lbCafeteiraLink.setBounds(13, 365, 300, 15);
        goWebsite(lbCafeteiraLink, "http://fernandoanselmo.orgfree.com/wordpress/?page_id=7", "");
        add(lbCafeteiraLink);
        
        lbIcones = new JLabel("Veja os ícones baixados no Flaticon:");
        lbIcones.setBounds(3, 385, 300, 15);
        add(lbIcones);
        
        lbIconesLink = new JLabel("Clique aqui para ver os links");
        lbIconesLink.setForeground(Color.BLUE);
        lbIconesLink.setBounds(13, 400, 300, 15);
        showTxt(lbIconesLink);
        add(lbIconesLink);
        
        this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				aoFechar();
			}
		});
        this.setVisible(true);
        
        ImageIcon icone = vari.getImage("logo.png");
		this.setIconImage(icone.getImage());
    }
    
    private void aoFechar() {
        dispose();
    }
    
    //Exemplo extraído de: https://stackoverflow.com/questions/8669350/jlabel-hyperlink-to-open-browser-at-correct-url
    private void goWebsite(JLabel website, final String url, String text) {
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
    }
    
    private void showTxt(JLabel website) {
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String dir = System.getProperty("java.io.tmpdir");
                String text = "";
                
                text =    "Descrição                      | Link                                             | Autor\n"
                		+ "-------------------------------+--------------------------------------------------+---------------------------------------------------\n"
                		+ "Opção de Mudar o Título        | https://www.flaticon.com/free-icons/title        | Title icons created by 88 Cloud - Flaticon\n"
                		+ "Opção de Mudar a cor da Janela | https://www.flaticon.com/free-icons/color        | Color icons created by Nikita Golubev - Flaticon\n"
                		+ "Opção de Criar Objetos         | https://www.flaticon.com/free-icons/create       | Create icons created by Freepik - Flaticon\n"
                		+ "Opção de Gerar o Fonte         | https://www.flaticon.com/free-icons/open-source  | Open source icons created by Smashicons - Flaticon\n"
                		+ "Opção de Abrir Arquivo         | https://www.flaticon.com/free-icons/folder       | Folder icons created by kumakamu - Flaticon\n"
                		+ "Opção de Salvar Arquivo        | https://www.flaticon.com/free-icons/save         | Save icons created by Freepik - Flaticon\n"
                		+ "Opção de Limpar a Tela         | https://www.flaticon.com/free-icons/trash-can    | Trash can icons created by Freepik - Flaticon\n"
                		+ "Opção do Botão de Sobre        | https://www.flaticon.com/free-icons/information  | Information icons created by Freepik - Flaticon\n"
                		+ "Opção do Botão de Sair         | https://www.flaticon.com/free-icons/close        | Close icons created by Alfredo Hernandez - Flaticon\n"
                		+ "Opção de Acionar o Cursor      | https://www.flaticon.com/br/icones-gratis/cursor | Cursor ícones criados por Freepik - Flaticon";
                
                PrintWriter writer;
				try {
					writer = new PrintWriter(dir + "pdialog_icons.txt", "UTF-8");
					writer.println(text);
	                writer.close();
	                File file = new File(dir + "pdialog_icons.txt");
	                
	                if (Desktop.isDesktopSupported()) {
	                    Desktop.getDesktop().edit(file);
	                } else {
	                    // I don't know, up to you to handle this
	                }
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    }
}
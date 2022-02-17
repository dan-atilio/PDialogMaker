import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Cria os componentes na Janela Principal
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */
public class CriaComponente {
    // Referência
    private PDialogMaker frmModi;
    // cursor
    private final Cursor MOVE_CURSOR = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    private final Cursor DEFAULT_CURSOR = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    private Cursor cursorAtual;
    // do componente
    private Component cmpMove;
    private JComponent cmpSelecionado;
    private Rectangle oldCaixaMov;
    private int moveStartX;
    private int moveStartY;
    // do Menu do Objeto
    private JPopupMenu popMenu;
    private JMenuItem itmPropriedade;
    private JMenuItem itmExclui;
    private AnalisaJComponent ajc;
    
    /** Construtor da classe
     * @param recModi Janela principal
     */
    public CriaComponente(PDialogMaker recModi) {
        frmModi = recModi;
        cursorAtual = DEFAULT_CURSOR;
        cmpMove = null;
        cmpSelecionado = null;
        popMenu = new JPopupMenu();
        itmPropriedade = new JMenuItem("Propriedades");
        itmExclui = new JMenuItem("Exclui");
        ajc = new AnalisaJComponent();
    }
    
    /** Cria determinado objeto em uma posição determinada pelo mouse
     * 1 - Cria CafTextField
     * 2 - Cria CafPasswordField
     * 3 - CafButton
     * 4 - CafList
     * 5 - CafTextArea
     * 6 - CafRadioButton
     * 7 - CafCheckBox
     * 8 - CafLabel
     * 9 - CafComboBox
     * @param e objeto MouseEvent contendo a posição do mouse
     * @param objCriar Inteiro contendo o número do objeto
     */
    public void criaObjeto(MouseEvent e, int objCriar) {
        switch (objCriar) {
            case 1: // CafTextField
                CafTextField jTextField1 = new CafTextField("", frmModi);
                jTextField1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 100, 21));
                insEvento(jTextField1);
                frmModi.getContentPane().add(jTextField1, null);
                break;
            case 2: // CafPasswordField
                CafPasswordField jPasswordField1 = new CafPasswordField("", frmModi);
                jPasswordField1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 100, 21));
                insEvento(jPasswordField1);
                frmModi.getContentPane().add(jPasswordField1, null);
                break;
            case 3: // CafButton
                CafButton jButton1 = new CafButton("Button", frmModi);
                jButton1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 100, 30));
                insEvento(jButton1);
                frmModi.getContentPane().add(jButton1, null);
                break;
            case 4: // CafList
                CafList jList1 = new CafList(frmModi);
                jList1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 130, 160));
                insEvento(jList1);
                frmModi.getContentPane().add(jList1, null);
                break;
            case 5: // CafTextArea
                CafTextArea jTextArea1 = new CafTextArea("", frmModi);
                jTextArea1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 130, 80));
                insEvento(jTextArea1);
                frmModi.getContentPane().add(jTextArea1, null);
                break;
            case 6: // CafRadioButton
                CafRadioButton jRadioButton1 = new CafRadioButton("RadioButton", frmModi);
                jRadioButton1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 100, 30));
                insEvento(jRadioButton1);
                frmModi.getContentPane().add(jRadioButton1, null);
                break;
            case 7: // CafCheckBox
                CafCheckBox jCheckBox1 = new CafCheckBox("CheckBox", frmModi);
                jCheckBox1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 100, 30));
                insEvento(jCheckBox1);
                frmModi.getContentPane().add(jCheckBox1, null);
                break;
            case 8: // CafLabel
                CafLabel jLabel1 = new CafLabel("Label", frmModi);
                jLabel1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 57, 13));
                insEvento(jLabel1);
                frmModi.getContentPane().add(jLabel1, null);
                break;
            case 9: // CafComboBox
                CafComboBox jComboBox1 = new CafComboBox(frmModi);
                jComboBox1.setBounds(new Rectangle(e.getX()-15, e.getY()-25, 57, 20));
                insEvento(jComboBox1);
                frmModi.getContentPane().add(jComboBox1, null);
                break;
        }
        if (frmModi.ajObjeto)
            frmModi.janAjusta.carrCbComp();
        frmModi.repaint();
    }
    
    /** Cria determinado objeto a partir de uma informação salva
     * 1 - Cria CafTextField
     * 2 - Cria CafPasswordField
     * 3 - CafButton
     * 4 - CafList
     * 5 - CafTextArea
     * 6 - CafRadioButton
     * 7 - CafCheckBox
     * 8 - CafLabel
     * 9 - CafComboBox
     * @param objCriar Inteiro contendo o número do objeto
     * @param nome String contendo o nome
     * @param posX Inteiro para a posição a esquerda
     * @param posY Inteiro para a posição do topo
     * @param posW Inteiro para a largura
     * @param posH Inteiro para a altura
     * @param texto String contendo o "text" do objeto
     * @param hint String contendo a "sugestão" do objeto
     * @param nEvt Inteiro contendo os eventos
     */
    public void criaObjetoDoArquivo(int objCriar, String nome, int posX,
    int posY, int posW, int posH, String texto, String hint, int nEvt) {
        switch (objCriar) {
            case 1: // CafTextField
                CafTextField jTextField1 = new CafTextField("", frmModi);
                jTextField1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jTextField1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jTextField1, null);
                break;
            case 2: // CafPasswordField
                CafPasswordField jPasswordField1 = new CafPasswordField("", frmModi);
                jPasswordField1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jPasswordField1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jPasswordField1, null);
                break;
            case 3: // CafButton
                CafButton jButton1 = new CafButton("Button", frmModi);
                jButton1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jButton1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jButton1, null);
                break;
            case 4: // CafList
                CafList jList1 = new CafList(frmModi);
                jList1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jList1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jList1, null);
                break;
            case 5: // CafTextArea
                CafTextArea jTextArea1 = new CafTextArea("", frmModi);
                jTextArea1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jTextArea1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jTextArea1, null);
                break;
            case 6: // CafRadioButton
                CafRadioButton jRadioButton1 = new CafRadioButton("RadioButton", frmModi);
                jRadioButton1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jRadioButton1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jRadioButton1, null);
                break;
            case 7: // CafCheckBox
                CafCheckBox jCheckBox1 = new CafCheckBox("CheckBox", frmModi);
                jCheckBox1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jCheckBox1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jCheckBox1, null);
                break;
            case 8: // CafLabel
                CafLabel jLabel1 = new CafLabel("Label", frmModi);
                jLabel1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jLabel1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jLabel1, null);
                break;
            case 9: // CafComboBox
                CafComboBox jComboBox1 = new CafComboBox(frmModi);
                jComboBox1.setBounds(new Rectangle(posX, posY, posW, posH));
                insEvento(jComboBox1, nome, texto, hint, nEvt);
                frmModi.getContentPane().add(jComboBox1, null);
                break;
        }
    }
    
    private void insEvento(JComponent cmp) {
        cmp.setName("objeto" + frmModi.seqObj++);
        // Menu do Objeto
        popMenu.add(itmPropriedade);
        popMenu.add(itmExclui);
        //    cmp.add(popMenu);
        // Eventos do Menu do Objeto
        itmPropriedade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                propObjeto(e);
            }
        });
        itmExclui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excObjeto(e);
            }
        });
        // Eventos de Mouse
        cmp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aoClicarMouse(evt);
            }
            public void mousePressed(MouseEvent evt) {
                aoPressionarMouse(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                aoSoltarMouse(evt);
            }
            public void mouseEntered(MouseEvent evt) {
            }
            public void mouseExited(MouseEvent evt) {
            }
        });
        cmp.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent evt) {
                aoArrastarMouse(evt);
            }
            public void mouseMoved(MouseEvent evt) {
                aoMoverMouse(evt);
            }
        });
        cmpSelecionado = cmp;
    }
    
    private void insEvento(JComponent cmp, String nome, String texto,
    String hint, int nEvt) {
        cmp.setName(nome);
        cmp.setToolTipText(hint);
        // Coloca as propriedades especiais
        AnalisaJComponent cmpAnalisado = new AnalisaJComponent();
        cmpAnalisado.setText(cmp, texto);
        cmpAnalisado.setNumEvento(cmp, nEvt);
        // Menu do Objeto
        popMenu.add(itmPropriedade);
        popMenu.add(itmExclui);
        // Eventos do Menu do Objeto
        itmPropriedade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                propObjeto(e);
            }
        });
        itmExclui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excObjeto(e);
            }
        });
        // Eventos de Mouse
        cmp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aoClicarMouse(evt);
            }
            public void mousePressed(MouseEvent evt) {
                aoPressionarMouse(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                aoSoltarMouse(evt);
            }
            public void mouseEntered(MouseEvent evt) {
            }
            public void mouseExited(MouseEvent evt) {
            }
        });
        cmp.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent evt) {
                aoArrastarMouse(evt);
            }
            public void mouseMoved(MouseEvent evt) {
                aoMoverMouse(evt);
            }
        });
        cmpSelecionado = cmp;
    }
    
    private void propObjeto(ActionEvent e) {
        if (cmpSelecionado != null) {
            if (frmModi.ajObjeto)
                frmModi.janAjusta.setComponente(cmpSelecionado);
            else {
                frmModi.janAjusta = new AjustaObjeto(cmpSelecionado, frmModi);
                frmModi.ajObjeto = true;
            }
            //frmModi.janAjusta.show();
            frmModi.janAjusta.setVisible(true);
        }
    }
    
    private void excObjeto(ActionEvent e) {
        if (cmpSelecionado != null) {
            frmModi.remove(cmpSelecionado);
            if (frmModi.ajObjeto) {
                frmModi.janAjusta.aoFechar();
                frmModi.ajObjeto = false;
            }
            frmModi.repaint();
        }
    }
    
    private boolean naoResize() {
        return (!ajc.resize(cmpSelecionado));
    }
    
    private void aoClicarMouse(MouseEvent evt) {
        if (naoResize())
            if (evt.isMetaDown())
                popMenu.show(evt.getComponent(), evt.getX(), evt.getY());
    }
    
    private void aoPressionarMouse(MouseEvent evt) {
        Component cmp = evt.getComponent();
        if (cmp != null) {
            cursorAtual = MOVE_CURSOR;
            frmModi.setCursor(cursorAtual);
            if (naoResize())
                startMove(cmp, evt.getX(), evt.getY());
        }
    }
    
    private void aoSoltarMouse(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        Component cmp = evt.getComponent();
        if (naoResize()) {
            if (cmp != null) {
                deleteCaixaMov();
                finishMove(x, y);
            }
        }
        cursorAtual = DEFAULT_CURSOR;
        frmModi.setCursor(cursorAtual);
        frmModi.repaint();
    }
    
    private synchronized void aoArrastarMouse(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        Component cmp = evt.getComponent();
        if (cmp != null)
            if (naoResize())
                drawCaixaMov(getMoveBox(x+2, y+23));
    }
    
    private synchronized void aoMoverMouse(MouseEvent evt) {
        if (naoResize()) {
            if (cursorAtual == MOVE_CURSOR) {
                Component cmp = evt.getComponent();
                if (cmp != null)
                    startMove(cmp, evt.getX(), evt.getY());
            }
        }
    }
    
    private void drawCaixaMov(Rectangle box) {
        Graphics g = frmModi.getGraphics();
        if (g != null) {
            deleteCaixaMov();
            g.setColor(Color.red);
            g.drawRect(box.x, box.y, box.width, box.height);
            oldCaixaMov = box;
        }
    }
    
    private void deleteCaixaMov() {
        if (oldCaixaMov != null) {
            Graphics g = frmModi.getGraphics();
            if (g != null) {
                g.setColor(frmModi.getBackground());
                g.drawRect(oldCaixaMov.x, oldCaixaMov.y,
                oldCaixaMov.width, oldCaixaMov.height);
                oldCaixaMov = null;
            }
        }
    }
    
    private void startMove(Component child, int x, int y) {
        cmpMove = child;
        moveStartX = x;
        moveStartY = y;
    }
    
    private Rectangle getMoveBox(int mx, int my) {
        int x = cmpMove.getLocation().x;
        int y = cmpMove.getLocation().y;
        int w = cmpMove.getSize().width;
        int h = cmpMove.getSize().height;
        x = x + mx - moveStartX;
        y = y + my - moveStartY;
        return new Rectangle(x, y, w, h);
    }
    
    private void finishMove(int mx, int my) {
        Rectangle box = getMoveBox(mx, my);
        cmpMove.setBounds(box.x, box.y, box.width, box.height);
        deleteCaixaMov();
        cmpMove = null;
    }
}
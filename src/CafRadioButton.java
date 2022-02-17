import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JRadioButton;

/**
 * Classe que controla o JRadioButton, mas ela não é usada no PDialogMaker, então o fonte foi mantido apenas por compatibilidade
 * @author Daniel Atilio
 * @version 1.01
 * @see https://atiliosistemas.com/
 * @obs Baseado no software Cafeteira do grande Fernando Anselmo - http://fernandoanselmo.orgfree.com/wordpress/?page_id=7
 */
public class CafRadioButton extends JRadioButton {
    
	private static final long serialVersionUID = -6000299811976313573L;
	/** Construtor da classe
     * @param titulo String contendo o "text" para o CafRadioButton
     * @param recModi Janela principal
     */
    public CafRadioButton(String titulo, PDialogMaker recModi) {
        frmModi = recModi;
        this.setText(titulo);
        selected = false;
        resizing = true;
        selectRec = 6;
        
        resizex = 0;
        resizey = 0;
        resizeW = false;
        resizeN = false;
        nextnumber++;
        this.addMouseListener(new MouseAdapter() {
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
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent evt) {
                aoArrastarMouse(evt);
            }
            public void mouseMoved(MouseEvent evt) {
                aoMoverMouse(evt);
            }
        });
    }
    
    /** Desenhar o objeto corretamente
     * @param g Objeto Graphics
     */
    public void paint(Graphics g) {
        super.paint(g);
        //int xpixelsTMP = (int)this.getX();
        //int ypixelsTMP = (int)this.getY();
        int wpixelsTMP = (int)this.getWidth();
        int hpixelsTMP = (int)this.getHeight();
        if (selected) {
            int side = selectRec;
            g.setColor(Color.black);
            g.fillRect(0, 0, side, side);
            g.fillRect(wpixelsTMP - side, 0, side, side);
            g.fillRect(0, hpixelsTMP - side, side, side);
            g.fillRect(wpixelsTMP - side, hpixelsTMP - side, side, side);
        }
    }
    
    /** Modifica o redimensionamento do componente
     * @param val lógico informando o redimensionamento
     */
    public void setResizing(boolean val) {
        resizing = val;
    }
    
    /** Obtém o tamanho do componente
     * @return lógico contendo o redimensionamento
     */
    public boolean getResizing() {
        return resizing;
    }
    
    /*
    private void refresh() {
        frmModi.refresh();
    }
    */
    
    private void aoMoverMouse(MouseEvent e) {
        int cx = e.getX();
        int cy = e.getY();
        if (!resizing && selected) {
            resizex = cx;
            resizey = cy;
            resizeIncx = true;
            resizeIncy = true;
            resizeW = false;
            resizeN = false;
            if ((double)cx > this.getWidth() - (double)selectRec &&
            (double)cy > this.getHeight() - (double)selectRec) {
                setCursor(new Cursor(5));
                resizing = true;
                return;
            }
            if ((double)cx > this.getWidth() - (double)selectRec) {
                setCursor(new Cursor(11));
                resizing = true;
                resizeIncy = false;
                return;
            }
            if ((double)cy > this.getHeight() - (double)selectRec) {
                setCursor(new Cursor(9));
                resizing = true;
                resizeIncx = false;
                return;
            }
        }
        if (resizing) {
            setCursor(defCursor);
            resizing = false;
        }
    }
    
    private void aoClicarMouse(MouseEvent e) {
        selected = !selected;
        frmModi.refresh();
    }
    
    private void aoSoltarMouse(MouseEvent e) {
        //    if (resizing) {
        //      setCursor(defCursor);
        //      resizing = false;
        //    }
    }
    
    private void aoArrastarMouse(MouseEvent e) {
        if (resizing) {
            frmModi.resizeSelected(resizeIncx, resizeIncy, e.getX() - resizex,
            e.getY() - resizey);
            if (resizeIncx)
                resizex = e.getX();
            if (resizeIncy)
                resizey = e.getY();
        }
    }
    
    private void aoPressionarMouse(MouseEvent e) {
        //int x = e.getX();
        //int y = e.getY();
        if (resizing)
            selected = true;
        frmModi.refresh();
    }
    
    /** Modifica o número de eventos que este componente possui
     * @param newNumEvento Inteiro contendo o número de evento
     */
    public void setNumEvento(int newNumEvento) {
        numEvento = newNumEvento;
    }
    
    /** Obtém os eventos que este componente possui
     * @return Inteiro contendo o número de eventos
     */
    public int getNumEvento() {
        return numEvento;
    }
    
    private boolean selected;
    private boolean resizing;
    private int selectRec;
    public static int nextnumber = 1;
    private static Cursor defCursor = new Cursor(0);
    private int resizex;
    private int resizey;
    public boolean resizeW;
    public boolean resizeN;
    private boolean resizeIncx;
    private boolean resizeIncy;
    private PDialogMaker frmModi;
    private int numEvento = 0;
}